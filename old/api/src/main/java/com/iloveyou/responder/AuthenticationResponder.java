package com.iloveyou.responder;

import io.javalin.http.Context;

import com.iloveyou.domain.AuthenticationResponse;

public class AuthenticationResponder implements Responder<AuthenticationResponse> {
    public void response(Context context, AuthenticationResponse authenticationResponse) {
        String response = "{\"id\":\"" + 
                          authenticationResponse.getId().toString() +
                          "\",\"token\":\"" + 
                          authenticationResponse.getToken() +
                          "\"}";
                          
        context.json(authenticationResponse);
    }
}
