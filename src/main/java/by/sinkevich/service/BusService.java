package by.sinkevich.service;

import by.sinkevich.CityMap;
import by.sinkevich.model.Bus;
import by.sinkevich.model.BusStop;
import by.sinkevich.model.BusStopName;
import by.sinkevich.model.Passenger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BusService extends Bus implements Runnable {

	private static final Logger LOG = LogManager.getLogger();

	private static final Lock lock = new ReentrantLock();

	private BusStop currentStop;

	private boolean isRunning;

	public BusService(int number, ArrayDeque<BusStopName> route) {
		super(number, route);
		isRunning = true;
	}

	@Override
	public void run() {
		while (isRunning) {
			currentStop = getCurrentStop();
			sleep(currentStop.getRouteTime());
			takePlaceOnTheBusStop();

			lock.lock();
			unloadPassengers();
			sleep(currentStop.getPassengersLoadTime());
			loadPassengers();
			lock.unlock();

			freePlaceOnTheBusStop();
			sleep(currentStop.getRouteTime());

			if (route.isEmpty()) {
				isRunning = false;
			}
		}
	}

	private void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
			LOG.error(e.getMessage(), e);
			Thread.currentThread().interrupt();
		}
	}

	private BusStop getCurrentStop() {
		BusStop busStop = null;
		if (!route.isEmpty()) {
			busStop = CityMap.BUS_STOPS.get(route.poll());
		}
		return busStop;
	}

	private void takePlaceOnTheBusStop() {
		try {
			currentStop.getBusServices().put(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
			LOG.error(e.getMessage(), e);
			Thread.currentThread().interrupt();
		}
		String message = String.format("Автобус номер %d приехал на остановку %s",
				number, currentStop.getName());
		LOG.info(message);
	}

	private void freePlaceOnTheBusStop() {
		currentStop.getBusServices().remove(this);
		String message = String.format("Автобус номер %d выехал с остановки %s",
				number, currentStop.getName());
		LOG.info(message);
	}

	private void loadPassengers() {
		for (Passenger passenger : currentStop.getPassengers()) {
			if (route.contains(passenger.getDestination())) {
				passengers.add(passenger);
				currentStop.getPassengers().remove(passenger);
				System.out.println(passenger.getName() + " сел в автобус номер " + number);
			}
		}
	}

	private void unloadPassengers() {
		for (Passenger passenger : passengers) {
			if (passenger.getDestination() == currentStop.getName()) {
				currentStop.getPassengers().add(passenger);
				passengers.remove(passenger);
				System.out.println(passenger.getName() + " вышел");
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BusService)) return false;
		BusService busService = (BusService) o;
		return number == busService.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}
