package com.iloveyou.domain.auth;

public interface TokenManager {
    String issueToken (String userId);
    boolean authorize (String token, String userId);
}