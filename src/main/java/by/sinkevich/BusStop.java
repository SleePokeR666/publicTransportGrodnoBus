package by.sinkevich;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class BusStop extends Thread {

	private BusStopName stopName;
	private ArrayBlockingQueue<Bus> buses;
	private List<Passenger> passengers;

	public BusStop(BusStopName stopName) {
		this.stopName = stopName;
		buses = new ArrayBlockingQueue<>(3, true);
		passengers = new ArrayList<>();
	}

	@Override
	public void run() {
		while (true) {
			if (buses.size() > 0) {
				try {
					Bus bus = buses.take();
					bus.unloadPassengers(passengers);
					Thread.sleep(3000);
					bus.loadPassengers(passengers);
					bus.getRoute().remove(0);
					BusStopName nextStop = bus.getRoute().get(0);
					for (BusStop busStop : Start.BUS_STOPS) {
						if (busStop.getStopName() == nextStop) {
							busStop.addBus(bus);
						}
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (buses.size() == 0) {
					System.out.println("На остановке никого нету!");
					Thread.currentThread().interrupt();
					break;
				}
			}
		}
	}

	public void addBus(Bus bus) {
		try {
			System.out.printf("Автобус номер %d: %s -- %s приехал на остановку %s\n",
					bus.getNumber(), bus.getRoute().get(0), bus.getRoute().get(bus.getRoute().size() - 1), stopName);
			buses.put(bus);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean addPassenger(Passenger passenger) {
		return passengers.add(passenger);
	}

	public BusStopName getStopName() {
		return stopName;
	}
}
