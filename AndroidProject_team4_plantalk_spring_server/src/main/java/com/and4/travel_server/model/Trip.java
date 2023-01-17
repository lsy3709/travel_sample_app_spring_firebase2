package com.and4.travel_server.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
	
	private int trip_id;
	private String title;
	private Long start_date;
	private Long end_date;
	private String place;
	private String member;
	private String phone;
	private int on_off;
}
