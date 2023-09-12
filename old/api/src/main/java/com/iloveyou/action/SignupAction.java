package com.iloveyou.action;

import io.javalin.http.Context;

import com.iloveyou.domain.service.UserService;
import com.iloveyou.responder.AuthenticationResponder;
import com.iloveyou.domain.AuthenticationRequest;
import com.iloveyou.domain.AuthenticationResponse;

public class SignupAction implements Action {
    UserService service;
    AuthenticationResponder responder;

    public SignupAction(UserService service, AuthenticationResponder responder) {
        this.service = service;
        this.responder = responder;
    }

    public void invoke(Context context) {
        AuthenticationRequest authenticationRequest = context.bodyAsClass(AuthenticationRequest.class);
        AuthenticationResponse authenticationResponse = service.signup(authenticationRequest);
        responder.response(context, authenticationResponse);
    }
}

