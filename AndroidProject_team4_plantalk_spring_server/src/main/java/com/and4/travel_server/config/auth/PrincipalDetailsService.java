package com.and4.travel_server.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.and4.travel_server.model.User;
import com.and4.travel_server.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

	private final UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("PrincipalDetailsService: 진입  --리턴타입: UserDetails ====loadUserByUsername호출==");
		
		// username 디비에서 불러오기.
		User user = userService.doGetOneUser(username);
		
		// PrincipalDetails 객체 에 해당 유저 넣기.
		return new PrincipalDetails(user);
	}

}
