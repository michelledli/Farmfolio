package com.iloveyou;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.iloveyou.entity.Account;
import com.iloveyou.repository.AccountRepository;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    AccountRepository accountRepository;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    private final Collection<String> admin = List.of("/api/admin");
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

    public AuthorizationFilter() {
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        boolean filter = exclude.stream()
                .anyMatch(p -> pathMatcher.match(p, request.getServletPath()));

        return filter;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        if (ServerConfiguration.USE_AUTH) {
            try {
                Claims claims = (Claims) request.getAttribute("claims");
                Long id = Long.valueOf(claims.getId());
                Account account = accountRepository.findById(id).orElse(null);

                if (!account.isAdmin()) {
                    if (admin.stream().anyMatch(p -> pathMatcher.match(p, request.getServletPath())))
                        throw new ServletException();
                }

                chain.doFilter(request, response);
            } catch (Exception e) {
                response.setStatus(403); // Forbidden
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
