package com.and4.travel_server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.and4.travel_server.model.Trip;
import com.and4.travel_server.service.TripService;

@RestController
@RequestMapping("/travel/trip/")
public class TripController {

	@Autowired
	private TripService tripService;
	
	@GetMapping("list")
	public Map<String,List<Trip>> doGetTripList() {
		Map<String,List<Trip>> map = new HashMap<String,List<Trip>>();
		map.put("trips", tripService.doGetTripList());
		System.out.println("체크222");
		return map;
	}
	
	@GetMapping("myList")
	public Map<String,List<Trip>> doGetMyTripList(String userName) {
		Map<String,List<Trip>> map = new HashMap<String,List<Trip>>();
		map.put("trips", tripService.doGetMyTripList(userName));
		System.out.println("체크333========="+tripService.doGetMyTripList(userName));
		return map;
	}
	
	@PostMapping("insert")
	public Trip doInsertTrip(@RequestBody Trip trip) {
		if(!trip.getTitle().equals("")) {
			tripService.doInsertTrip(trip);
		}
		System.out.println("체크111"+trip.getPhone()+"===");
		
		return trip;
	}
	
	@GetMapping("oneTrip")
	public Trip doGetOneTrip(@RequestParam("title") String title) {
		Trip trip = tripService.doGetOneTrip(title);
		
		return trip;
	}
	
	@GetMapping("plusMember")
	public Trip doGetTripId(@RequestParam("title") String title, @RequestParam("member") String member) {
		Trip trip = tripService.doGetOneTrip(title);
		trip.setMember(trip.getMember()+member+",");
		
		return trip;
	}
}
