package com.iloveyou.domain.service;

import java.util.UUID;
import java.util.Optional;
import java.security.Key;

import com.iloveyou.domain.persistence.UserDAO;
import com.iloveyou.domain.User;
import com.iloveyou.domain.AuthenticationRequest;
import com.iloveyou.domain.AuthenticationResponse;

import io.javalin.http.ForbiddenResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class UserService {
    UserDAO dao;
    private final Key key;

    public UserService(UserDAO dao) {
        this.dao = dao;
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        Optional<User> query = dao.findByEmail(request.getEmail());
        
        if(query.isPresent()) {
            User user = query.get();

            if(request.getPassword().equals(user.getPassword())) {
                return new AuthenticationResponse(
                    user.getId(),
                    issueToken(user.getId()));
            }
            else {
                throw new ForbiddenResponse();
            }
        }
        else {
            throw new ForbiddenResponse();
        }
    }

    public AuthenticationResponse signup(AuthenticationRequest request) {
        User user = new User(
            UUID.randomUUID(), 
            request.getEmail(), 
            request.getPassword());

        dao.create(user);

        String token = issueToken(user.getId());
        
        AuthenticationResponse response = new AuthenticationResponse(user.getId(), token);
        return response;
    }

    private String issueToken(UUID id) {
        String token = Jwts.builder().setSubject(id.toString()).signWith(key).compact();
        return token;
    }

    
    public boolean authorize(String token, UUID id) {
        boolean isAuthorized = false;

        if (token == null){
            throw new ForbiddenResponse();
        }
        
        try {
            String subject = Jwts.parserBuilder()
                                 .setSigningKey(this.key)
                                 .build()
                                 .parseClaimsJws(token)
                                 .getBody()
                                 .getSubject();
            isAuthorized = subject.equalsIgnoreCase(id.toString());
        } catch (Exception ex) {}

        return isAuthorized;
    }
}
