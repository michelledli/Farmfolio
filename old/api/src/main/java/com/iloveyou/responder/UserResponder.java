package com.iloveyou.responder;

import io.javalin.http.Context;

import com.iloveyou.domain.User;

public class UserResponder implements Responder<User> {
    public void response(Context context, User user) {}
}
