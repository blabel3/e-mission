package com.abnc.emission;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.abnc.emission.ui.datavisualization.DataVisualization;
import com.emission.abnc.emission.R;

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
