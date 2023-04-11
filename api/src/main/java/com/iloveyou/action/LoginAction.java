package com.iloveyou.action;

import com.iloveyou.responder.AuthenticationResponder;
import com.iloveyou.responder.UserResponder;
import com.iloveyou.domain.service.UserService;
import com.iloveyou.domain.AuthenticationRequest;
import com.iloveyou.domain.AuthenticationResponse;

import io.javalin.http.Context;

public class LoginAction implements Action {
    UserService service;
    AuthenticationResponder responder;

    public LoginAction(UserService service, AuthenticationResponder responder) {
        this.service = service;
        this.responder = responder;
    }

    public void invoke(Context context) {
        AuthenticationRequest authenticationRequest = context.bodyAsClass(AuthenticationRequest.class);
        AuthenticationResponse authenticationResponse = service.login(authenticationRequest);
        responder.response(context, authenticationResponse);
    }
}
