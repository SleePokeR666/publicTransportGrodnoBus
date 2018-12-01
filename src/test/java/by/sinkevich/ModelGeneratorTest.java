package by.sinkevich;

import by.sinkevich.model.Bus;
import by.sinkevich.model.BusStopName;
import by.sinkevich.model.Passenger;
import by.sinkevich.util.ModelGenerator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayDeque;

import static by.sinkevich.model.BusStopName.*;
import static org.testng.Assert.*;

@Test
public class ModelGeneratorTest {

	private ModelGenerator generator;

	public void putPassengerOnBusStopTest() {
		//Expected
		Passenger expPassenger = new Passenger("Denis", SOVETSKAYA);

		//Actual
		generator.putPassengerOnBusStop(new Passenger("Denis", SOVETSKAYA), VISHNEVETS);
		Passenger actPassenger = CityMap.BUS_STOPS.get(VISHNEVETS).getPassengers().get(0);

		assertEquals(actPassenger, expPassenger);
	}

	public void createBusWithRouteFromStartPointTest() {
		//Expected
		ArrayDeque<BusStopName> route = new ArrayDeque<>();
		route.add(VISHNEVETS);
		route.add(YANKI_KUPALI);
		route.add(SOVETSKAYA);
		route.add(GORKOGO);
		route.add(DOMBROVSKOGO);
		Bus expBus = new Bus(1, route);

		//Actual
		Bus actBus = generator.createBusWithRouteFromStartPoint(1, VISHNEVETS);

		assertEquals(actBus, expBus);
	}

	@BeforeMethod
	public void initCityMap() {
		new CityMap();
		generator = new ModelGenerator();
	}

	@AfterMethod
	public void destroyCityMap() {
		generator = null;
	}
}
