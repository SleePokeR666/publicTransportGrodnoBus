package by.sinkevich;

public interface Route {

	long getPassengersLoadTime(BusStopName stop);

	long getRouteTime(BusStopName stop1, BusStopName stop2);
}
