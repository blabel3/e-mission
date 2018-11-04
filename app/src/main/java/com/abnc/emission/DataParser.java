package com.abnc.emission;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataParser {

    private int offset;
    private boolean more;

    public DataParser(){
        offset = 0;
        more = true;
    }

    public DataParser(int offset, boolean hasMore){
        this.offset = offset;
        this.more = hasMore;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public int getOffset() {
        return offset;
    }

    public boolean isMore() {
        return more;
    }

    public static ArrayList<Car> readJSON(InputStream json) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(json, "UTF-8"));

        reader.setLenient(true);

        try {
            reader.beginObject();

            reader.nextName();

            return readCars(reader);

        } finally {
            reader.close();
        }

    }

    public static ArrayList<ElectricCar> readJSON2(InputStream json) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(json, "UTF-8"));

        reader.setLenient(true);

        try {
            reader.beginObject();

            reader.nextName();

            return readElectricCars(reader);

        } finally {
            reader.close();
        }

    }

    public static ArrayList<Car> readCars(JsonReader reader) throws IOException {
        ArrayList<Car> cars = new ArrayList<Car>();

        reader.beginArray();

        while (reader.hasNext()){
            //check that there are more objects to add
            cars.add(readCar(reader));
        }

        reader.endArray();
        return cars;
    }

    public static ArrayList<ElectricCar> readElectricCars(JsonReader reader) throws IOException {
        ArrayList<ElectricCar> cars = new ArrayList<ElectricCar>();

        reader.beginArray();

        while (reader.hasNext()){
            //check that there are more objects to add
            cars.add(readElectricCar(reader));
        }

        reader.endArray();
        return cars;
    }

    public static Car readCar(JsonReader reader) throws IOException {
        //all possible data to get from a deviation as variables

        String make = "";
        String model = "";
        int year = 0;
        int MPG = 0;

        reader.beginObject();
        while(reader.hasNext()){
            String name = reader.nextName();
            switch (name) {
                case "make": make = reader.nextString(); //app does not support buying prints now.
                    break;
                case "model": model = reader.nextString(); //app does not support buying prints now.
                    break;
                case "year": year = reader.nextInt(); //app does not support buying prints now.
                    break;
                case "MPG": MPG = reader.nextInt(); //app does not support buying prints now.
                    break;
                default: reader.skipValue();
                    break;
            }
        }
        reader.endObject();

        return new Car(make, model, year, MPG);

    }

    public static ElectricCar readElectricCar(JsonReader reader) throws IOException {
        //all possible data to get from a deviation as variables

        String make = "";
        String model = "";
        int year = 0;
        int MPG = 0;
        int pricePoint = 0;

        reader.beginObject();
        while(reader.hasNext()){
            String name = reader.nextName();
            switch (name) {
                case "make": make = reader.nextString(); //app does not support buying prints now.
                    break;
                case "model": model = reader.nextString(); //app does not support buying prints now.
                    break;
                case "year": year = reader.nextInt(); //app does not support buying prints now.
                    break;
                case "MPG": MPG = reader.nextInt(); //app does not support buying prints now.
                    break;
                case "pricePoint": pricePoint = reader.nextInt();
                    break;
                default: reader.skipValue();
                    break;
            }
        }
        reader.endObject();

        return new ElectricCar(make, model, year, MPG, pricePoint);

    }

}
