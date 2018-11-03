package com.emission.abnc.e_mission;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.emission.abnc.e_mission.ui.datavisualization.DataVisualization;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DataVisualization.newInstance())
                    .commitNow();
        }
    }
}
