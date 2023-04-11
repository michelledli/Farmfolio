package com.iloveyou.domain;

import java.util.UUID;

public class AuthenticationResponse {
    private UUID id;
    private String token;

    public AuthenticationResponse(UUID id, String token) {
        this.id = id;
        this.token = token;
    }

    public UUID getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}