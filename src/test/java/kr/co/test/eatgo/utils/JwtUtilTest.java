package kr.co.test.eatgo.utils;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

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
		String token= "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKb2huIn0.8hm6ZOJykSINHxL-rf0yV882fApL3hyQ9-WGlJUyo2A";
		Claims claims =jwtUtil.getClaims(token);
		
		assertThat(claims.get("userId", Long.class), is(1004L));
		assertThat(claims.get("name"), is("John"));
	}

}
