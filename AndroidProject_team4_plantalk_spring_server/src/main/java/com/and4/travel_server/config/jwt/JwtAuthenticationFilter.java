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
		System.out.println("저장확인");
		
		ObjectMapper om = new ObjectMapper();
		LoginRequestDto loginRequestDto = null;
		
		try {
			//Convert "JSON" to "Java Object"
			loginRequestDto = om.readValue(request.getInputStream(), LoginRequestDto.class);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("JwtAuthenticationFilter:  loginRequestDto toString():  "+loginRequestDto);
		
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(
						loginRequestDto.getUsername(),
						loginRequestDto.getPassword());
		
		System.out.println("JwtAuthenticationFilter  =--> "
				+ " UsernamePasswordAuthenticationToken :   authenticationToken: 토큰생성완료");
		
		// 생성된 토큰 
		//전달된 인증 개체를 인증하려고 시도하고 성공하면 완전히 채워진 인증 개체(허가된 권한 포함)를 반환합니다.
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		
		//getPrincipal();
		//인증 중인 보안 주체의 ID입니다. 사용자 이름과 암호가 포함된 인증 요청의 경우 사용자 이름이 됩니다. 
		//호출자는 인증 요청을 위해 주체를 채울 것으로 예상됩니다.
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		System.out.println("Authentication 의 principalDetails.getUser().getUsername()의 내용: "+principalDetails.getUser().getUsername());
		
		return authentication;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain, Authentication authResult) throws IOException, ServletException {
		
		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
		
		System.out.println("principalDetails.getUsername() : 내용" + principalDetails.getUsername());
		
		String jwtToken = JWT.create()
				.withSubject(principalDetails.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
//				.withClaim("id", principalDetails.getUser().getUser_id())
				.withClaim("username", principalDetails.getUser().getUsername())
				.sign(Algorithm.HMAC512(JwtProperties.SECRET));
				
		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken);
		
		/*
		 * HMAC 알고리즘을 사용할 경우 서명은 다음과 같이 생성한다.
    HMACSHA256(
      base64UrlEncode(header) + "." +
      base64UrlEncode(payload),
      your-256-bit-secret
    )
		 * 
		 */
		
		
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