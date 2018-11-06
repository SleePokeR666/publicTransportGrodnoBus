package by.sinkevich;

public class Passenger {

	private String name;
	private BusStopName destination;
	private boolean isAlive;
	private BusStop currentBusStop;

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
