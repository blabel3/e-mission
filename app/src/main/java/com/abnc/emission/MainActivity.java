package com.abnc.emission;

import android.app.Activity;
import android.content.IntentSender;
import android.location.Location;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.*;
import androidx.viewpager.widget.ViewPager;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.*;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements DataVisualization.OnFragmentInteractionListener,
        Trip.OnFragmentInteractionListener {

    Activity bonus = this;

    static final int NUM_ITEMS = 3;

    MyAdapter mAdapter;

    ViewPager mPager;

    private Bundle carData;

    private boolean locationsAllGood = false;

    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        carData = getIntent().getBundleExtra("Cars");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        mAdapter = new MyAdapter(fragmentManager);

        mPager = (ViewPager)findViewById(R.id.fragment_holder);
        mPager.setAdapter(mAdapter);

        BottomNavigationView navBar = (BottomNavigationView) findViewById(R.id.main_navigation);
        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()) {
                    case R.id.savings_option:
                        mPager.setCurrentItem(0);
                        return true;
                    case R.id.trip_option:
                        mPager.setCurrentItem(1);
                        return true;
                }

                return false;
            }
        });

        /*Location stuff

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(10);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());



        //Checks that location services are on

        //They're on cool
        task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                // All location settings are satisfied. The client can initialize
                // location requests here.
                // ...
                Log.e("wahhh", "please");

                locationsAllGood = true;

                startLocationUpdates();

            }
        });

        //Services are off, turn them on pls
        task.addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(bonus,
                                0x1);

                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore the error.
                    }
                }
            }
        });

        //THis homie continuously requests location
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    // Update UI with location data

                    Log.v("LOCATION", location.toString());

                    ((TextView)findViewById(R.id.trip_header)).setText(location.toString());
                }
            };
        };

        //((TextView)getActivity().findViewById(R.id.trip_header)).setText(":(");
        Log.e("ayo", "yuh");

        if(locationsAllGood){
            startLocationUpdates();
            //((TextView)getActivity().findViewById(R.id.trip_header)).setText("Yay");
            Log.e("ayo", "YUHHH");

        }

        /*Switch toggle = (Switch)(this).findViewById(R.id.trip_switch);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    Log.e("ayo", "on");
                } else {
                    // The toggle is disabled
                    Log.e("ayo", "off");
                }
            }
        });*/

    }

    private void startLocationUpdates() {
        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                mLocationCallback,
                null /* Looper */);
    }

    public class MyAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments;

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            //return ArrayListFragment.newInstance(position);
            //return fragments.get(position);
            if(position == 0) {
                Log.e("CARMAKE", carData.getString("make"));
                Log.e("CaRmAkE", carData.toString());
                return DataVisualization.newInstance(carData);
            } else {
                return Trip.newInstance(Integer.toString(position), null);

            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
