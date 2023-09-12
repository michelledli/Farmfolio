package com.iloveyou;

import io.javalin.Javalin;
import io.javalin.http.Context;
import static io.javalin.apibuilder.ApiBuilder.*;
import com.iloveyou.domain.persistence.Database;

public class Main {
    public static void main(String[] args) {
        
        Database database = new Database(Database.DB_URL);
        // database.resetDatabase();

        FrontController frontController = new FrontController(database);

        Javalin app = Javalin.create()
            .routes(() -> {
                path("authorize", () -> {
                    before(frontController::getAuthorize);
                    path("test", () -> {
                        get((Context ctx) -> { ctx.result("AUTHORIZED"); });
                    });
                });

                path("login", () -> {
                    get(frontController::getLogin);
                    post(frontController::postLogin);
                });

                path("signup", () -> {
                    post(frontController::postSignup);
                });

                path("profile", () -> {
                    get(frontController::getProfile);
                });

                path("user", () -> {
                    get(frontController::getUser);
                    path("[id]", () -> {
                        get(frontController::getUserId);
                        post(frontController::postUserId);
                        put(frontController::postUserId);
                        patch(frontController::postUserId);
                        delete(frontController::deleteUserId);
                    });
                });

                path("dashboard", () -> {
                    get(frontController::getDashboard);
                });

                path("admin", () -> {
                    get(frontController::getAdmin);
                });


                path("entity", () -> {
                    get(frontController::getEntity);
                    path("{id}", () -> {
                        get(frontController::getEntityId);
                        post(frontController::postEntityId);
                        put(frontController::putEntityId);
                        patch(frontController::patchEntityId);
                        delete(frontController::deleteEntityId);
                        path("feature", () -> {
                            get(frontController::getEntityIdFeature);
                            post(frontController::postEntityIdFeature);
                            put(frontController::putEntityIdFeature);
                            patch(frontController::patchEntityIdFeature);
                            delete(frontController::deleteEntityIdFeature);
                            path("{key}", () -> {
                                get(frontController::getEntityIdFeatureKey);
                                post(frontController::postEntityIdFeatureKey);
                                put(frontController::putEntityIdFeatureKey);
                                patch(frontController::patchEntityIdFeatureKey);
                                delete(frontController::deleteEntityIdFeatureKey);

                            });
                        });
                    });
                    path("feature", () -> {
                        path("{key}", () -> {
                            get(frontController::getEntityFeatureKey);
                            post(frontController::postEntityFeatureKey);
                            put(frontController::putEntityFeatureKey);
                            patch(frontController::patchEntityFeatureKey);
                            delete(frontController::deleteEntityFeatureKey);
                        });
                    });
                });

                path("feature", () -> {
                    get(frontController::getFeature);
                    post(frontController::postFeature);
                    put(frontController::putFeature);
                    patch(frontController::patchFeature);
                    delete(frontController::deleteFeature);
                    path("{key}", () -> {
                        get(frontController::getFeatureKey);
                        post(frontController::postFeatureKey);
                        put(frontController::putFeatureKey);
                        patch(frontController::patchFeatureKey);
                        delete(frontController::deleteFeatureKey);
                    });
                });

                path("search", () -> {
                    path("{key}", () -> {
                        path("entity", () -> {
                            path("{id}", () -> {
                                get(frontController::getSearch);
                                post(frontController::postSearch);
                                put(frontController::putSearch);
                                patch(frontController::patchSearch);
                                delete(frontController::deleteSearch);
                            });
                        });
                    });
                });
            }).start(7000);
    }
}