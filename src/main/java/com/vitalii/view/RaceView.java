package com.vitalii.view;

import com.vitalii.control.RaceManager;
import com.vitalii.utils.Constants;
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

public class RaceView extends Application {

    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("CarsRace!");

        Button button = new Button("Start the Race!");

        Group root = new Group();

        addRoads(root);

        Line finishLine = new Line();
        finishLine.setStartX(750);
        finishLine.setStartY(10);
        finishLine.setEndX(750);
        finishLine.setEndY(550);
        finishLine.setStrokeWidth(5);
        finishLine.setStroke(Color.WHITE);
        root.getChildren().add(finishLine);

        final RaceManager raceManager = new RaceManager(root);

        Scene scene = new Scene(root, 835, 600, Color.LIGHTSKYBLUE);

        button.setLayoutX(350);
        button.setLayoutY(560);

        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                raceManager.startRace();
            }
        });

        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("X: " + mouseEvent.getX());
            }
        });

        root.getChildren().add(button);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void addRoads(Group root) {

        int step = 40;

        for (int i = 0; i < Constants.CARS_TO_RACE; i++) {

            Line asphaltRoad = new Line(Constants.ASPHALT1_ROAD_START, step, Constants.ASPHALT1_ROAD_FINISH, step);
            asphaltRoad.setStrokeWidth(20);
            asphaltRoad.setStroke(Color.rgb(35, 27, 19));

            Line offRoad = new Line(Constants.OFF_ROAD_START, step, Constants.OFF_ROAD_FINISH, step);
            offRoad.setStrokeWidth(20);
            offRoad.setStroke(Color.rgb(205, 129, 47));

            Line tunnel = new Line(Constants.TUNNEL_ROAD_START, step, Constants.TUNNEL_ROAD_FINISH, step);
            tunnel.setStrokeWidth(20);
            tunnel.setStroke(Color.GRAY);

            Line asphaltRoad2 = new Line(Constants.ASPHALT2_ROAD_START, step, Constants.ASPHALT2_ROAD_FINISH, step);
            asphaltRoad2.setStrokeWidth(20);
            asphaltRoad2.setStroke(Color.rgb(35, 27, 19));

            step += 50;

            root.getChildren().add(asphaltRoad);
            root.getChildren().add(offRoad);
            root.getChildren().add(tunnel);
            root.getChildren().add(asphaltRoad2);
        }
    }
}
