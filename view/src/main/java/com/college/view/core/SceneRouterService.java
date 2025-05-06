package com.college.view.core;

import javafx.stage.Stage;

public class SceneRouterService {
    private static SceneRouter sceneRouter;

    public static void build(Stage stage) {
        SceneRouterService.sceneRouter = new SceneRouter(stage);
    }

    public static SceneRouter getSceneRouter() {
        return sceneRouter;
    }
}
