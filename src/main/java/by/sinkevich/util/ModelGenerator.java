package by.sinkevich.util;

import by.sinkevich.CityMap;
import by.sinkevich.model.Bus;
import by.sinkevich.model.BusStop;
import by.sinkevich.model.BusStopName;
import by.sinkevich.model.Passenger;
import by.sinkevich.service.BusService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;

import static by.sinkevich.model.BusStopName.*;

public class ModelGenerator {

	private static final Logger LOG = LogManager.getLogger();

	public ModelGenerator() {
		super();
	}

	private ArrayDeque<BusStopName> createRoute(BusStopName startingPoint) {
		ArrayDeque<BusStopName> route = new ArrayDeque<>();
		switch (startingPoint) {
			case VISHNEVETS:
				route.add(VISHNEVETS);
				route.add(YANKI_KUPALI);
				route.add(SOVETSKAYA);
				route.add(GORKOGO);
				route.add(DOMBROVSKOGO);
				break;

			case OLSHANKA:
				route.add(OLSHANKA);
				route.add(TOMINA);
				route.add(SOVETSKAYA);
				route.add(KORONA_MARKET);
				route.add(ZARITSA);
				break;

			case FOLUSH:
				route.add(FOLUSH);
				route.add(OBUVNAYA_FABRIKA);
				route.add(SOVETSKAYA);
				route.add(TRAIN_STATION);
				route.add(DEVIATOVKA);
				break;

			case DOMBROVSKOGO:
				route.add(DOMBROVSKOGO);
				route.add(GORKOGO);
				route.add(SOVETSKAYA);
				route.add(YANKI_KUPALI);
				route.add(VISHNEVETS);
				break;

			case ZARITSA:
				route.add(ZARITSA);
				route.add(KORONA_MARKET);
				route.add(SOVETSKAYA);
				route.add(TOMINA);
				route.add(OLSHANKA);
				break;

			case DEVIATOVKA:
				route.add(DEVIATOVKA);
				route.add(TRAIN_STATION);
				route.add(SOVETSKAYA);
				route.add(OBUVNAYA_FABRIKA);
				route.add(FOLUSH);
				break;
		}

		return route;
	}

	public Passenger putPassengerOnBusStop(Passenger passenger, BusStopName startPoint) {
		String message = String.format("Пассажир %s пришёл на остановку %s чтобы доехать на %s",
				passenger.getName(), startPoint, passenger.getDestination());
		LOG.info(message);
		BusStop busStop = CityMap.BUS_STOPS.get(startPoint);
		busStop.addPassenger(passenger);
		return passenger;
	}

	public Bus createBusWithRouteFromStartPoint(int number, BusStopName startPoint) {
		ArrayDeque<BusStopName> route = createRoute(startPoint);
		return new Bus(number, route);
	}

	public BusService createBusService(Bus bus) {
		BusService busService = new BusService(bus);
		String message = String.format("Автобус номер %d выехал на маршрут %s -- %s",
				busService.getNumber(),
				busService.getRoute().getFirst(),
				busService.getRoute().getLast());
		LOG.info(message);
		return busService;
	}
}
