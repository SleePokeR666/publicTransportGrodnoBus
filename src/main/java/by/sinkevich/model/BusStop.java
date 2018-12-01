package by.sinkevich.model;

import by.sinkevich.service.BusService;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BusStop {

	private BusStopName name;

	private List<Passenger> passengers;

	private BlockingQueue<BusService> busServices;

	private Lock lock;

	public BusStop(BusStopName name) {
		this.name = name;
		passengers = new LinkedList<>();
		busServices = new ArrayBlockingQueue<>(name.getMaxBusCapacity(), true);
		lock = new ReentrantLock(true);
	}

	public boolean addPassenger(Passenger passenger) {
		return passengers.add(passenger);
	}

	public long getPassengersLoadTime() {
		return name.getPassengersLoadTime();
	}

	public long getRouteTime() {
		return name.getRouteTime();
	}

	public BusStopName getName() {
		return name;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public BlockingQueue<BusService> getBusServices() {
		return busServices;
	}

	public Lock getLock() {
		return lock;
	}

}
