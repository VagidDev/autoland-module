package com.college.view.core;

import com.college.view.Application;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.LinkedHashMap;

public class SceneRouter {

    private final Stage stage;
    private final double defaultWidth;
    private final double defaultHeight;
    private final LinkedHashMap<String, AnimationType> scenes;

    public SceneRouter(Stage stage, double defaultWidth, double defaultHeight) {
        this.stage = stage;
        this.defaultWidth = defaultWidth;
        this.defaultHeight = defaultHeight;
        this.scenes = new LinkedHashMap<>();
    }

    public void switchTo(String fxmlPath, AnimationType animationType) {
        Parent oldRoot = stage.getScene() != null ? stage.getScene().getRoot() : null;

        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource(fxmlPath));
            Parent newRoot = loader.load();
            Scene newScene = new Scene(newRoot, defaultWidth, defaultHeight);


            Animation enterAnimation = createEnterAnimation(newRoot, animationType);
            Animation exitAnimation = createExitAnimation(oldRoot, animationType);

            //Temporary solution 'runLater'
            Platform.runLater(() -> {

                if (exitAnimation != null) {
                    exitAnimation.setOnFinished(e -> {
                        stage.setScene(newScene);
                        stage.centerOnScreen();
                        enterAnimation.play();
                    });
                    exitAnimation.play();
                } else {
                    stage.setScene(newScene);
                    stage.centerOnScreen();
                    enterAnimation.play();
                }

                scenes.put(fxmlPath, animationType);
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot load FXML: " + fxmlPath, e);
        }
    }

    public void switchToPreviousScene() {
        scenes.remove(scenes.lastEntry().getKey());
        switchTo(scenes.lastEntry().getKey(), scenes.lastEntry().getValue());
    }

    private Animation createEnterAnimation(Parent root, AnimationType type) {
        Duration duration = Duration.millis(300);

        switch (type) {
            case FADE -> {
                root.setOpacity(0);

                FadeTransition ft = new FadeTransition(duration, root);
                ft.setFromValue(0);
                ft.setToValue(1);

                return ft;
            }
            case ZOOM -> {
                root.setOpacity(0);
                root.setScaleX(0.9);
                root.setScaleY(0.9);

                FadeTransition ft = new FadeTransition(duration, root);
                ft.setFromValue(0);
                ft.setToValue(1);

                ScaleTransition st = new ScaleTransition(duration, root);
                st.setFromX(0.9);
                st.setFromY(0.9);
                st.setToX(1);
                st.setToY(1);

                return new ParallelTransition(ft, st);
            }
            case ROTATE -> {
                root.setOpacity(0);
                root.setRotate(-15);

                FadeTransition ft = new FadeTransition(duration, root);
                ft.setFromValue(0);
                ft.setToValue(1);

                RotateTransition rt = new RotateTransition(duration, root);
                rt.setFromAngle(-15);
                rt.setToAngle(0);

                return new ParallelTransition(ft, rt);
            }
            case SLIDE -> {
                root.setOpacity(0);
                root.setTranslateX(defaultWidth);

                FadeTransition ft = new FadeTransition(duration, root);
                ft.setFromValue(0);
                ft.setToValue(1);

                TranslateTransition tt = new TranslateTransition(duration, root);
                tt.setFromX(defaultWidth);
                tt.setToX(0);

                return new ParallelTransition(ft, tt);
            }
            case SLIDE_REVERSE -> {
                root.setOpacity(0);
                root.setTranslateX(-defaultWidth);

                FadeTransition ft = new FadeTransition(duration, root);
                ft.setFromValue(0);
                ft.setToValue(1);

                TranslateTransition tt = new TranslateTransition(duration, root);
                tt.setFromX(-defaultWidth);
                tt.setToX(0);

                return new ParallelTransition(ft, tt);
            }
            default -> throw new IllegalArgumentException("Unknown animation type");
        }
    }

    private Animation createExitAnimation(Parent root, AnimationType type) {
        if (root == null) return null;

        Duration duration = Duration.millis(300);

        switch (type) {
            case FADE -> {
                FadeTransition ft = new FadeTransition(duration, root);
                ft.setFromValue(1);
                ft.setToValue(0);

                return ft;
            }
            case ZOOM -> {
                FadeTransition ft = new FadeTransition(duration, root);
                ft.setFromValue(1);
                ft.setToValue(0);

                ScaleTransition st = new ScaleTransition(duration, root);
                st.setFromX(1);
                st.setFromY(1);
                st.setToX(0.9);
                st.setToY(0.9);

                return new ParallelTransition(ft, st);
            }
            case ROTATE -> {

                FadeTransition ft = new FadeTransition(duration, root);
                ft.setFromValue(1);
                ft.setToValue(0);

                RotateTransition rt = new RotateTransition(duration, root);
                rt.setFromAngle(0);
                rt.setToAngle(-15);

                return new ParallelTransition(ft, rt);
            }
            case SLIDE -> {
                FadeTransition ft = new FadeTransition(duration, root);
                ft.setFromValue(1);
                ft.setToValue(0);

                TranslateTransition tt = new TranslateTransition(duration, root);
                tt.setFromX(0);
                tt.setToX(-defaultWidth);

                return new ParallelTransition(ft, tt);
            }
            case SLIDE_REVERSE -> {
                FadeTransition ft = new FadeTransition(duration, root);
                ft.setFromValue(1);
                ft.setToValue(0);

                TranslateTransition tt = new TranslateTransition(duration, root);
                tt.setFromX(0);
                tt.setToX(defaultWidth);

                return new ParallelTransition(ft, tt);
            }
            default -> throw new IllegalArgumentException("Unknown animation type");
        }
    }
}
