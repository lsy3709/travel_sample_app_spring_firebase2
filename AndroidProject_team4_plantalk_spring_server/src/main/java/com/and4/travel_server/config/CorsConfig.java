package com.and4.travel_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

//	Cross Origin에서 자원을 요청하기 위해서는 다음과 같은 과정을 거친다.
//
//	HTTP 통신 헤더인 Origin 헤더에 요청을 보내는 곳의 정보를 담고 서버로 요청을 보낸다.
//	이후 서버는 Access-Control-Allow-Origin헤더에 허용된 Origin이라는 정보를 담아 보낸다
//	클라이언트는 헤더의 값과 비교해 정상 응답임을 확인하고 지정된 요청을 보낸다.
//	서버는 요청을 수행하고 200OK 코드를 응답한다.
	
	//Class CorsFilter 공식 문서
	//https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/filter/CorsFilter.html
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		
		source.registerCorsConfiguration("/**", config);
		
		return new CorsFilter(source);
	}
}