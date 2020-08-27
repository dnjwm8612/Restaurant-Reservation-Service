package kr.co.test.eatgo.utils;

import java.security.Key;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

	private Key key;
	
	public JwtUtil(String secret) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String createToken(long userId, String name) {
		//TODO: JJWT »ç¿ë
		String token = Jwts.builder().claim("userId", userId).claim("name", name).signWith(key, SignatureAlgorithm.HS256).compact();
		
		return "header.payload.signature";
	}

}
