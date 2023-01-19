package com.and4.travel_server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.and4.travel_server.config.jwt.JwtAuthenticationFilter;
import com.and4.travel_server.config.jwt.JwtAuthorizationFilter;
import com.and4.travel_server.service.UserService;

//해당 클래스를 Configuration으로 등록한다.
@Configuration

//스프링시큐리티 사용을 위한 어노테이션선언
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CorsConfig corsConfig;
	
	
	
//- BCryptPasswordEncoder는 BCrypt 해싱 함수(BCrypt hashing function)를 
	//사용해서 비밀번호를 인코딩해주는 메서드와 
	//사용자의 의해 제출된 비밀번호와 저장소에 저장되어 있는 비밀번호의 일치 여부를 확인해주는 메서드를 제공합니다.

//- PasswordEncoder 인터페이스를 구현한 클래스입니다.
//
//- 생성자의 인자 값(verstion, strength, SecureRandom instance)을 통해서 해시의 강도를 조절할 수 있습니다.
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//hasRole() or hasAnyRole()
		//특정 권한을 가지는 사용자만 접근할 수 있습니다
		
		//Role은 역할이고 Authority는 권한이지만 사실은 표현의 차이입니다. 
		//Role은 “ADMIN”으로 표현하고 Authority는 “ROLE_ADMIN”으로 표기합니다.
		
		http
		.addFilter(corsConfig.corsFilter())
		.csrf().disable() //csrf 보안 설정을 비활성화 , 해당 기능을 사용하기 위해서는 프론트단에서 csrf토큰값 보내줘야함
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // SessionCreationPolicy.STATELESS     - 스프링시큐리티가 생성하지도않고 기존것을 사용하지도 않음        ->JWT 같은토큰방식을 쓸때 사용하는 설정 
		.and()
		.httpBasic().disable() //사용자 인증방법으로는 HTTP Basic Authentication을 사용 안한다.
		.addFilter(new JwtAuthenticationFilter(authenticationManager()))  // JwtAutienticationFilter : jwt를 사용해서 인증 처리
		.addFilter(new JwtAuthorizationFilter(authenticationManager(), userService)) // JwtAutiorizationFilter : jwt를 사용해서 인가 처리
		.authorizeRequests()
		.antMatchers("/api/user/**") 	// 스프링 시큐리티 룰을 무시하게 하는 Url 규칙(여기 등록하면 규칙 적용하지 않음)
			.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") //ROLE_USER, ROLE_MANAGER, ROLE_ADMIN이라는 롤을 가진 사용자만 접근 허용
		.antMatchers("/api/manager/**")
			.access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
		.antMatchers("/api/admin/**")
			.access("hasRole('ROLE_ADMIN')")
		.anyRequest().permitAll(); // 위에 권한 접근 외에 어떤 요청도 다 허용함. 
		
		
	}
}
