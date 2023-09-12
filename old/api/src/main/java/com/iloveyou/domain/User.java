package com.iloveyou.domain;

import java.util.UUID;

public class User {
    private UUID id;
    private String email;
    private String password;

    public User(UUID id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}