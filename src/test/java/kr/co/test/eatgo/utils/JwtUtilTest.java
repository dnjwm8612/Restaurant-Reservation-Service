package kr.co.test.eatgo.utils;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Claims;

class JwtUtilTest {
	
	String secret="12345678901234567890123456789012";
	JwtUtil jwtUtil;
	
	@BeforeEach
	public void setUp() {
		jwtUtil = new JwtUtil(secret);
	}
	
	@Test
	public void createToken() {	
		String token = jwtUtil.createToken(1004L, "John");
		
		assertThat(token, containsString("."));
		
	}
	
	@Test
	public void getClaims() {
		String token= "";
		Claims claims =jwtUtil.getClaims(token);
		
		assertThat(claims.get("name"), is("Tester"));
	}

}
