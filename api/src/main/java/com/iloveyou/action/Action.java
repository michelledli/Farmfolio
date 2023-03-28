package com.iloveyou.action;

import io.javalin.http.Handler;
import com.iloveyou.domain.service.Service;
import com.iloveyou.responder.Responder;

// TODO: Change T to be an interface for domain objects...
//       Sould be json-able, html-able, etc.

public abstract class Action<T> implements Handler {
    Service<? extends Service<T>> service;
    Responder<? extends Responder<T>> responder;

    public Action(Service<? extends Service<T>> service, 
                  Responder<? extends Responder<T>> responder) {
        this.service = service;
        this.responder = responder;
    }
}