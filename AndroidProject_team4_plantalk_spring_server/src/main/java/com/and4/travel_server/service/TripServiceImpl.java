package com.and4.travel_server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.and4.travel_server.mapper.TripMapper;
import com.and4.travel_server.model.Trip;

@Service
public class TripServiceImpl implements TripService {

	@Autowired
	private TripMapper tripMapper;
	
	@Override
	public List<Trip> doGetTripList() {
		return tripMapper.doGetTripList();
	}

	@Override
	public void doInsertTrip(Trip trip) {
		tripMapper.doInsertTrip(trip);
	}

	@Override
	public Trip doGetOneTrip(String title) {
		return tripMapper.doGetOneTrip(title);
	}

	@Override
	public List<Trip> doGetMyTripList(String userName) {
		return tripMapper.doGetMyTripList(userName);
	}

}
