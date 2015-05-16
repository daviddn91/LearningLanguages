package com.example.david.aprendiendoidiomas;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;


public class AddLanguagesActivity extends ActionBarActivity {

    Spinner spinner1;
    ArrayList<String> idiomas1 = new ArrayList<String>();
    SQLiteDatabase db;
    EditText editidioma = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addlanguages);
        db=openOrCreateDatabase("BaseDeDatos", Context.MODE_PRIVATE, null);
        Cursor c1=db.rawQuery("SELECT * FROM nomidiomes", null);
        while(c1.moveToNext()) {
            idiomas1.add(c1.getString(0));
        }
        editidioma = (EditText) findViewById(R.id.editTextIdioma);
        spinner1 = (Spinner) findViewById(R.id.spinnerIdiomes);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter <String>(this,android.R.layout.simple_spinner_item, idiomas1);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(spinnerArrayAdapter);

        // Boton 'Enrere'
        Button boton = (Button) findViewById(R.id.bEnrere);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddLanguagesActivity.this.finish(); // ... cerramos el activity actual
            }
        });

        // Boton 'Afegir' para insertar en el idioma

        Button botonAfegir = (Button) findViewById(R.id.bAfegir);
        botonAfegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nouidioma = (String) editidioma.getText().toString();
                // Leer idiomas

                if (!nouidioma.matches("")) {
                    Cursor cidioma=db.rawQuery("SELECT * FROM nomidiomes WHERE upper(id) like upper('"+nouidioma+"');", null);
                    if (cidioma.getCount() > 0) {
                        showMessage("Error", "Ja existeix l'idioma que es vol afegir.");
                    }
                    else {
                        db.execSQL("ALTER TABLE idiomes ADD '"+nouidioma+"' VARCHAR;");
                        db.execSQL("INSERT into nomidiomes(id) VALUES ('"+nouidioma+"');");
                        editidioma.setText("");
                        showMessageClose("Idioma afegit", "S'ha afegit l'idioma correctament.");
                    }
                } else {
                    showMessage("Error", "Has d'escriure un nom pel nou idioma.");
                }

            }
        });
    }

    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void showMessageClose(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                AddLanguagesActivity.this.finish();
            }
        });
        builder.show();
    }
}
