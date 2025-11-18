package com.example.apptickets.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.apptickets.R;

import com.example.apptickets.Model.Ticket;
import com.example.apptickets.ViewModel.TicketManager;
import com.example.apptickets.Model.EstadoTicket;

public class CrearTickets extends Fragment {

    private EditText txtTituloEdit, txtDescripcionEdit, txtComoRepetirEdit;
    private Spinner spinnerEstado;
    private Button btnEnviar;


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

        ArrayAdapter<EstadoTicket> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                EstadoTicket.values()
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapter);

        btnEnviar.setOnClickListener(v -> guardarTicket());

        return rootView;


    }

    private void guardarTicket(){
        String titulo = txtTituloEdit.getText().toString().trim();
        String descripcion = txtDescripcionEdit.getText().toString().trim();
        String comoRepetir = txtComoRepetirEdit.getText().toString().trim();
        EstadoTicket estado = (EstadoTicket) spinnerEstado.getSelectedItem();

        if(titulo.isEmpty() || descripcion.isEmpty() || comoRepetir.isEmpty()){
            Toast.makeText(getContext(), "No hay alertas disponibles aún", Toast.LENGTH_SHORT).show();
            return;
        }

        Ticket ticket = new Ticket(0, titulo, descripcion, comoRepetir, estado);
        TicketManager manager = new TicketManager(requireContext());
        manager.guardarTicket(ticket);

        txtTituloEdit.setText("");
        txtDescripcionEdit.setText("");
        txtComoRepetirEdit.setText("");
        spinnerEstado.setSelection(0);

    }
}