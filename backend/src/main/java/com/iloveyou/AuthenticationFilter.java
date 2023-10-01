package com.iloveyou;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class AuthenticationFilter extends GenericFilterBean {

    private final String secret;

    public AuthenticationFilter(String secret) {
        this.secret = secret;
    }

    public Optional<String> readServletCookie(HttpServletRequest request, String name) {
        return Arrays.stream(request.getCookies())
                .filter((cookie) -> {
                    return name.equals(cookie.getName());
                })
                .map(Cookie::getValue)
                .findAny();
    }

    @Override
    public void doFilter(ServletRequest req,
            ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        String token = readServletCookie(request, "access_token").orElse(null);

        if (token != null) {
            try {
                Claims claims = Jwts
                        .parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                request.setAttribute("claims", claims);
                
            } catch (SignatureException e) {
                throw new ServletException();
            }
        }

        chain.doFilter(request, res);
    }
}