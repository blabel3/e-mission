package com.abnc.emission;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class CarInputActivity extends AppCompatActivity {
    private Spinner spinner1, spinner2, spinner3;
    private Button btnSubmit;

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
        addItemsOnmodelSpinner();
        addItemsOnmakeSpinner();
        addItemsOnyearSpinner();
        addListenerOnButton();
       // addListenerOnSpinnerItemSelection();

    }
    public void addItemsOnmakeSpinner() {

        spinner1 = (Spinner) findViewById(R.id.makeSpinner);
        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }
    public void addItemsOnmodelSpinner() {

        spinner2 = (Spinner) findViewById(R.id.modelSpinner);
        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }
    public void addItemsOnyearSpinner() {

        spinner3 = (Spinner) findViewById(R.id.yearSpinner);
        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(dataAdapter);
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

        spinner1 = (Spinner) findViewById(R.id.makeSpinner);
        spinner2 = (Spinner) findViewById(R.id.modelSpinner);
        btnSubmit = (Button) findViewById(R.id.sbutton);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(CarInputActivity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : " + String.valueOf(spinner1.getSelectedItem()) +
                                "\nSpinner 2 : " + String.valueOf(spinner2.getSelectedItem())+
                                "\nSpinner 2 : " + String.valueOf(spinner2.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }

        });

    }
}
