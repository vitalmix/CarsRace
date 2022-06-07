package com.vitalii.control;

import com.vitalii.model.Car;
import com.vitalii.model.CarImpl;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.vitalii.utils.Constants.CARS_TO_RACE;
import static com.vitalii.utils.Constants.DISTANCE_TO_RACE;

public class RaceManager {

    private Group root;
    private ArrayList<Car> carsToRace;
    private CarImpl carImpl;
    private String winner;

    private boolean isRace = false;

    CountDownLatch countDownLatch;

    public RaceManager(Group root) {
        this.root = root;

        carImpl = new CarImpl(CARS_TO_RACE, root);
        carsToRace = carImpl.getCars();
    }

    public void startRace() {

        final ExecutorService executorService = Executors.newFixedThreadPool(CARS_TO_RACE);

        if (winner != null) {
            carImpl.reload();
            winner = null;
        }

        final Object monitor = new Object();
        isRace = true;

        countDownLatch = new CountDownLatch(CARS_TO_RACE);

        for (int i = 0; i < CARS_TO_RACE; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                public void run() {

                    Car car = carsToRace.get(index);

                    prepareToRace(car.getName());

                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    while (isRace) {
                        synchronized (monitor) {
                            if (Thread.interrupted()) {
                                System.out.println(car.getName() + " has passed: " + car.getPassedDistance() +
                                        " speed: " + car.getSpeed());
                                break;
                            }
                            if (car.getPassedDistance() >= DISTANCE_TO_RACE) {
                                if (winner == null) {
                                    winner = car.getName();
                                    isRace = false;
                                    System.out.println(winner + " is the Winner!");
                                    System.out.println("Passed distance: " + car.getPassedDistance());
                                    executorService.shutdownNow();
                                }
                                System.out.println(car.getName() + " has passed: " + car.getPassedDistance() +
                                        " speed: " + car.getSpeed());
                                break;
                            }
                        }
                        car.move();
                        try {
                            Thread.sleep(15);
                        } catch (InterruptedException e) {
                            System.out.println(car.getName() + " has passed: " + car.getPassedDistance() +
                                  " speed: " + car.getSpeed());
                            break;
                        }
                    }
                }
            });
        }

        executorService.shutdown();
    }

    private void prepareToRace(String name) {

        int timeToPrepare = carImpl.getRandNumber(1000,3000);

        try {
            Thread.sleep(timeToPrepare);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        countDownLatch.countDown();

        System.out.println(name + " is ready!");
    }
}
