package com.vitalii.control;

import com.vitalii.model.Car;
import com.vitalii.model.CarImpl;
import com.vitalii.model.Road;
import com.vitalii.utils.Constants;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class RaceManager {

    private Group root;
    private ArrayList<Car> carsToRace;
    private CarImpl carImpl;
    private String winner;

    private CountDownLatch countDownLatch;

    public RaceManager(Group root) {
        this.root = root;

        carImpl = new CarImpl(Constants.CARS_TO_RACE, root);
        carsToRace = carImpl.getCars();
    }

    public void startRace() {

        final ExecutorService executorService = Executors.newFixedThreadPool(Constants.CARS_TO_RACE);

        if (winner != null) {
            carImpl.reload();
            winner = null;
        }

        final Semaphore semaphore = new Semaphore(2);

        countDownLatch = new CountDownLatch(Constants.CARS_TO_RACE);

        for (int i = 0; i < Constants.CARS_TO_RACE; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                public void run() {

                    Car car = carsToRace.get(index);
                    car.setSemaphore(semaphore);

                    prepareToRace(car.getName());

                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    while (car.getPassedDistance() <= Constants.DISTANCE_TO_RACE) {

                        if (car.getRoad().equals(Road.TUNNEL)) {
                            try {
                                semaphore.acquire();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            while (car.getRoad().equals(Road.TUNNEL)) {
                                race(car);
                            }

                            semaphore.release();
                        } else {
                            race(car);
                        }
                    }

                    if (winner == null) {
                        winner = car.getName();
                        System.out.println(winner + " is the Winner!");
                        System.out.println("Passed distance: " + car.getPassedDistance());
                    }
                    System.out.println(car.getName() + " has passed: " + car.getPassedDistance() +
                            " speed: " + car.getSpeed());
                }
            });
        }

        executorService.shutdown();
    }

    private void race(Car car) {
        car.move();

        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void prepareToRace(String name) {

        int timeToPrepare = carImpl.getRandNumber(1000, 3000);

        try {
            Thread.sleep(timeToPrepare);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        countDownLatch.countDown();

        System.out.println(name + " is ready!");
    }
}
