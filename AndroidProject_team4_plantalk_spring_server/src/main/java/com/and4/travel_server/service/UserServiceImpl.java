package com.and4.travel_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.and4.travel_server.mapper.UserMapper;
import com.and4.travel_server.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public User doGetOneUser(String username) {
		return userMapper.doGetOneUser(username);
	}

	@Override
	public void doInsertUser(User user) {
		userMapper.doInsertUser(user);
	}

}
