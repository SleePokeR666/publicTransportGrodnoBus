package by.sinkevich.model;

import java.util.*;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Bus)) return false;
		Bus bus = (Bus) o;
		return number == bus.number &&
				Arrays.equals(route.toArray(), bus.route.toArray());
	}

	@Override
	public int hashCode() {
		return Objects.hash(number, route);
	}
}
