package com.vitalii.model;

import javafx.scene.Group;

import java.util.ArrayList;

public class CarImpl {

    private ArrayList<Car> cars = new ArrayList<Car>();

    public CarImpl(int amountOfCarsToImpl, Group root) {

        int step = 20;

        for (int i = 0; i < amountOfCarsToImpl; i++) {

            Car buffCar = new Car("Car " + (i + 1), (i + 1), getRandNumber(200, 250), getRandNumber(10, 20),
                    new CarView());

            buffCar.getCarView().getImageView().setX(0);
            buffCar.getCarView().getImageView().setY(step);
            root.getChildren().add(buffCar.getCarView().getImageView());

            step += 50;

            cars.add(buffCar);

        }
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void reload() {
        for (Car car : cars) {
            car.setMaxSpeed(getRandNumber(200,250));
            car.setAcceleration(getRandNumber(10,15));
            car.setPassedDistance(0);
            car.getCarView().getImageView().setX(0);
        }
    }

    public int getRandNumber(int min, int max) {

        return (int) ((Math.random() * (max - min) + min));
    }
}
