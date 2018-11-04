package com.abnc.emission;

public class ElectricCar extends Car {

    private int pricePoint;

    ElectricCar(){}

    ElectricCar(String make, String model, int year, int MPG, int pricePoint){
        super(make, model, year, MPG);

        this.pricePoint = pricePoint;
    }

    public int getPricePoint() {
        return pricePoint;
    }

    public void setPricePoint(int pricePoint) {
        this.pricePoint = pricePoint;
    }
}
