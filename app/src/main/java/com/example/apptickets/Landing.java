package com.example.apptickets;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Landing extends Fragment {


    private Button btnAcceder;

    public Landing() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_landing, container, false);


        btnAcceder = findViewById(R.id.btnAcceder);

        btnAcceder.setOnClickListener(v -> {

        });

    }
}