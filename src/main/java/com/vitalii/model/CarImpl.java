package com.vitalii.model;

import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class CarImpl {

    private ArrayList<Car> cars = new ArrayList<Car>();

    public CarImpl(int amountOfCarsToImpl, Group root) {

        int step = 20;

        for (int i = 0; i < amountOfCarsToImpl; i++) {

            Text percentOfPreparing = new Text("0%");
            percentOfPreparing.setFont(new Font(20));

            Car buffCar = new Car("Car " + (i + 1), (i + 1), getRandNumber(200, 250), getRandNumber(10, 20),
                    new CarView(), percentOfPreparing);

            percentOfPreparing.setX(755);
            percentOfPreparing.setY(step + 25);
            buffCar.getCarView().getImageView().setX(0);
            buffCar.getCarView().getImageView().setY(step);
            root.getChildren().add(buffCar.getCarView().getImageView());
            root.getChildren().add(percentOfPreparing);

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
