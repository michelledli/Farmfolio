package com.iloveyou;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String secret;

    private final Collection<String> exclude = List.of(
            "/api/login",
            "/",
            "",
            "/login",
            "/*.js",
            "/*.css",
            "/*.ico",
            "/*.html",
            "/*.png");

    public AuthenticationFilter() {
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        boolean filter = exclude.stream()
                .anyMatch(p -> pathMatcher.match(p, request.getServletPath()));

        return filter;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        if (RootConfiguration.USE_AUTH) {
            try {
                String token = Arrays.stream(request.getCookies())
                        .filter((cookie) -> {
                            return cookie.getName().equals("access_token");
                        })
                        .map(Cookie::getValue)
                        .findAny().orElse(null);

                if (token == null) {
                    throw new ServletException();
                }

                Claims claims = Jwts
                        .parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                if (claims == null) {
                    throw new ServletException();
                }

                request.setAttribute("claims", claims);
                chain.doFilter(request, response);

            } catch (Exception e) {
                String redirectUrl = response.encodeRedirectURL("/");
                response.setStatus(401); // Unauthorized
                response.setHeader("Location", redirectUrl);
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}