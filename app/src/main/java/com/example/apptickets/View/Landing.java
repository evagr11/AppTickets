package com.example.apptickets.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.apptickets.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Fragmento inicial de la aplicación.
 *
 * <p>Muestra un botón para acceder al sistema y habilita la navegación inferior.
 * Al pulsar el botón, oculta el contenedor de la pantalla de inicio y muestra el menú
 * de navegación inferior.</p>
 */
public class Landing extends Fragment {

    public Landing() {
        // Required empty public constructor
    }

    /**
     * Infla el layout del fragmento y configura el botón de acceso.
     * Al pulsar el botón, oculta el contenedor de landing y muestra el menú de navegación.
     *
     * @param inflater Inflador de vistas.
     * @param container Contenedor padre.
     * @param savedInstanceState Estado guardado.
     * @return Vista raíz del fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_landing, container, false);

        Button btnAcceder = (Button) rootView.findViewById(R.id.btnAcceder);
        //Acceder a algo que esta en MainActivity
        FragmentContainerView fragmentContainerLanding = (FragmentContainerView) ((MainActivity) getActivity()).findViewById(R.id.fragmentContainerLanding);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) ((MainActivity) getActivity()).findViewById(R.id.menuNavegacion);

        btnAcceder.setOnClickListener(v -> {
            fragmentContainerLanding.setVisibility(View.GONE);
            bottomNavigationView.setVisibility(View.VISIBLE);

        });

        return rootView;

    }
}