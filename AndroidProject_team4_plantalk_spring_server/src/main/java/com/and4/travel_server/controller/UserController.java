package com.and4.travel_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.and4.travel_server.model.User;
import com.and4.travel_server.service.UserService;

@RestController
@RequestMapping("/travel/user/")
public class UserController {
	

	@Autowired
	UserService userService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("join")
	public User doJoin(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userService.doInsertUser(user);
		System.out.println("=======================join 수행 후================123");
		return user;
	}
	
	@GetMapping("oneUser")
	public User doGetOneUser(@RequestParam("username") String username) {
		User user = userService.doGetOneUser(username);
		System.out.println("oneUser=====doGetOneUser==============="+user);
		return user;
	}
}
