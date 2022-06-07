package com.vitalii.view;

import com.vitalii.control.RaceManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import static com.vitalii.utils.Constants.CARS_TO_RACE;

public class RaceView extends Application {

    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("CarsRace!");

        Button button = new Button("Start the Race!");

        Group root = new Group();

        addRoads(root);

        final RaceManager raceManager = new RaceManager(root);

        Scene scene = new Scene(root,800, 600, Color.LIGHTSKYBLUE);

        button.setLayoutX(350);
        button.setLayoutY(550);

        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                raceManager.startRace();
            }
        });

        Line finishLine = new Line();
        finishLine.setStartX(600);
        finishLine.setStartY(10);
        finishLine.setEndX(600);
        finishLine.setEndY(550);
        finishLine.setStrokeWidth(5);
        finishLine.setStroke(Color.WHITE);

        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("X: " + mouseEvent.getX());
            }
        });

        root.getChildren().add(finishLine);
        root.getChildren().add(button);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void addRoads(Group root) {

        int step = 40;

        for (int i = 0; i < CARS_TO_RACE; i++) {

            Line asphaltRoad = new Line(0,step,149,step);
            asphaltRoad.setStrokeWidth(20);

            Line offRoad = new Line(169,step,299,step);
            offRoad.setStrokeWidth(20);
            offRoad.setStroke(Color.BROWN);

            Line tunnel = new Line(319,step,399,step);
            tunnel.setStrokeWidth(20);
            tunnel.setStroke(Color.GRAY);

            Line asphaltRoad2 = new Line(419,step,590,step);
            asphaltRoad2.setStrokeWidth(20);

            step += 50;

            root.getChildren().add(asphaltRoad);
            root.getChildren().add(offRoad);
            root.getChildren().add(tunnel);
            root.getChildren().add(asphaltRoad2);
        }
    }
}
