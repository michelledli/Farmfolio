package com.iloveyou;

import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(/*config*/)
            .get("/", ctx -> ctx.result("Hello World"))
            .start(7070);

        Javalin mock = Javalin.create()
            .routes(() -> {
                path("entity", () -> {
                    path("{id}", () -> {
                        get(ctx -> ctx.json(
                                Mock.entity(ctx.pathParam("id"))));
                        path("feature", () -> {
                            get(ctx -> ctx.json(
                                    Mock.entityFeature(ctx.pathParam("id"))));
                            path("{key}", () -> {
                                get(ctx -> ctx.json(
                                        Mock.entityFeature(ctx.pathParam("id"), ctx.pathParam("key"))));
                            });
                        });
                    });
                });
                path("feature", () -> {
                    path("{id}", () -> {
                        get(ctx -> ctx.json(
                            Mock.feature(ctx.pathParam("id"))
                        ));
                    });
                });
            })
            .start(3001);
    }
}
