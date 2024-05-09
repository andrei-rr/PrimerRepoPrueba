package es.ifp.ejercicioevaluableuf1dam;

import static android.provider.Settings.System.getString;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {
    protected ListView list1;
    private ArrayList<String> notas = new ArrayList<String>(); //ArrayList para el ListView
    private ArrayAdapter<String> adaptador; //Es una clase que se encarga de asignar los elementos de un array al ListView
    private String contenidoItem = "";
    private Intent pasarPantalla;
    protected DataBaseSQL db;
    private String[] partes;
    private int identificador = 0;
    private String nota = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        db= new DataBaseSQL(this);
        list1 = (ListView) findViewById(R.id.list1_listado);

        notas = db.getAllNotes();
        //Cada uno de los elementos del ArrayList y los añade en cada uno de los items de la lista
        adaptador = new ArrayAdapter<String>(ListadoActivity.this, android.R.layout.simple_list_item_1, notas);
        list1.setAdapter(adaptador); //Asignar el adaptador a la lista

        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                contenidoItem = parent.getItemAtPosition(position).toString();
                partes = contenidoItem.split(".-");
                if (partes.length > 1) {
                    identificador = Integer.parseInt(partes[0]);
                    nota = partes[1];
                }
                pasarPantalla = new Intent(ListadoActivity.this, VerNotaActivity.class);
                pasarPantalla.putExtra("ID", Integer.toString(identificador));
                pasarPantalla.putExtra("NOTA", nota);
                finish();
                startActivity(pasarPantalla);
            }
        });
        //db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //Sobrecarga de onCreate para el MENU
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_listado, menu); //Le asigmanos el menu_listado.xml
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // Capturar los eventos Click en los items del MENU
        // Handle item selection
        int itemId = item.getItemId(); // Obtener el ID del item seleccionado

        if (itemId == R.id.item_crear_listado) { // Item Menu Crear
            pasarPantalla = new Intent(ListadoActivity.this, CrearNotaActivity.class);
            finish();
            startActivity(pasarPantalla);
            return true;
        } else if (itemId == R.id.item_opciones_listado) { // Item Menu Opciones
            pasarPantalla = new Intent(ListadoActivity.this, BorrarNotasActivity.class);
            finish();
            startActivity(pasarPantalla);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}