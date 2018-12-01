package by.sinkevich;

import by.sinkevich.model.Bus;
import by.sinkevich.model.Passenger;
import by.sinkevich.util.ModelGenerator;
import org.testng.annotations.Test;

import static by.sinkevich.model.BusStopName.*;
import static org.testng.Assert.*;

@Test
public class CityMapTest {

	public void busServiceResultsTest() {
		new CityMap();
		ModelGenerator generator = new ModelGenerator();

		//expected
		Passenger expPassenger1 = new Passenger("VishDom", DOMBROVSKOGO);
		Passenger expPassenger2 = new Passenger("OlshZar", ZARITSA);
		Passenger expPassenger3 = new Passenger("FolDev", DEVIATOVKA);
		Passenger expPassenger4 = new Passenger("DomVish", VISHNEVETS);
		Passenger expPassenger5 = new Passenger("ZarOlsh", OLSHANKA);
		Passenger expPassenger6 = new Passenger("DevFol", FOLUSH);

		generator.putPassengerOnBusStop(new Passenger("VishDom", DOMBROVSKOGO), VISHNEVETS);
		generator.putPassengerOnBusStop(new Passenger("OlshZar", ZARITSA), OLSHANKA);
		generator.putPassengerOnBusStop(new Passenger("FolDev", DEVIATOVKA), FOLUSH);
		generator.putPassengerOnBusStop(new Passenger("DomVish", VISHNEVETS), DOMBROVSKOGO);
		generator.putPassengerOnBusStop(new Passenger("ZarOlsh", OLSHANKA), ZARITSA);
		generator.putPassengerOnBusStop(new Passenger("DevFol", FOLUSH), DEVIATOVKA);

		Bus bus1 = generator.createBusWithRouteFromStartPoint(1, VISHNEVETS);
		Bus bus2 = generator.createBusWithRouteFromStartPoint(2, OLSHANKA);
		Bus bus3 = generator.createBusWithRouteFromStartPoint(3, FOLUSH);
		Bus bus4 = generator.createBusWithRouteFromStartPoint(4, DOMBROVSKOGO);
		Bus bus5 = generator.createBusWithRouteFromStartPoint(5, ZARITSA);
		Bus bus6 = generator.createBusWithRouteFromStartPoint(6, DEVIATOVKA);

		Thread thread1 = new Thread(generator.createBusService(bus1));
		thread1.start();
		Thread thread2 = new Thread(generator.createBusService(bus2));
		thread2.start();
		Thread thread3 = new Thread(generator.createBusService(bus3));
		thread3.start();
		Thread thread4 = new Thread(generator.createBusService(bus4));
		thread4.start();
		Thread thread5 = new Thread(generator.createBusService(bus5));
		thread5.start();
		Thread thread6 = new Thread(generator.createBusService(bus6));
		thread6.start();

		try {
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
			thread5.join();
			thread6.join();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		//Actual
		Passenger actPassenger1 = CityMap.BUS_STOPS.get(DOMBROVSKOGO).getPassengers().get(0);
		Passenger actPassenger2 = CityMap.BUS_STOPS.get(ZARITSA).getPassengers().get(0);
		Passenger actPassenger3 = CityMap.BUS_STOPS.get(DEVIATOVKA).getPassengers().get(0);
		Passenger actPassenger4 = CityMap.BUS_STOPS.get(VISHNEVETS).getPassengers().get(0);
		Passenger actPassenger5 = CityMap.BUS_STOPS.get(OLSHANKA).getPassengers().get(0);
		Passenger actPassenger6 = CityMap.BUS_STOPS.get(FOLUSH).getPassengers().get(0);

		assertEquals(actPassenger1, expPassenger1);
		assertEquals(actPassenger2, expPassenger2);
		assertEquals(actPassenger3, expPassenger3);
		assertEquals(actPassenger4, expPassenger4);
		assertEquals(actPassenger5, expPassenger5);
		assertEquals(actPassenger6, expPassenger6);
	}
}
