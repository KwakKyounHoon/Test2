package com.obigo.tmusimulator.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.obigo.tmusimulator.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarInfoFragment extends Fragment {


    public CarInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_info, container, false);
    }

}
