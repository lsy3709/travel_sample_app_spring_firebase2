package com.and4.travel_server.service;

import java.util.List;

import com.and4.travel_server.model.Trip;

public interface TripService {
	
	public List<Trip> doGetTripList();

	public void doInsertTrip(Trip trip);

	public Trip doGetOneTrip(String title);

	public List<Trip> doGetMyTripList(String userName);
}
