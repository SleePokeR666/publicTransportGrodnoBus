package by.sinkevich;

import by.sinkevich.model.BusStop;
import by.sinkevich.model.BusStopName;

import java.util.HashMap;

public class CityMap {

	public static final HashMap<BusStopName, BusStop> BUS_STOPS;

	static {
		BUS_STOPS = new HashMap<>();
	}

	public CityMap() {
		init();
	}

	private static void init() {
		for (BusStopName name : BusStopName.values()) {
			BusStop busStop = new BusStop(name);
			BUS_STOPS.put(name, busStop);
		}

	}
}
