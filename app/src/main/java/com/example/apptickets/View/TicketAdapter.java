package com.example.apptickets.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptickets.Model.Ticket;
import com.example.apptickets.R;

import java.util.List;

public class TicketAdapter extends ArrayAdapter<Ticket> {

    public TicketAdapter(Context context, List<Ticket> tickets) {
        super(context, 0, tickets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_ticket, parent, false);

        Ticket ticket = getItem(position);

        TextView titulo = itemView.findViewById(R.id.Titulo);
        TextView descripcion = itemView.findViewById(R.id.Descripcion);
        TextView id = itemView.findViewById(R.id.ID);
        ImageView circulo = itemView.findViewById(R.id.imageView4);

        titulo.setText(ticket.getTitulo());
        descripcion.setText(ticket.getDescripcion());
        id.setText(String.valueOf(ticket.getId()));

        switch (ticket.getEstado()) {
            case NUEVO:
                circulo.setColorFilter(getContext().getResources().getColor(R.color.estado_nuevo));
                break;
            case ABIERTO:
                circulo.setColorFilter(getContext().getResources().getColor(R.color.estado_abierto));
                break;
            case EN_PROCESO:
                circulo.setColorFilter(getContext().getResources().getColor(R.color.estado_enProceso));
                break;
            case RESUELTO:
                circulo.setColorFilter(getContext().getResources().getColor(R.color.estado_resuelto));
                break;
            case CERRADO:
                circulo.setColorFilter(getContext().getResources().getColor(R.color.estado_cerrado));
                break;
        }

        return itemView;
    }

}
