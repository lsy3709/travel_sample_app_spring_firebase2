package com.and4.travel_server.config.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.and4.travel_server.config.auth.PrincipalDetails;
import com.and4.travel_server.dto.LoginRequestDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {	
	
	private final AuthenticationManager authenticationManager;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("JwtAuthenticationFilter: 진입");
		
		ObjectMapper om = new ObjectMapper();
		LoginRequestDto loginRequestDto = null;
		
		try {
			loginRequestDto = om.readValue(request.getInputStream(), LoginRequestDto.class);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("JwtAuthenticationFilter: "+loginRequestDto);
		
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(
						loginRequestDto.getUsername(),
						loginRequestDto.getPassword());
		
		System.out.println("JwtAuthenticationFilter: 토큰생성완료");
		
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		System.out.println("Authentication: "+principalDetails.getUser().getUsername());
		
		return authentication;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain, Authentication authResult) throws IOException, ServletException {
		
		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
		
		String jwtToken = JWT.create()
				.withSubject(principalDetails.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
//				.withClaim("id", principalDetails.getUser().getUser_id())
				.withClaim("username", principalDetails.getUser().getUsername())
				.sign(Algorithm.HMAC512(JwtProperties.SECRET));
				
		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken);
		
		ObjectMapper om = new ObjectMapper();
		
		LoginRequestDto cmRequestDto = new LoginRequestDto();
		cmRequestDto.setUsername(principalDetails.getUser().getUsername());
		cmRequestDto.setPassword(principalDetails.getUser().getPassword());
		
		String cmRequestDtoJson = om.writeValueAsString(cmRequestDto);
		
		PrintWriter out = response.getWriter();
		out.print(cmRequestDtoJson);
		out.flush();
		
	}
}