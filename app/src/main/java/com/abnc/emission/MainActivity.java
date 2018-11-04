package com.abnc.emission;

import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.*;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements DataVisualization.OnFragmentInteractionListener,
        Trip.OnFragmentInteractionListener {

    static final int NUM_ITEMS = 3;

    MyAdapter mAdapter;

    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        /*FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        DataVisualization dataVisFragment = new DataVisualization();
        fragmentTransaction.add(R.id.fragment_holder, dataVisFragment);
        fragmentTransaction.commit();    FOR CHANGING FRAGMENTS*/

        mAdapter = new MyAdapter(getSupportFragmentManager());

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


    }

    public static class MyAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments;

        public MyAdapter(FragmentManager fm) {
            super(fm);

            DataVisualization dataScreen = DataVisualization.newInstance("test", "testt");
            Trip tripScreen = Trip.newInstance("haha", "herherh");

            //fragments.add(0, dataScreen);
            //fragments.add(1, tripScreen);

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
                return new DataVisualization();
            } else {
                return new Trip();
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
