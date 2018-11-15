package by.sinkevich.model;

import by.sinkevich.service.BusService;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BusStop {

	private BusStopName name;

	private List<Passenger> passengers;

	private BlockingQueue<BusService> busServices;

	public BusStop(BusStopName name) {
		this.name = name;
		passengers = new LinkedList<>();
		busServices = new ArrayBlockingQueue<>(3, true);
	}

	public boolean addPassenger(Passenger passenger) {
		return passengers.add(passenger);
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

	public long getPassengersLoadTime() {
		return 1000L;
	}

	public long getRouteTime() {
		return 1500L;
	}
}
