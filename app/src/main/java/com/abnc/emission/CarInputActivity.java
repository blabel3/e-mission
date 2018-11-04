package com.abnc.emission;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class CarInputActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button btnSubmit;

    Context mContext = this;

    ArrayList<Car> cars;
    ArrayList<ElectricCar> eCars;

    ArrayAdapter<String> makeAdapter;
    ArrayAdapter<String> modelAdapter;
    ArrayAdapter<String> yearAdapter;

    ArrayAdapter<String> eMakeAdapter;
    ArrayAdapter<String> eModelAdapter;
    ArrayAdapter<String> eYearAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_input);

        cars = new ArrayList<>(0);

        eCars = new ArrayList<>(0);

        try{
            InputStream carStream = getAssets().open("carData.json");
            cars = DataParser.readJSON(carStream);
            carStream.close();
            InputStream eCarStream = getAssets().open("eCarData.json");
            eCars = DataParser.readJSON2(eCarStream);
        } catch (IOException oops){
            Log.e("FileError", oops.toString());
        }

        //GAS CARS
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

        final Spinner makeSpinner = (Spinner)findViewById(R.id.makeSpinner);
        makeSpinner.setAdapter(makeAdapter);
        makeSpinner.setOnItemSelectedListener(this);
        final Spinner modelSpinner = (Spinner)findViewById(R.id.modelSpinner);
        modelSpinner.setAdapter(modelAdapter);
        modelSpinner.setOnItemSelectedListener(this);
        final Spinner yearSpinner = (Spinner)findViewById(R.id.yearSpinner);
        yearSpinner.setAdapter(yearAdapter);
        yearSpinner.setOnItemSelectedListener(this);

        //ELECTRIC CARS
        ArrayList<String> eMake = new ArrayList<>(0);
        ArrayList<String> eModel = new ArrayList<>(0);
        ArrayList<String> eYear = new ArrayList<>(0);

        for(ElectricCar eCar : eCars){
            eMake.add(eCar.getMake());
            eModel.add(eCar.getModel());
            eYear.add(Integer.toString(eCar.getYear()));
        }

        Set<String> eMakeSet = new HashSet<>();
        eMakeSet.addAll(eMake);
        eMake.clear();
        eMake.addAll(eMakeSet);

        Set<String> eModelSet = new HashSet<>();
        eModelSet.addAll(eModel);
        eModel.clear();
        eModel.addAll(eModelSet);

        Set<String> eYearSet = new HashSet<>();
        eYearSet.addAll(eYear);
        eYear.clear();
        eYear.addAll(eYearSet);

        eMakeAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, eMake);
        eModelAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, eModel);
        eYearAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, eYear);

        final Spinner eMakeSpinner = (Spinner)findViewById(R.id.electricMakeSpinner);
        eMakeSpinner.setAdapter(eMakeAdapter);
        eMakeSpinner.setOnItemSelectedListener(this);
        final Spinner eModelSpinner = (Spinner)findViewById(R.id.electricModelSpinner);
        eModelSpinner.setAdapter(eModelAdapter);
        eModelSpinner.setOnItemSelectedListener(this);
        final Spinner eYearSpinner = (Spinner)findViewById(R.id.electricYearSpinner);
        eYearSpinner.setAdapter(eYearAdapter);
        eYearSpinner.setOnItemSelectedListener(this);

        btnSubmit = (Button) findViewById(R.id.sbutton);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Car theOne = new Car("Blake", "Kart", 2018, 195);
                ElectricCar theTwo = new ElectricCar("eBlake", "Bike", 2048, 151, 420);

                for(Car car : cars){
                    if( makeSpinner.getSelectedItem().equals(car.getMake()) &&
                            modelSpinner.getSelectedItem().equals(car.getModel()) ){
                        theOne = car;
                    }
                }

                for(ElectricCar eCar : eCars){
                    if( eMakeSpinner.getSelectedItem().equals(eCar.getMake()) &&
                            eModelSpinner.getSelectedItem().equals(eCar.getModel()) ){
                        theTwo = eCar;
                    }
                }

                Log.e("VROOOOOM", theOne.toString());
                Log.e("VROOOOOM", theTwo.toString());

                Bundle carBundle = new Bundle();
                carBundle.putString("make", theOne.getMake());
                carBundle.putString("model", theOne.getModel());
                carBundle.putInt("year", theOne.getYear());
                carBundle.putInt("mpg", theOne.getMPG());

                Log.e("carmake", theOne.getMake());

                carBundle.putString("eMake", theTwo.getMake());
                carBundle.putString("eModel", theTwo.getModel());
                carBundle.putInt("eYear", theTwo.getYear());
                carBundle.putInt("empg", theTwo.getMPG());
                carBundle.putInt("price", theTwo.getPricePoint());

                Intent goToMain = new Intent(mContext, MainActivity.class);

                goToMain.putExtra("Cars", carBundle);
                startActivity(goToMain);

            }

        });

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

            case R.id.electricMakeSpinner:

                String eMake = eMakeAdapter.getItem(pos);

                eModelAdapter.clear();
                eYearAdapter.clear();

                models = new HashSet<>(0);
                years = new HashSet<>(0);


                for(Car car : eCars){
                    if(eMake.equals(car.getMake())){
                        models.add(car.getModel());
                        years.add(Integer.toString(car.getYear()));
                    }
                }

                eModelAdapter.addAll(models);
                eYearAdapter.addAll(years);

                Log.e("Options", "make");


                break;
            case R.id.electricModelSpinner:

                String eModel = eMakeAdapter.getItem(pos);

                eYearAdapter.clear();

                years = new HashSet<>(0);

                for(Car car : eCars){
                    if(eModel.equals((car.getMake()))){
                        years.add(Integer.toString(car.getYear()));
                    }
                }

                eYearAdapter.addAll(years);

                break;
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

}
