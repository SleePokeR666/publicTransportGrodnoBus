package by.sinkevich.model;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

public class Bus {

	protected int number;

	protected ArrayDeque<BusStopName> route;

	protected List<Passenger> passengers;

	public Bus(int number, ArrayDeque<BusStopName> route) {
		this.number = number;
		this.route = route;
		passengers = new LinkedList<>();
	}

	public int getNumber() {
		return number;
	}

	public ArrayDeque<BusStopName> getRoute() {
		return route;
	}
}
