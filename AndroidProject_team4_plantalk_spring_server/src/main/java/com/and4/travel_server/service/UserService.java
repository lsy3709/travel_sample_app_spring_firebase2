package com.and4.travel_server.service;

import com.and4.travel_server.model.User;

public interface UserService {

	public User doGetOneUser(String username);
	
	public void doInsertUser(User user);
}
