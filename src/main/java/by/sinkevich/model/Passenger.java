package by.sinkevich.model;

public class Passenger {

	private String name;

	private BusStopName destination;

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
}
