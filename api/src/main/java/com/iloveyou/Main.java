package com.iloveyou;

import io.javalin.Javalin;
import io.javalin.http.Context;
import static io.javalin.apibuilder.ApiBuilder.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import com.iloveyou.action.SignupAction;
import com.iloveyou.action.AuthorizeAction;
import com.iloveyou.action.LoginAction;
import com.iloveyou.responder.AuthenticationResponder;
import com.iloveyou.domain.service.UserService;
import com.iloveyou.domain.persistence.UserDAO;

public class Main {
    public static void main(String[] args) {
        UserDAO userDao = new UserDAO();
        UserService userService = new UserService(userDao);
        AuthenticationResponder authenticationResponder = new AuthenticationResponder();
        SignupAction signupAction = new SignupAction(userService, authenticationResponder);
        AuthorizeAction authorizeAction = new AuthorizeAction(userService);
        LoginAction loginAction = new LoginAction(userService, authenticationResponder);

        Javalin app = Javalin.create().start(7000);
        app.post("/signup", signupAction::invoke);
        app.post("/login", loginAction::invoke);

        app.before("/authorize/*", authorizeAction::invoke);
        app.get("/authorize/test", (Context ctx) -> { ctx.result("AUTHORIZED"); });



    }
}
