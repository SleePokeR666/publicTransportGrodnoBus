package by.sinkevich;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bus {

	private int number;
	private List<BusStopName> route;
	private List<Passenger> passengers;

	public Bus(int number, List<BusStopName> route) {
		this.number = number;
		this.route = route;
		passengers = new ArrayList<>();
	}

	public void loadPassengers(List<Passenger> passengers) {
		this.passengers = passengers.stream()
				.filter(p -> route.contains(p.getDestination()))
				.peek(p -> System.out.println(p.getName() + " сел в автобус номер " + number + "\n"))
				.collect(Collectors.toList());
	}

	public void unloadPassengers(List<Passenger> passengers) {
		for (Passenger passenger : this.passengers) {
			if (passenger.getDestination() == route.get(0)) {
				passengers.add(passenger);
			}
		}
	}

	public int getNumber() {
		return number;
	}

	public List<BusStopName> getRoute() {
		return route;
	}
}
