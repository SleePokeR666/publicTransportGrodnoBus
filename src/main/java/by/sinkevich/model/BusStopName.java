package by.sinkevich.model;

public enum BusStopName {

	SOVETSKAYA(3, 600, 1000),
	YANKI_KUPALI(2, 500, 1250),
	VISHNEVETS(4, 400, 1500),
	KORONA_MARKET(2, 500, 1250),
	ZARITSA(4, 400, 1500),
	TOMINA(2, 500, 1250),
	OLSHANKA(4, 400, 1500),
	GORKOGO(2, 500, 1250),
	DOMBROVSKOGO(4, 400, 1500),
	OBUVNAYA_FABRIKA(2, 500, 1250),
	FOLUSH(4, 400, 1500),
	TRAIN_STATION(2, 500, 1250),
	DEVIATOVKA(4, 400, 1500);

	private int maxBusCapacity;

	private long routeTime;

	private long passengersLoadTime;

	BusStopName(int maxBusCapacity, long routeTime, long passengersLoadTime) {
		this.maxBusCapacity = maxBusCapacity;
		this.routeTime = routeTime;
		this.passengersLoadTime = passengersLoadTime;
	}

	public int getMaxBusCapacity() {
		return maxBusCapacity;
	}

	public long getRouteTime() {
		return routeTime;
	}

	public long getPassengersLoadTime() {
		return passengersLoadTime;
	}
}
