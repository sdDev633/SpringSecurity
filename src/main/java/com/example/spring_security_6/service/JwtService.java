package com.example.spring_security_6.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.spring_security_6.entity.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private String secretKey = null;
	public String generateToken(Users user) {
		Map<String, Object> claims = new HashMap<>();
		
		return Jwts
				.builder()
				.claims()
				.add(claims)
				.subject(user.getUsername())
				.issuer("Shrijit")
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+60*10*1000))
				.and()
				.signWith(generateKey())
				.compact();
	}
	
	private SecretKey generateKey() {
		byte[] decode =  Decoders.BASE64.decode(getSecretKey());
		
		return Keys.hmacShaKeyFor(decode);
	}
	
	public String getSecretKey() {
		return secretKey = "09c1c266eb2f08376308a47e004255a0a2dbb8b12b332248871aaae7eab287a5";
	}
	
	public String extractUserName(String token) {
		return extractClaims(token, Claims::getSubject);
	}
	
	private <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
		Claims claims = extractClaims(token);
		return claimResolver.apply(claims);
	}
	
	private Claims extractClaims(String token) {
		return Jwts
				.parser()
				.verifyWith(generateKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	public boolean isTokenValid(String token, UserDetails userDetails) {
		// TODO Auto-generated method stub
		final String username = extractUserName(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpirationToken(token).before(new Date());
	}
	
	private Date extractExpirationToken(String token) {
		return extractClaims(token, Claims::getExpiration);
	}
}
