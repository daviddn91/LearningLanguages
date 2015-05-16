package com.example.david.aprendiendoidiomas;

import android.content.Context;
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
import android.app.AlertDialog.Builder;

import java.util.ArrayList;


public class AddWordsActivity extends ActionBarActivity {

    Spinner spinner1;
    ArrayList<String> idiomas1 = new ArrayList<String>();
    SQLiteDatabase db;
    String idioma = new String();
    EditText editcat = null;
    EditText edit2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addwords);
        db=openOrCreateDatabase("BaseDeDatos", Context.MODE_PRIVATE, null);
        Cursor c1=db.rawQuery("SELECT * FROM nomidiomes WHERE id not in ('Catala')", null);
        while(c1.moveToNext()) {
            idiomas1.add(c1.getString(0));
        }
        editcat = (EditText) findViewById(R.id.editTextCat);
        edit2 = (EditText) findViewById(R.id.editText2);
        spinner1 = (Spinner) findViewById(R.id.spinnerIdiomes);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter <String>(this,android.R.layout.simple_spinner_item, idiomas1);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(spinnerArrayAdapter);

        // Boton 'Enrere'
        Button boton = (Button) findViewById(R.id.bEnrere);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddWordsActivity.this.finish(); // ... cerramos el activity actual
            }
        });

        // Boton 'Afegir' para insertar en el idioma

        Button botonAfegir = (Button) findViewById(R.id.bAfegir);
        botonAfegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String paraulacat = (String) editcat.getText().toString();
                String paraula2 = (String) edit2.getText().toString();
                // Leer idiomas
                if (idioma != "Catala" && !paraulacat.matches("") && !paraula2.matches("")) {
                    Cursor c = db.rawQuery("SELECT * FROM idiomes WHERE Catala = '"+paraulacat+"' and "+idioma+" is null", null);
                    if(c.getCount()==0)
                    {
                        db.execSQL("INSERT INTO idiomes('Catala','" + idioma + "') VALUES ('" + paraulacat + "','" + paraula2 + "');");
                        editcat.setText("");
                        edit2.setText("");
                        showMessage("Paraula afegida", "Paraula afegida correctament.");
                    }
                    else {
                        db.execSQL("UPDATE idiomes SET "+idioma+"='"+paraula2+"' WHERE Catala = '"+paraulacat+"'");
                        editcat.setText("");
                        edit2.setText("");
                        showMessage("Paraula afegida", "Paraula afegida correctament.");
                    }
                }
                else {
                    showMessage("Error", "Has d'escriure les dues traduccions.");
                }
                // Consultar todas las palabras de la BD
                Cursor c = db.rawQuery("SELECT * FROM idiomes", null);
                if(c.getCount()==0)
                {
                    showMessage("Error", "No records found");
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Catala: "+c.getString(0)+"\n");
                    buffer.append("Castella: "+c.getString(1)+"\n");
                }
                // Muestra las palabras de la BD
                //showMessage("Paraules guardades", buffer.toString());
            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idioma = idiomas1.get(i);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
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

}
