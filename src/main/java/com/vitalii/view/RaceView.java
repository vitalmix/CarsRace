package com.vitalii.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RaceView extends Application {

    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("HBox Experiment 1");

        Button button = new Button("My Button");

        Scene scene = new Scene(button, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
