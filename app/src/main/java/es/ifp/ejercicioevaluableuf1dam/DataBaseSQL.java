package es.ifp.ejercicioevaluableuf1dam;

import static android.provider.Settings.System.getString;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseSQL extends SQLiteOpenHelper {
    protected SQLiteDatabase db;

    public DataBaseSQL(Context context) { //Contructor, Context hace referencia a la actividad como parametro
        //nombre de la BD es "notitas"
        super(context, "notitas", null, 1); //param: 1 es la version, variar cada vez que se construya una BD
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE table notas (id integer primary key autoincrement not null, nota text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notitas"); //Elimiar tabla si hay una nueva versión, usar ALTER para modificar
    }

    public int numberOfNotes() { //Indica el numero de notas en la BD
        int num = 0;
        db = this.getReadableDatabase(); //Abrir BD en moto lectura
        num = (int) DatabaseUtils.queryNumEntries(db, "notas");
        return num;
    }
    public ArrayList<String> getAllNotes() {
        ArrayList<String> filas =  new ArrayList<>();

        Cursor res = null; //Objeto Cursor, parecido ArrayList, que guarda resultados
        String contenido = "";
        if (numberOfNotes() > 0) {
            db = this.getReadableDatabase();
            res = db.rawQuery("SELECT * FROM notas", null);
            res.moveToFirst(); //Empezar en la priemera fila, sino por defecto res = -1
            while (res.isAfterLast() == false) { //mientras no pase del ultimo resultado
                contenido = res.getInt(res.getColumnIndex("id")) + ".-   " + res.getString(res.getColumnIndex("nota"));
                filas.add(contenido);
                //System.out.println("--> " + contenido);
                res.moveToNext(); //Mover a la siguiente fila
            }
        }
        return filas;
    }

    public void insertNote(String nota) { //Creamos método Insert
        db = this.getWritableDatabase(); //Abrir la BD en modo lectura
        db.execSQL("INSERT INTO notas (nota) VALUES ('" + nota + "')");
    }
    public void deleteNote(int id) { //Borrar una nota
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM notas WHERE id = "+id);
    }
    public void deleteAllNotes() { //Metodo borrar Todas las notas
        db = this.getWritableDatabase(); //Abrir BD en modo escritura
        db.execSQL("DELETE FROM notas");
    }

   /* public void close() {
        db.close();
    }*/
}
