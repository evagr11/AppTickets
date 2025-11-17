package com.example.apptickets.View;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.apptickets.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Landing fragmentLanding;
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
        fragmentLanding = new Landing();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainerTickets, fragmentList);
        transaction.commit();

        bottomNavigationView = findViewById(R.id.menuNavegacion);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            FragmentTransaction navTransaction = getSupportFragmentManager().beginTransaction();

            if (item.getItemId() == R.id.nav_list) {
                navTransaction.replace(R.id.fragmentContainerTickets, fragmentList);
            } else if (item.getItemId() == R.id.nav_redaccion) {
                navTransaction.replace(R.id.fragmentContainerTickets, fragmentCrear);
            }

            navTransaction.commit();
            return true;
        });


    }
}
