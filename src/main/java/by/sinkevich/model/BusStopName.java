package by.sinkevich.model;

public enum BusStopName {

	SOVETSKAYA(3, 1000, 600),
	YANKI_KUPALI(2, 1250, 500),
	VISHNEVETS(4, 1500, 400),
	KORONA_MARKET(2, 1250, 500),
	ZARITSA(4, 1500, 400),
	TOMINA(2, 1250, 500),
	OLSHANKA(4, 1500, 400),
	GORKOGO(2, 1250, 500),
	DOMBROVSKOGO(4, 1500, 400),
	OBUVNAYA_FABRIKA(2, 1250, 500),
	FOLUSH(4, 1500, 400),
	TRAIN_STATION(2, 1250, 500),
	DEVIATOVKA(4, 1500, 400);

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
