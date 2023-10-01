package com.iloveyou.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iloveyou.entity.Account;
import com.iloveyou.repository.AccountRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AuthenticationController {
	@Autowired
	AccountRepository accountRepository;

	@Value("${jwt.secret}")
	private String secret;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody AuthenticationRequest request)
			throws ServletException, JsonProcessingException {

		Account account = accountRepository.findByEmailAndPassword(request.email, request.password);

		// if (request.email == null || request.password == null || !accountRepository.existsByEmailAndPassword(request.email, request.password)) {
		// 	return ResponseEntity.status(401).build();
		// }

		if(account == null ) {
			return ResponseEntity.status(401).build();
		}

		Instant now = Instant.now();
		String token = Jwts.builder()
				.claim("roles", account.getRoles())
				.setSubject(account.getEmail())
				.setId(account.getId().toString())
				.setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(1, ChronoUnit.DAYS)))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes()))
				.compact();

		AuthenticationResponse response = new AuthenticationResponse(token);
		ObjectMapper mapper = new ObjectMapper();
		return ResponseEntity.status(200).body(mapper.writeValueAsString(response));
	}

	@SuppressWarnings("unused")
	private static class AuthenticationRequest {
		private final String email;
		private final String password;

		public AuthenticationRequest(String email, String password) {
			this.email = email;
			this.password = password;
		}

		public String getEmail() {
			return this.email;
		}

		public String getPassword() {
			return this.password;
		}
	}

	@SuppressWarnings("unused")
	private static class AuthenticationResponse {
		public final String token;

		public AuthenticationResponse(final String token) {
			this.token = token;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/role/{role}", method = RequestMethod.GET)
	public ResponseEntity<String> role(@PathVariable String role,
			HttpServletRequest request) throws ServletException {
		Claims claims = (Claims) request.getAttribute("claims");

		return ResponseEntity.ok(
				String.valueOf(
						((List<String>) claims.get("roles")).contains(role)));
	}
}
