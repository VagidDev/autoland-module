package com.college.view.core;

import javafx.stage.Stage;

public class SceneRouterService {
    private static SceneRouter sceneRouter;

    public static void build(Stage stage, double width, double height) {
        SceneRouterService.sceneRouter = new SceneRouter(stage, width, height);
    }

    public static SceneRouter getSceneRouter() {
        return sceneRouter;
    }
}
