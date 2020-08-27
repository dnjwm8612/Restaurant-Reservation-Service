package kr.co.test.eatgo.utils;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class JwtUtilTest {

	@Test
	public void createToken() {
		String secret="12345678901234567890123456789012";
		JwtUtil jwtUtil = new JwtUtil(secret);
		
		String token = jwtUtil.createToken(1004L, "John");
		
		assertThat(token, containsString("."));
		
	}

}
