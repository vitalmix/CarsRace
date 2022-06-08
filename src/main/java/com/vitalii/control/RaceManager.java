package com.vitalii.control;

import com.vitalii.model.Car;
import com.vitalii.model.CarImpl;
import com.vitalii.model.Road;
import com.vitalii.utils.Constants;
import javafx.scene.Group;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.concurrent.*;

public class RaceManager {

    private Group root;
    private ArrayList<Car> carsToRace;
    private CarImpl carImpl;
    private String winner;

    private int finishCarsCounter;

    private Button startButton;

    private CountDownLatch countOfPreparingCars;

    public RaceManager(Group root, Button startButton) {
        this.root = root;
        this.startButton = startButton;

        carImpl = new CarImpl(Constants.CARS_TO_RACE, root);
        carsToRace = carImpl.getCars();
    }

    public void startRace() {

        Object finishCarsCounterMonitor = new Object();

        final ExecutorService executorService = Executors.newFixedThreadPool(Constants.CARS_TO_RACE);

        if (winner != null) {
            carImpl.reload();
            winner = null;
        }

        final Semaphore semaphore = new Semaphore(Constants.CARS_IN_TUNNEL);

        countOfPreparingCars = new CountDownLatch(Constants.CARS_TO_RACE);

        for (int i = 0; i < Constants.CARS_TO_RACE; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                public void run() {

                    Car car = carsToRace.get(index);

                    prepareToRace(car);

                    try {
                        countOfPreparingCars.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    car.setIndicatorOfRaceStage("in race!");

                    long beforeRace = System.currentTimeMillis();

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

                    synchronized (finishCarsCounterMonitor) {
                        finishCarsCounter++;
                        car.setPlaceAfterRace(finishCarsCounter);
                    }

                    car.setIndicatorOfRaceStage(car.getPlaceAfterRace() + " place!");

                    car.setCarInfoAfterRace("CAR INFO: " + getPlace(car.getPlaceAfterRace()) +
                            "Speed: " + car.getSpeed() + "Time: " +
                            (float) (System.currentTimeMillis() - beforeRace) / 1000);
                }
            });
        }

        executorService.shutdown();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    executorService.awaitTermination(20, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                startButton.setDisable(false);
            }
        }).start();
    }

    private void race(Car car) {
        car.move();

        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getPlace(int place) {
        String placeStr = "Place: ";
        if (place == 1) {
            return "WINNER!!! THE 1ST ONE! ";
        }

        if (place == 2) {
            return "THE 2nd! ";
        }

        if (place == 3) {
            return "THE 3rd! ";
        }

        return placeStr + place + " ";
    }

    private void prepareToRace(Car car) {

        float timeToPrepare = carImpl.getRandNumber(1000, 3000);

        float oneHundredthOfTime = timeToPrepare / 100;

        for (int i = 0; i < 101; i++) {

            final int index = i;

            car.setIndicatorOfRaceStage(index + "%");

            try {
                Thread.sleep((long) oneHundredthOfTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        countOfPreparingCars.countDown();

    }
}
