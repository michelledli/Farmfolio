package com.iloveyou.responder;

import io.javalin.http.Context;

public interface Responder<T> {
    public void response(Context context, T t);
}
