package com.example.anjana.binmaster.HomePage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anjana.binmaster.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodDonation extends Fragment {


    public FoodDonation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_donation, container, false);
    }

}
