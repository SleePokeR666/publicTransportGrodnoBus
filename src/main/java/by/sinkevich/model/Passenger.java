package by.sinkevich.model;

import java.util.Objects;

public class Passenger {

	private String name;

	private BusStopName destination;

	public Passenger() {
		super();
	}

	public Passenger(String name, BusStopName destination) {
		this.name = name;
		this.destination = destination;
	}

	public String getName() {
		return name;
	}

	public BusStopName getDestination() {
		return destination;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Passenger)) return false;
		Passenger passenger = (Passenger) o;
		return name.equals(passenger.name) &&
				destination == passenger.destination;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, destination);
	}
}
