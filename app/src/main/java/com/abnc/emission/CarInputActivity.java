package com.abnc.emission;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class CarInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_input);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ArrayList<Car> cars = new ArrayList<>(0);

        try{
            InputStream carStream = getAssets().open("carData.json");
            cars = DataParser.readJSON(carStream);
        } catch (IOException oops){
            Log.e("FileError", oops.toString());
        }

        ArrayList<String> make = new ArrayList<>(0);
        ArrayList<String> model = new ArrayList<>(0);
        ArrayList<String> year = new ArrayList<>(0);

        for(Car car : cars){
            make.add(car.getMake());
            model.add(car.getModel());
            year.add(Integer.toString(car.getYear()));
        }

        ArrayAdapter<String> makeAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, make);
        ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, model);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, year);

        ((Spinner)findViewById(R.id.makeSpinner)).setAdapter(makeAdapter);
        ((Spinner)findViewById(R.id.modelSpinner)).setAdapter(modelAdapter);
        ((Spinner)findViewById(R.id.yearSpinner)).setAdapter(yearAdapter);

    }

}
