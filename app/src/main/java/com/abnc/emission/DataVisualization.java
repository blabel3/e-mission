package com.abnc.emission;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DataVisualization.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DataVisualization#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataVisualization extends androidx.fragment.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    // TODO: Rename and change types of parameters
    private Bundle mParam1;

    private OnFragmentInteractionListener mListener;

    public DataVisualization() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataVisualization.
     */
    // TODO: Rename and change types and number of parameters
    public static DataVisualization newInstance(Bundle param1) {
        DataVisualization fragment = new DataVisualization();
        Bundle args = new Bundle();
        args.putBundle(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getBundle(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inf = inflater.inflate(R.layout.data_visualization_fragment, container, false);

        Log.e("DATAAAAA", mParam1.toString());

        final Car gasCar = new Car(mParam1.getString("make"),
                mParam1.getString("model"),
                mParam1.getInt("year"),
                mParam1.getInt("mpg"));
        final ElectricCar eCar = new ElectricCar(mParam1.getString("eMake"),
                mParam1.getString("eModel"),
                mParam1.getInt("eYear"),
                mParam1.getInt("empg"),
                mParam1.getInt("price"));



        TextView subtitle = (TextView) inf.findViewById(R.id.subtext_car);

        final TextView daySavingsView = inf.findViewById(R.id.day_savings);
        final TextView yearSavingsView = inf.findViewById(R.id.year_savings);
        final TextView monthSavingsView = inf.findViewById(R.id.month_savings);

        final TextView mileage = inf.findViewById(R.id.mileage);

        SeekBar distanceBar = inf.findViewById(R.id.distance_slider);

        distanceBar.setMax(100);
        distanceBar.setMin(1);
        distanceBar.setProgress(65);

        distanceBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int distance = i;

                double gasPrice = 2.8 * distance / gasCar.getMPG();
                double electricPrice = 1.15 * distance / eCar.getMPG();

                String daySavings = String.format(Locale.getDefault(), "%.2f", (gasPrice - electricPrice));
                String yearSavings = String.format(Locale.getDefault(), "%.2f", (gasPrice - electricPrice) * 365);
                String monthSavings = String.format(Locale.getDefault(), "%.2f", (gasPrice - electricPrice) * 365 / 12);

                daySavingsView.setText(daySavings);
                yearSavingsView.setText(yearSavings);
                monthSavingsView.setText(monthSavings);
                mileage.setText(String.format(Locale.getDefault(), "%d", distance));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        String carDescripton = "if you drove the " + eCar.getModel() + " instead of the " + gasCar.getModel();
        subtitle.setText(carDescripton);

        return inf;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
