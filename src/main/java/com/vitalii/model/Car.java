package com.vitalii.model;

public class Car {

    private String name;
    private int speed;
    private int maxSpeed;
    private int acceleration;

    public Car(String name, int speed, int maxSpeed, int acceleration) {
        this.name = name;
        this.speed = speed;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
    }

    public void accelerate() {
        if (speed < maxSpeed) {
            speed += acceleration;
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
}
