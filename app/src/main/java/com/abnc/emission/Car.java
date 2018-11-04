package com.abnc.emission;

import androidx.annotation.NonNull;

public class Car {

    private String make;
    private String model;
    private int year;
    private int MPG;

    public Car(){}

    public Car(String make, String model, int year, int MPG){
        this.make = make;
        this.model = model;
        this.year = year;
        this.MPG = MPG;
    }

    public String getMake() {
        return make;
    }

    public int getYear() {
        return year;
    }

    public int getMPG() {
        return MPG;
    }

    public String getModel() {
        return model;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMPG(int MPG) {
        this.MPG = MPG;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @NonNull
    @Override
    public String toString() {
        return "make:" + make + " model: " + model + " year: " + year + " mpg " + MPG;
    }
}
