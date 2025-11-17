package com.example.apptickets.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apptickets.R;
import com.google.android.material.chip.ChipGroup;

public class ListTickets extends Fragment {

    private ChipGroup chipGroup;
    public ListTickets() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_tickets, container, false);

    }
}