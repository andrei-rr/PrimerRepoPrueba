package es.ifp.ejercicioevaluableuf1dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CrearNotaActivity extends AppCompatActivity {
    protected TextView label1;
    protected EditText caja1;
    protected Button boton1;
    protected Button boton2;
    private String contenidoCaja = "";
    private Intent pasarPantalla;
    protected DataBaseSQL db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_nota);

        db = new DataBaseSQL(this); // Inicializa la instancia de DataBaseSQL

        label1 = (TextView) findViewById(R.id.label1_CrearNota);
        caja1 = (EditText) findViewById(R.id.caja1_CrearNota);
        boton1 = (Button) findViewById(R.id.boton1_CrearNota);
        boton2 = (Button) findViewById(R.id.boton2_CrearNota);

        boton1.setOnClickListener(new View.OnClickListener() { //Boton Volver
            @Override
            public void onClick(View v) {
                pasarPantalla = new Intent(CrearNotaActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });
        boton2.setOnClickListener(new View.OnClickListener() { //Boton Crear
            @Override
            public void onClick(View v) {
                contenidoCaja = caja1.getText().toString();
                
                if (contenidoCaja.equals("")) { //Si la caja está vacía
                    Toast.makeText(CrearNotaActivity.this, getString(R.string.toast1_CrearNota_text), Toast.LENGTH_SHORT).show();
                }
                else { //Insertar en la BD
                    db.insertNote(contenidoCaja);
                    Toast.makeText(CrearNotaActivity.this, getString(R.string.toast2_CrearNota_text), Toast.LENGTH_SHORT).show();
                    pasarPantalla = new Intent(CrearNotaActivity.this, ListadoActivity.class);
                    finish();
                    startActivity(pasarPantalla);
                }
            }
        });
    }
}