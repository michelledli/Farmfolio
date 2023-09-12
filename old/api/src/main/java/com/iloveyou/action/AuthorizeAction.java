package com.iloveyou.action;

import java.util.UUID;

import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;

import com.iloveyou.domain.service.UserService;

public class AuthorizeAction implements Action {
    UserService service;

    public AuthorizeAction(UserService service) {
        this.service = service;
    }

    public void invoke(Context context) {
        String token = context.header("Authorization");
        String id = context.header("X-User-ID");

        if(token == null || id == null) { throw new ForbiddenResponse(); }

        if(!service.authorize(token, UUID.fromString(id))) { throw new ForbiddenResponse(); }
    }
}

