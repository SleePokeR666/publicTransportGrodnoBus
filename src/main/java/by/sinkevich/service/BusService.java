package by.sinkevich.service;

import by.sinkevich.CityMap;
import by.sinkevich.model.Bus;
import by.sinkevich.model.BusStop;
import by.sinkevich.model.Passenger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.locks.Lock;

public class BusService extends Bus implements Runnable {

	private static final Logger LOG = LogManager.getLogger();

	private BusStop currentStop;

	private boolean isRunning;

	public BusService(Bus bus) {
		super(bus.getNumber(), bus.getRoute());
		isRunning = true;
	}

	@Override
	public void run() {
		while (isRunning) {
			currentStop = getCurrentStop();
			sleep(currentStop.getRouteTime());
			takePlaceOnTheBusStop();

			unloadPassengers();
			sleep(currentStop.getPassengersLoadTime());
			loadPassengers();

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
		String message = String.format("Автобус номер %d приехал на остановку %s. Автобусов на остановке: %d",
				number, currentStop.getName(), currentStop.getBusServices().size());
		LOG.info(message);
	}

	private void freePlaceOnTheBusStop() {
		currentStop.getBusServices().remove(this);
		String message = String.format("Автобус номер %d выехал с остановки %s",
				number, currentStop.getName());
		LOG.info(message);
	}

	private void loadPassengers() {
		Lock lock = currentStop.getLock();
		lock.lock();
		Iterator<Passenger> iterator = currentStop.getPassengers().iterator();
		while (iterator.hasNext()) {
			Passenger passenger = iterator.next();
			if (route.contains(passenger.getDestination())) {
				passengers.add(passenger);
				LOG.info(String.format("%s сел в автобус номер %d", passenger.getName(), number));
				iterator.remove();
			}
		}
		lock.unlock();
	}

	private void unloadPassengers() {
		Iterator<Passenger> iterator = passengers.iterator();
		while (iterator.hasNext()) {
			Passenger passenger = iterator.next();
			if (passenger.getDestination() == currentStop.getName()) {
				currentStop.getPassengers().add(passenger);
				LOG.info(String.format("%s вышел", passenger.getName()));
				iterator.remove();
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
