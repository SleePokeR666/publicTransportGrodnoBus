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

		generator.createPassenger(VISHNEVETS, SOVETSKAYA, "Denis1");
		generator.createPassenger(VISHNEVETS, DOMBROVSKOGO, "Denis2");
		generator.createPassenger(OLSHANKA, SOVETSKAYA, "Yulia1");
		generator.createPassenger(OLSHANKA, ZARITSA, "Yulia2");
		generator.createPassenger(FOLUSH, SOVETSKAYA, "Vasia1");
		generator.createPassenger(FOLUSH, DEVIATOVKA, "Vasia2");
		generator.createPassenger(DOMBROVSKOGO, SOVETSKAYA, "Artem1");
		generator.createPassenger(DOMBROVSKOGO, VISHNEVETS, "Artem2");
		generator.createPassenger(ZARITSA, SOVETSKAYA, "Zina1");
		generator.createPassenger(ZARITSA, OLSHANKA, "Zina2");
		generator.createPassenger(DEVIATOVKA, SOVETSKAYA, "Oleg1");
		generator.createPassenger(DEVIATOVKA, FOLUSH, "Oleg2");

		generator.createBusService(VISHNEVETS, DOMBROVSKOGO);
		generator.createBusService(VISHNEVETS, DOMBROVSKOGO);
		generator.createBusService(VISHNEVETS, DOMBROVSKOGO);
		generator.createBusService(VISHNEVETS, DOMBROVSKOGO);
		generator.createBusService(OLSHANKA, ZARITSA);
		generator.createBusService(FOLUSH, DEVIATOVKA);
		generator.createBusService(DOMBROVSKOGO, VISHNEVETS);
		generator.createBusService(ZARITSA, OLSHANKA);
		generator.createBusService(DEVIATOVKA, FOLUSH);
	}
}
