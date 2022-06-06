package com.vitalii.model;

import javafx.application.Platform;

public class Car {

    private String name;
    private int speed;
    private int maxSpeed;
    private int acceleration;
    private CarView carView;
    private int passedDistance = 0;

    private float step;

    public Car(String name, int speed, int maxSpeed, int acceleration, CarView carView) {
        this.name = name;
        this.speed = speed;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.carView = carView;
    }

    public void accelerate() {
        if (speed < maxSpeed) {
            speed += acceleration;
        }
    }

    public void move() {
        accelerate();

        step = (float) speed / 70;

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    carView.getImageView().setX(carView.getImageView().getX() + step);
                }
            });
            passedDistance = (int) carView.getImageView().getX();

    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public CarView getCarView() {
        return carView;
    }

    public int getPassedDistance() {
        return passedDistance;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public void setPassedDistance(int passedDistance) {
        this.passedDistance = passedDistance;
    }
}
