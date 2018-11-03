package com.emission.abnc.e_mission.ui.datavisualization;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.emission.abnc.e_mission.R;

public class DataVisualization extends Fragment {

    private DataVisualizationViewModel mViewModel;

    public static DataVisualization newInstance() {
        return new DataVisualization();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.data_visualization_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DataVisualizationViewModel.class);
        // TODO: Use the ViewModel
    }

}
