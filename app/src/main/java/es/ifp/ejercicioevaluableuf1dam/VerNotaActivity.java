package es.ifp.ejercicioevaluableuf1dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VerNotaActivity extends AppCompatActivity {
    protected TextView label1;
    protected TextView label2;
    protected Button boton1;
    protected Button boton2;
    protected DataBaseSQL db;
    private Intent pasarPantalla;
    private String paquete1 = "";
    private String paquete2 = "";
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_nota);

        db = new DataBaseSQL(this);

        label1 = (TextView) findViewById(R.id.label1_VerNota);
        label2 = (TextView) findViewById(R.id.label2_VerNota);
        boton1 = (Button) findViewById(R.id.boton1_VerNota);
        boton2 = (Button) findViewById(R.id.boton2_VerNota);

        extras =  getIntent().getExtras();
        if (extras != null) {  //He recibido al menos un paquete
            paquete2 = extras.getString("NOTA");
            label2.setText(paquete2);
        }

        pasarPantalla = new Intent(VerNotaActivity.this, ListadoActivity.class);

        boton1.setOnClickListener(new View.OnClickListener() { //Boton Volver
            @Override
            public void onClick(View v) {
                finish();
                startActivity(pasarPantalla);
            }
        });
        boton2.setOnClickListener(new View.OnClickListener() { //Boton Borrar
            @Override
            public void onClick(View v) {
                if (extras != null) {  //He recibido al menos un paquete
                    paquete1 = extras.getString("ID");
                    int id = Integer.parseInt(paquete1);
                    db.deleteNote(id);
                    Toast.makeText(VerNotaActivity.this, getString(R.string.toast1_VerNota_text), Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(pasarPantalla);
                }
            }
        });
    }
}