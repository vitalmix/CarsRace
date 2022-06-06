package com.vitalii.view;

import com.vitalii.control.RaceManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RaceView extends Application {

    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("CarsRace!");

        Button button = new Button("Start the Race!");

        Group root = new Group();

        final RaceManager raceManager = new RaceManager(root);

        Scene scene = new Scene(root,800, 600, Color.LIGHTSKYBLUE);

        button.setLayoutX(350);
        button.setLayoutY(550);

        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                raceManager.startRace();
            }
        });

        root.getChildren().add(button);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
