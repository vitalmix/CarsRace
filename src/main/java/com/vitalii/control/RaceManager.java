package com.vitalii.control;

import com.vitalii.model.Car;
import com.vitalii.model.CarImpl;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RaceManager {

    private Group root;
    private final int CARS_TO_RACE = 4;
    private final int DISTANCE = 600;
    private ArrayList<Car> carsToRace;
    private String winner;

    private boolean isRace = false;

    public RaceManager(Group root) {
        this.root = root;

        carsToRace = new CarImpl(CARS_TO_RACE, root).getCars();
    }

    public void startRace() {

        final Object monitor = new Object();
        isRace = true;

        System.out.println("The race has started!");

        final ExecutorService executorService = Executors.newFixedThreadPool(CARS_TO_RACE);

        for (int i = 0; i < CARS_TO_RACE; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                public void run() {
                    Car car = carsToRace.get(index);
                    while (isRace) {
                        synchronized (monitor) {
                            if (car.getPassedDistance() >= DISTANCE) {
                                if (winner == null) {
                                    winner = car.getName();
                                    isRace = false;
                                    System.out.println(winner + " is the Winner!");
                                    System.out.println("Passed distance: " + car.getPassedDistance());
                                    executorService.shutdownNow();
                                }
                                System.out.println(car.getName() + " has passed: " + car.getPassedDistance());
                                break;
                            }
                        }
                        car.move();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            System.out.println(car.getName() + " has passed: " + car.getPassedDistance());
                            break;
                        }
                    }
                }
            });
        }

        executorService.shutdown();
    }
}
