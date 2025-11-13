package com.example.apptickets;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
        View rootView = inflater.inflate(R.layout.fragment_landing, container, false);

        txtTituloEdit = rootView.findViewById(R.id.txtTituloEdit);
        txtDescripcionEdit = rootView.findViewById(R.id.txtDescripcionEdit);
        txtComoRepetirEdit = rootView.findViewById(R.id.txtComoRepetirEdit);
        spinnerEstado = rootView.findViewById(R.id.spinnerEstado);
        btnEnviar = rootView.findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(v -> guardarTicket());

        return rootView;
    }

    private void guardarTicket(){
        String titulo = txtTituloEdit.getText().toString().trim();
        String descripcion = txtDescripcionEdit.getText().toString().trim();
        String comoRepetir = txtComoRepetirEdit.getText().toString().trim();
        String estado = spinnerEstado.getSelectedItem().toString();

        if(titulo.isEmpty() || descripcion.isEmpty() || comoRepetir.isEmpty()){
            Toast.makeText(getContext(), "No hay alertas disponibles aún", Toast.LENGTH_SHORT).show();
            return;
        }

        TicketController manager = new TicketController(requireContext());
        manager.crearTicket(titulo, descripcion,comoRepetir,estado);

        txtTituloEdit.setText("");
        txtTituloEdit.setText("");
        txtTituloEdit.setText("");
        txtTituloEdit.setSelection(0);

    }
}