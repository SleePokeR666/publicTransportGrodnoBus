package by.sinkevich;

import by.sinkevich.model.BusStop;
import by.sinkevich.model.BusStopName;
import by.sinkevich.util.ModelGenerator;

import java.util.HashMap;

import static by.sinkevich.model.BusStopName.*;

public class CityMap {

	public static final HashMap<BusStopName, BusStop> BUS_STOPS;
	private static final ModelGenerator generator;

	static {
		BUS_STOPS = new HashMap<>();
		generator = new ModelGenerator();
	}

	public CityMap() {
		init();
	}

	private static void init() {
		for (BusStopName name : BusStopName.values()) {
			BusStop busStop = new BusStop(name);
			BUS_STOPS.put(name, busStop);
		}

		generator.createPassenger(VISHNEVETS, SOVETSKAYA);
		generator.createBus(VISHNEVETS, DOMBROVSKOGO);
	}
}
