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
                path("login", () -> {
                    get(FrontController::getLogin);
                });

                path("profile", () -> {
                    get(FrontController::getProfile);
                });

                path("user", () -> {
                    get(FrontController::getUser);
                    path("[id]", () -> {
                        get(FrontController::getUserId);
                        post(FrontController::postUserId);
                        put(FrontController::postUserId);
                        patch(FrontController::postUserId);
                        delete(FrontController::deleteUserId);
                    });
                });

                path("dashboard", () -> {
                    get(FrontController::getDashboard);
                });

                path("admin", () -> {
                    get(FrontController::getAdmin);
                });


                path("entity", () -> {
                    get(FrontController::getEntity);
                    path("{id}", () -> {
                        get(FrontController::getEntityId);
                        post(FrontController::postEntityId);
                        put(FrontController::putEntityId);
                        patch(FrontController::patchEntityId);
                        delete(FrontController::deleteEntityId);
                        path("feature", () -> {
                            get(FrontController::getEntityIdFeature);
                            post(FrontController::postEntityIdFeature);
                            put(FrontController::putEntityIdFeature);
                            patch(FrontController::patchEntityIdFeature);
                            delete(FrontController::deleteEntityIdFeature);
                            path("{key}", () -> {
                                get(FrontController::getEntityIdFeatureKey);
                                post(FrontController::postEntityIdFeatureKey);
                                put(FrontController::putEntityIdFeatureKey);
                                patch(FrontController::patchEntityIdFeatureKey);
                                delete(FrontController::deleteEntityIdFeatureKey);

                            });
                        });
                    });
                    path("feature", () -> {
                        path("{key}", () -> {
                            get(FrontController::getEntityFeatureKey);
                            post(FrontController::postEntityFeatureKey);
                            put(FrontController::putEntityFeatureKey);
                            patch(FrontController::patchEntityFeatureKey);
                            delete(FrontController::deleteEntityFeatureKey);
                        });
                    });
                });

                path("feature", () -> {
                   get(FrontController::getFeature);
                   post(FrontController::postFeature);
                   put(FrontController::putFeature);
                   patch(FrontController::patchFeature);
                   delete(FrontController::deleteFeature);
                    path("{key}", () -> {
                        get(FrontController::getFeatureKey);
                        post(FrontController::postFeatureKey);
                        put(FrontController::putFeatureKey);
                        patch(FrontController::patchFeatureKey);
                        delete(FrontController::deleteFeatureKey);
                    });
                });

                path("search", () -> {
                    path("{key}", () -> {
                        path("entity", () -> {
                            path("{id}", () -> {
                                get(FrontController::getSearchKeyEntityId);
                                post(FrontController::postSearchKeyEntityId);
                                put(FrontController::putSearchKeyEntityId);
                                patch(FrontController::patchSearchKeyEntityId);
                                delete(FrontController::deleteSearchKeyEntityId);
                            });
                        });
                    });
                });
            }).start(3001);
    }
}
