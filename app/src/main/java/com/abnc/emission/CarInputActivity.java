package com.abnc.emission;

import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class CarInputActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button btnSubmit;

    ArrayList<Car> cars;

    ArrayAdapter<String> makeAdapter;
    ArrayAdapter<String> modelAdapter;
    ArrayAdapter<String> yearAdapter;


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

        cars = new ArrayList<>(0);

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

        Set<String> makeSet = new HashSet<>();
        makeSet.addAll(make);
        make.clear();
        make.addAll(makeSet);

        Set<String> modelSet = new HashSet<>();
        modelSet.addAll(model);
        model.clear();
        model.addAll(modelSet);

        Set<String> yearSet = new HashSet<>();
        yearSet.addAll(year);
        year.clear();
        year.addAll(yearSet);

        makeAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, make);
        modelAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, model);
        yearAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, year);


        ((Spinner)findViewById(R.id.makeSpinner)).setAdapter(makeAdapter);
        ((Spinner)findViewById(R.id.makeSpinner)).setOnItemSelectedListener(this);
        ((Spinner)findViewById(R.id.modelSpinner)).setAdapter(modelAdapter);
        ((Spinner)findViewById(R.id.modelSpinner)).setOnItemSelectedListener(this);
        ((Spinner)findViewById(R.id.yearSpinner)).setAdapter(yearAdapter);
        ((Spinner)findViewById(R.id.yearSpinner)).setOnItemSelectedListener(this);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        Set<String> makes;
        Set<String> models;
        Set<String> years;

        switch(parent.getId()){
            case R.id.makeSpinner:

                String make = makeAdapter.getItem(pos);

                modelAdapter.clear();
                yearAdapter.clear();

                models = new HashSet<>(0);
                years = new HashSet<>(0);


                for(Car car : cars){
                    if(make.equals(car.getMake())){
                        models.add(car.getModel());
                        years.add(Integer.toString(car.getYear()));
                    }
                }

                modelAdapter.addAll(models);
                yearAdapter.addAll(years);

                Log.e("Options", "make");


                break;
            case R.id.modelSpinner:

                String model = makeAdapter.getItem(pos);

                yearAdapter.clear();

                years = new HashSet<>(0);

                for(Car car : cars){
                    if(model.equals((car.getMake()))){
                        years.add(Integer.toString(car.getYear()));
                    }
                }

                yearAdapter.addAll(years);

                break;
            case R.id.yearSpinner:

                /*String year = makeAdapter.getItem(pos);

                makeAdapter.clear();
                yearAdapter.clear();

                makes = new HashSet<>(0);
                years = new HashSet<>(0);


                for(Car car : cars){
                    if(year.equals(car.getMake())){
                        makes.add(car.getModel());
                        years.add(Integer.toString(car.getYear()));
                    }
                }

                modelAdapter.addAll(makes);
                yearAdapter.addAll(years);

                break; */
        }

    }

    public void onNothingSelected(AdapterView<?> parent) {

        makeAdapter.clear();
        modelAdapter.clear();
        yearAdapter.clear();

        Set<String> makes = new HashSet<>(0);
        Set<String> models = new HashSet<>(0);
        Set<String> years = new HashSet<>(0);


        for(Car car : cars){
            makes.add(car.getMake());
            models.add(car.getModel());
            years.add(Integer.toString(car.getYear()));
        }

        makeAdapter.addAll(makes);
        modelAdapter.addAll(models);
        yearAdapter.addAll(years);

        // Another interface callback
    }


    //    public void addListenerOnSpinnerItemSelection() {
//        spinner1 = (Spinner) findViewById(R.id.makeSpinner);
//        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
//        spinner2 = (Spinner) findViewById(R.id.modelSpinner);
//        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
//        spinner3 = (Spinner) findViewById(R.id.yearSpinner);
//        spinner3.setOnItemSelectedListener(new CustomOnItemSelectedListener());
//    }
    public void addListenerOnButton() {

        final Spinner spinner1 = (Spinner) findViewById(R.id.makeSpinner);
        final Spinner spinner2 = (Spinner) findViewById(R.id.modelSpinner);
        final Spinner spinner3 = (Spinner) findViewById(R.id.yearSpinner);
        btnSubmit = (Button) findViewById(R.id.sbutton);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(CarInputActivity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : " + String.valueOf(spinner1.getSelectedItem()) +
                                "\nSpinner 2 : " + String.valueOf(spinner2.getSelectedItem())+
                                "\nSpinner 3 : " + String.valueOf(spinner3.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }

        });

    }
}
