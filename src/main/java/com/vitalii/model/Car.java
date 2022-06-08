package com.vitalii.model;

import com.vitalii.utils.Constants;
import javafx.application.Platform;
import javafx.scene.text.Text;

public class Car {

    private String name;
    private int speed;
    private int maxSpeed;
    private int acceleration;
    private CarView carView;
    private int passedDistance = 0;
    private Road road = Road.ASPHALT;

    private Text indicatorOfRaceStage;

    private float step;

    public Car(String name, int speed, int maxSpeed, int acceleration, CarView carView, Text indicatorOfRaceStage) {
        this.name = name;
        this.speed = speed;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.carView = carView;
        this.indicatorOfRaceStage = indicatorOfRaceStage;
    }

    public void accelerate() {
        if (speed < maxSpeed) {
            speed += acceleration;
        }
    }

    public void move() {
        accelerate();

        checkRoad();

        if (road.equals(Road.OFF_ROAD)) {
            speed = (int) (speed * 0.7);
        }

        step = (float) speed / 70;

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                carView.getImageView().setX(carView.getImageView().getX() + step);
            }
        });
        passedDistance = (int) carView.getImageView().getX() + 75;

    }

    private void checkRoad() {
        if (passedDistance >= Constants.OFF_ROAD_START && passedDistance <= Constants.OFF_ROAD_FINISH
                && !road.equals(Road.OFF_ROAD)) {
            road = Road.OFF_ROAD;
        }

        if (passedDistance >= Constants.TUNNEL_ROAD_START && passedDistance <= Constants.TUNNEL_ROAD_FINISH
                && !road.equals(Road.TUNNEL)) {
            road = Road.TUNNEL;
        }

        if (passedDistance >= Constants.ASPHALT2_ROAD_START && passedDistance <= Constants.ASPHALT2_ROAD_FINISH
                && !road.equals(Road.ASPHALT)) {
            road = Road.ASPHALT;
        }
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

    public Road getRoad() {
        return road;
    }

    public Text getIndicatorOfRaceStage() {
        return indicatorOfRaceStage;
    }

    public void setIndicatorOfRaceStage(String indicatorOfRaceStage) {
        this.indicatorOfRaceStage.setText(indicatorOfRaceStage);;
    }
}
