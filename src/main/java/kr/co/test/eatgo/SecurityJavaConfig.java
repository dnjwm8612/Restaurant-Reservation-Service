package kr.co.test.eatgo;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.co.test.eatgo.filters.JwtAuthenticationFilter;
import kr.co.test.eatgo.utils.JwtUtil;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter{
	
	@Value("${jwt.secret}")
	private String secret;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		Filter filter = new JwtAuthenticationFilter(authenticationManager(), jwtUtil());
		
		http.formLogin().disable()
		.cors().disable()
		.csrf().disable()
		.headers().frameOptions().disable()
		.and()//초기화 .headers가 httpSecurity 하나 낮은 HeadersConfigurer 있기 때문에 초기화 작업 진행
		.addFilter(filter)
		.sessionManagement()//.sessionManagement이동
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);//정책설정 받은 토근을 filter에서 자동 처리가능 하도록 설정
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtUtil jwtUtil() {
		return new JwtUtil(secret);
	}
	
}
