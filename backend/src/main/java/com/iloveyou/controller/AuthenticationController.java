package com.iloveyou.controller;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iloveyou.entity.Account;
import com.iloveyou.repository.AccountRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import io.jsonwebtoken.Claims;

@Controller
public class AuthenticationController {
	@Autowired
	AccountRepository accountRepository;

	@Value("${jwt.secret}")
	private String secret;

	public static final boolean USE_PLAINTEXT = true;

	public static String encode(String password) {
		if (!USE_PLAINTEXT) {
			int strength = 10; // work factor of bcrypt
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
			password = bCryptPasswordEncoder.encode(password);
		}

		return password;
	}

	public static boolean isValid(String password, String encoding) {
		boolean isValid;

		if (!USE_PLAINTEXT) {
			int strength = 10; // work factor of bcrypt
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
			isValid = bCryptPasswordEncoder.matches(password, encoding);
		} else {
			isValid = encoding.contentEquals(password);
		}

		return isValid;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> createAccount(@RequestBody RegisterRequest request) {
		final ResponseEntity<Void> bad = ResponseEntity.badRequest().build();

		if (accountRepository.existsByEmail(request.email)) {
			return bad;
		}

		try {
			Account account = Account.builder().fname("").lname("").email(request.email)
					.password(encode(request.password))
					.isAdmin(request.isAdmin).build();
			return ResponseEntity.ok(accountRepository.save(account));
		} catch (Exception e) {
			return bad;
		}
	}

	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public ResponseEntity<?> changePassword(HttpServletRequest request) {
		ResponseEntity<Void> bad = ResponseEntity.badRequest().build();

		try {
			Claims claims = (Claims) request.getAttribute("claims");
			Long requesterId = Long.valueOf(claims.getId());
			Account requesterAccount = accountRepository.findById(requesterId).orElse(null);

			var body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

			ObjectMapper mapper = new ObjectMapper();
			ChangePasswordRequest chp = mapper.readValue(body, ChangePasswordRequest.class);

			Account requestedAccount = accountRepository.findByEmail(chp.email).orElse(null);

			// Account does not exist
			if (requestedAccount == null || requesterAccount == null) {
				return bad;
			}

			// Not admin or user changing their own password
			if (requesterAccount.getId() != requestedAccount.getId() && !requesterAccount.isAdmin()) {
				return bad;
			}

			requestedAccount.setPassword(encode(chp.password));

			return ResponseEntity.ok(accountRepository.save(requestedAccount));
		} catch (Exception e) {
			return bad;
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest request)
			throws ServletException, JsonProcessingException {

		ResponseEntity<Void> error = ResponseEntity.status(401).build();

		if (request.email == null || request.password == null) {
			return error;
		}

		Account account = accountRepository.findByEmail(request.email).orElse(null);

		if (account == null || !isValid(request.password, account.getPassword())) {
			return error;
		}

		Instant now = Instant.now();
		String token = Jwts.builder()
				.claim("admin", account.isAdmin())
				.setSubject(account.getEmail())
				.setId(account.getId().toString())
				.setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(1, ChronoUnit.DAYS)))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes()))
				.compact();

		AuthenticationResponse response = new AuthenticationResponse(token);
		String body = new ObjectMapper().writeValueAsString(response);

		return ResponseEntity.status(200).body(body);
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ResponseEntity<String> admin(String admin)
			throws ServletException, JsonProcessingException {
		return ResponseEntity.status(200).body("admin");
	}

	@SuppressWarnings("unused")
	static class RegisterRequest {
		public String email;
		public String password;
		public Boolean isAdmin;

		public RegisterRequest() {
		}
	}

	static class ChangePasswordRequest {
		public String email;
		public String password;

		public ChangePasswordRequest() {
		}

		@Override
		public String toString() {
			return this.email + " " + this.password;
		}
	}

	@SuppressWarnings("unused")
	private static class AuthenticationRequest {
		public String email;
		public String password;

		public AuthenticationRequest() {
		}
	}

	@SuppressWarnings("unused")
	private static class AuthenticationResponse {
		public final String token;

		public AuthenticationResponse(final String token) {
			this.token = token;
		}
	}
}
