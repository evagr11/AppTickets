package com.example.apptickets.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptickets.R;

import com.example.apptickets.Model.Ticket;
import com.example.apptickets.ViewModel.TicketManager;
import com.example.apptickets.Model.EstadoTicket;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CrearTickets extends Fragment {

    private EditText txtTituloEdit, txtDescripcionEdit, txtComoRepetirEdit;
    private Spinner spinnerEstado;
    private Button btnEnviar;
    private TextView txtEstado;

    private int ticketId = 0;
    private boolean esEdicion = false;

    public CrearTickets() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_crear_tickets, container, false);

        txtTituloEdit = rootView.findViewById(R.id.txtTituloEdit);
        txtDescripcionEdit = rootView.findViewById(R.id.txtDescripcionEdit);
        txtComoRepetirEdit = rootView.findViewById(R.id.txtComoRepetirEdit);
        spinnerEstado = rootView.findViewById(R.id.spinnerEstado);
        btnEnviar = rootView.findViewById(R.id.btnEnviar);
        txtEstado = rootView.findViewById(R.id.txtEstado);

        if (getArguments() != null) {
            esEdicion = true;
            txtEstado.setVisibility(View.VISIBLE);
            spinnerEstado.setVisibility(View.VISIBLE);
            txtTituloEdit.setText(getArguments().getString("titulo", ""));
            txtDescripcionEdit.setText(getArguments().getString("descripcion", ""));
            txtComoRepetirEdit.setText(getArguments().getString("comoRepetir", ""));
            String estadoStr = getArguments().getString("estado", EstadoTicket.NUEVO.name());

            spinnerEstado.setSelection(EstadoTicket.valueOf(estadoStr).ordinal());
            ticketId = getArguments().getInt("id", 0);
        }

        btnEnviar.setOnClickListener(v -> guardarTicket());

        return rootView;


    }

    private void guardarTicket(){
        String titulo = txtTituloEdit.getText().toString().trim();
        String descripcion = txtDescripcionEdit.getText().toString().trim();
        String comoRepetir = txtComoRepetirEdit.getText().toString().trim();

        if(titulo.isEmpty() || descripcion.isEmpty() || comoRepetir.isEmpty()){
            Toast.makeText(getContext(), "No hay alertas disponibles aún", Toast.LENGTH_SHORT).show();
            return;
        }

        TicketManager manager = new TicketManager(requireContext());

        if(esEdicion){
            EstadoTicket estado = EstadoTicket.values()[spinnerEstado.getSelectedItemPosition()];
            Ticket ticket = new Ticket(ticketId, titulo, descripcion, comoRepetir, estado);
            manager.actualizarTicket(ticket);
            Toast.makeText(getContext(), "Ticket editado correctamente", Toast.LENGTH_SHORT).show();

            MainActivity activity = (MainActivity)getActivity();
            activity.CambiarFragmento();



        } else {
            Ticket ticket = new Ticket(0, titulo, descripcion, comoRepetir, EstadoTicket.NUEVO);
            manager.guardarTicket(ticket);
            Toast.makeText(getContext(), "Ticket registrado", Toast.LENGTH_SHORT).show();

            // limpiar campos
            txtTituloEdit.setText("");
            txtDescripcionEdit.setText("");
            txtComoRepetirEdit.setText("");
            spinnerEstado.setSelection(0);
        }




    }
}