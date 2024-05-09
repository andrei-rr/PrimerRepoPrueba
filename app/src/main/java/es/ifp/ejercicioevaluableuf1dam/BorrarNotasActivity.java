package es.ifp.ejercicioevaluableuf1dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BorrarNotasActivity extends AppCompatActivity {
    protected Button boton1;
    protected Button boton2;
    protected DataBaseSQL db;
    private Intent pasarPantalla;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_notas);
        
        db = new DataBaseSQL(this);
        
        boton1 = (Button) findViewById(R.id.boton1_BorrarNotas);
        boton2 = (Button) findViewById(R.id.boton2_BorrarNotas);
        
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteAllNotes();
                Toast.makeText(BorrarNotasActivity.this, getString(R.string.toast1_BorrarNotas_text), Toast.LENGTH_SHORT).show();
                pasarPantalla = new Intent(BorrarNotasActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarPantalla = new Intent(BorrarNotasActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });
    }
}