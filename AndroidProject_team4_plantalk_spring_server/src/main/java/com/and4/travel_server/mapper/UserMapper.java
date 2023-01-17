package com.and4.travel_server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.and4.travel_server.model.User;

@Repository
@Mapper
public interface UserMapper {
	
	public User doGetOneUser(String username);

	public void doInsertUser(User user);

}
