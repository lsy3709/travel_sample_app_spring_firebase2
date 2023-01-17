package com.and4.travel_server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.and4.travel_server.model.Trip;

@Mapper
public interface TripMapper {

	List<Trip> doGetTripList();

	void doInsertTrip(Trip trip);

	Trip doGetOneTrip(String title);

	List<Trip> doGetMyTripList(String userName);

}
