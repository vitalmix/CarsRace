package com.vitalii.control;

import com.vitalii.model.Car;
import com.vitalii.model.CarImpl;
import javafx.scene.Group;

import java.util.ArrayList;

public class RaceManager {

    private Group root;
    private final int CARS_TO_RACE = 10;
    private final int DISTANCE = 2500;
    private ArrayList<Car> carsToRace = new CarImpl(10).getCars();

    private boolean isRace = false;

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

    public void startRace() {

        isRace = true;

        System.out.println("The race has started!");

        new Thread(new Runnable() {
            public void run() {
                while (isRace) {
                    for (Car car : carsToRace) {
                        car.move();
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (car.getPassedDistance() >= DISTANCE) {
                            isRace = false;
                            System.out.println(car.getName() + " is the Winner!");
                            break;
                        }
                    }
                }
            }
        }).start();
    }
}
