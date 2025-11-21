package com.example.apptickets.View;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.apptickets.R;
import com.example.apptickets.ViewModel.TicketManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Actividad principal de la aplicación AppTickets.
 *
 * <p>Se encarga de inicializar los fragmentos y gestionar la navegación inferior
 * entre el listado de tickets y el formulario de creación/edición.</p>
 *
 * <p>Contiene tres fragmentos principales:
 * <ul>
 *   <li>{@link Landing}: Pantalla inicial de acceso.</li>
 *   <li>{@link CrearTickets}: Fragmento para crear o editar tickets.</li>
 *   <li>{@link ListTickets}: Fragmento para listar y filtrar tickets.</li>
 * </ul>
 * </p>
 */
public class MainActivity extends AppCompatActivity {

    Landing fragmentLanding;
    CrearTickets fragmentCrear;
    ListTickets fragmentList;
    BottomNavigationView bottomNavigationView;

    /**
     * Método de ciclo de vida que inicializa la actividad.
     *
     * <p>Configura los fragmentos, establece el listado como vista inicial y
     * define la navegación inferior entre las secciones principales.</p>
     *
     * @param savedInstanceState Estado guardado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TicketManager manager = new TicketManager(this);

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
                navTransaction.replace(R.id.fragmentContainerTickets, new CrearTickets());
            }

            navTransaction.commit();
            return true;
        });

    }

    /**
     * Cambia el fragmento actual al listado de tickets.
     *
     * <p>Se utiliza tras editar o crear un ticket para volver a la lista.</p>
     */
    public void CambiarFragmento(){
        FragmentTransaction editTransaction = getSupportFragmentManager().beginTransaction();
        editTransaction.replace(R.id.fragmentContainerTickets, fragmentList);
        editTransaction.commit();
    }
}
