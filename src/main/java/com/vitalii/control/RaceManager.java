package com.vitalii.control;

import com.vitalii.model.Car;
import com.vitalii.model.CarImpl;

import java.util.ArrayList;

public class RaceManager {

    private ArrayList<Car> carsToRace = new CarImpl(10).getCars();

    public RaceManager() {


    }
}
