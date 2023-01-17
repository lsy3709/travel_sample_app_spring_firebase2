package com.and4.travel_server.config.jwt;

public interface JwtProperties {

	String SECRET = "빅데이터5기";
	int EXPIRATION_TIME = 864000000;
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
}
