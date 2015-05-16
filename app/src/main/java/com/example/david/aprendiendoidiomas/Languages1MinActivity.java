package com.example.david.aprendiendoidiomas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class Languages1MinActivity extends ActionBarActivity {
    Spinner spinner1, spinner2;
    ArrayList<String> idiomas1 = new ArrayList<String>();
    ArrayList<String> idiomas2 = new ArrayList<String>();
    String idioma1 = "";
    String idioma2 = "";
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.languages1min);
        Button boton = (Button) findViewById(R.id.bEnrere);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Languages1MinActivity.this.finish(); // ... cerramos el activity actual
            }
        });
        db=openOrCreateDatabase("BaseDeDatos", Context.MODE_PRIVATE, null);
        Cursor c1=db.rawQuery("SELECT * FROM nomidiomes", null);
        while(c1.moveToNext()) {
            idiomas1.add(c1.getString(0));
        }

        spinner1 = (Spinner) findViewById(R.id.spinnerIdioma1);
        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter <String>(this,android.R.layout.simple_spinner_item, idiomas1);
        spinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(spinnerArrayAdapter1);

        Cursor c2=db.rawQuery("SELECT * FROM nomidiomes", null);
        while(c2.moveToNext()) {
            idiomas2.add(c2.getString(0));
        }
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idioma1 = idiomas1.get(i);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }

        });
        spinner2 = (Spinner) findViewById(R.id.spinnerIdioma2);
        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter <String>(this,android.R.layout.simple_spinner_item, idiomas2);
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(spinnerArrayAdapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idioma2 = idiomas2.get(i);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        Button boton2 = (Button) findViewById(R.id.bJugar);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (idioma1.matches("") || idioma2.matches("")) {
                    showMessage("Error", "Selecciona els dos idiomes.");
                }
                else {
                    if (idioma1.matches(idioma2)) {
                        showMessage("Error", "Els dos idiomes no poden ser iguals.");
                    } else {
                        Cursor c2=db.rawQuery("SELECT * FROM idiomes WHERE "+idioma1+" is not null and "+idioma2+ " is not null", null);
                        Boolean hiha = false;
                        int contadorpalabras = 0;
                        while(c2.moveToNext()) {
                            hiha = true;
                            contadorpalabras++;
                        }
                        if (hiha) {
                            if (contadorpalabras >= 5) {
                                db.execSQL("DELETE FROM partida_actual");
                                db.execSQL("INSERT INTO partida_actual(idioma1, idioma2) VALUES ('" + idioma1 + "','" + idioma2 + "');");
                                // AQUI MIRAR SI LOS DOS IDIOMAS TIENEN VARIAS PALABRAS PARA PODER JUGAR
                                Intent nuevoform = new Intent(Languages1MinActivity.this, Game1MinActivity.class);
                                startActivity(nuevoform); // Activamos el activity
                            }
                            else {
                                showMessageConfirmacio("Compte!", "Hi ha poques traduccions entre els dos idiomes, algunes paraules es repetiran.\nSegur que vols continuar?");
                            }
                        }
                        else {
                            showMessage("Error", "Els dos idiomes no tenen paraules comunes.");
                        }
                    }
                }
            }
        });

    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void showMessageConfirmacio(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.setNegativeButton("SI", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                db.execSQL("DELETE FROM partida_actual");
                db.execSQL("INSERT INTO partida_actual(idioma1, idioma2) VALUES ('" + idioma1 + "','" + idioma2 + "');");
                // AQUI MIRAR SI LOS DOS IDIOMAS TIENEN VARIAS PALABRAS PARA PODER JUGAR
                Intent nuevoform = new Intent(Languages1MinActivity.this, Game1MinActivity.class);
                startActivity(nuevoform); // Activamos el activity
            }
        });
        builder.show();
    }
}
