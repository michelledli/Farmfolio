package com.iloveyou.domain;

public class AuthenticationRequest {
    private String email;
    private String password;

    public AuthenticationRequest() {}
    
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
