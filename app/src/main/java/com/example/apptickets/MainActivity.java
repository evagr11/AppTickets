package com.example.apptickets;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    CrearTickets fragmentCrear;
    ListTickets fragmentList;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        fragmentCrear = new CrearTickets();
        fragmentList = new ListTickets();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragmentList);
        transaction.commit();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            FragmentTransaction navTransaction = getSupportFragmentManager().beginTransaction();

            if (item.getItemId() == R.id.nav_list) {
                navTransaction.replace(R.id.fragment_container, fragmentList);
            } else if (item.getItemId() == R.id.nav_redaccion) {
                navTransaction.replace(R.id.fragment_container, fragmentCrear);
            }

            navTransaction.commit();
            return true;
        });


    }
}
