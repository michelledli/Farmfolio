package com.iloveyou.action;

import io.javalin.http.Handler;
import io.javalin.http.Context;
import com.iloveyou.responder.Responder;

// TODO: Change T to be an interface for domain objects...
//       Sould be json-able, html-able, etc.

public interface Action {
    public void invoke(Context context);
}