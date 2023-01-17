package com.and4.travel_server.model;

import lombok.Data;

@Data
public class Photo {
	private String pname;
	private String url;
	private int dno;
	private int trip_id;
}
