package com.iloveyou;

import io.javalin.http.Context;

import com.iloveyou.action.SignupAction;
import com.iloveyou.action.AuthorizeAction;
import com.iloveyou.action.LoginAction;
import com.iloveyou.responder.AuthenticationResponder;
import com.iloveyou.domain.service.UserService;
import com.iloveyou.domain.persistence.UserDAO;
import com.iloveyou.domain.persistence.Database;

class FrontController {
    UserDAO userDao;
    UserService userService;

    AuthenticationResponder authenticationResponder;
    
    SignupAction signupAction;
    AuthorizeAction authorizeAction;
    LoginAction loginAction;
    
    public FrontController(Database database) {
        userDao = new UserDAO(database);
        userService = new UserService(userDao);

        authenticationResponder = new AuthenticationResponder();
        
        signupAction = new SignupAction(userService, authenticationResponder);
        authorizeAction = new AuthorizeAction(userService);
        loginAction = new LoginAction(userService, authenticationResponder);
    }

    public void getRoot(Context ctx) {}

    public void getLogin(Context ctx) {}
    public void postLogin(Context ctx) { loginAction.invoke(ctx); }

    public void getSignup(Context ctx) {}
    public void postSignup(Context ctx) { signupAction.invoke(ctx); }

    public void getProfile(Context ctx) {}

    public void getUser(Context ctx) {}

    public void getUserId(Context ctx) {}
    public void postUserId(Context ctx) {}
    public void putUserId(Context ctx) {}
    public void patchUserId(Context ctx) {}
    public void deleteUserId(Context ctx) {}

    public void getDashboard(Context ctx) {}

    public void getAdmin(Context ctx) {}

    public void getAuthorize(Context ctx) { authorizeAction.invoke(ctx); }

    public void getEntity(Context ctx) {}
    public void postEntity(Context ctx) {}

    public void getEntityId(Context ctx) {}
    public void postEntityId(Context ctx) {}
    public void putEntityId(Context ctx) {}
    public void patchEntityId(Context ctx) {}
    public void deleteEntityId(Context ctx) {}

    public void getEntityIdFeature(Context ctx) {}
    public void postEntityIdFeature(Context ctx) {}
    public void putEntityIdFeature(Context ctx) {}
    public void patchEntityIdFeature(Context ctx) {}
    public void deleteEntityIdFeature(Context ctx) {}

    public void getEntityIdFeatureKey(Context ctx) {}
    public void postEntityIdFeatureKey(Context ctx) {}
    public void putEntityIdFeatureKey(Context ctx) {}
    public void patchEntityIdFeatureKey(Context ctx) {}
    public void deleteEntityIdFeatureKey(Context ctx) {}

    public void getEntityFeatureKey(Context ctx) {}
    public void postEntityFeatureKey(Context ctx) {}
    public void putEntityFeatureKey(Context ctx) {}
    public void patchEntityFeatureKey(Context ctx) {}
    public void deleteEntityFeatureKey(Context ctx) {}

    public void getFeature(Context ctx) {}
    public void postFeature(Context ctx) {}
    public void putFeature(Context ctx) {}
    public void patchFeature(Context ctx) {}
    public void deleteFeature(Context ctx) {}

    public void postFeatureId(Context ctx) {}
    public void putFeatureId(Context ctx) {}
    public void patchFeatureId(Context ctx) {}
    public void deleteFeatureId(Context ctx) {}

    public void getFeatureKey(Context ctx) {}
    public void postFeatureKey(Context ctx) {}
    public void putFeatureKey(Context ctx) {}
    public void patchFeatureKey(Context ctx) {}
    public void deleteFeatureKey(Context ctx) {}

    public void getSearch(Context ctx) {}
    public void postSearch(Context ctx) {}
    public void putSearch(Context ctx) {}
    public void patchSearch(Context ctx) {}
    public void deleteSearch(Context ctx) {}
}