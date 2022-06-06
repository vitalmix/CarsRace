package com.vitalii.control;

import com.vitalii.model.Car;
import com.vitalii.model.CarImpl;
import javafx.scene.Group;

import java.util.ArrayList;

public class RaceManager {

    private Group root;
    private final int CARS_TO_RACE = 10;
    private ArrayList<Car> carsToRace = new CarImpl(10).getCars();

    public RaceManager(Group root) {
        this.root = root;
        int step = 20;

        for (int i = 0; i < CARS_TO_RACE; i++) {
            carsToRace.get(i).getCarView().getImageView().setX(0);
            carsToRace.get(i).getCarView().getImageView().setY(step);
            root.getChildren().add(carsToRace.get(i).getCarView().getImageView());

            step += 50;
        }
    }
}
