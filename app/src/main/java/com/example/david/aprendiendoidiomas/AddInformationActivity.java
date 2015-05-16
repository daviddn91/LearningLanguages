package com.example.david.aprendiendoidiomas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class AddInformationActivity extends ActionBarActivity {

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addinformation);
        Button boton = (Button) findViewById(R.id.bEnrere);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddInformationActivity.this.finish(); // ... cerramos el activity actual
            }
        });
        Button boton2 = (Button) findViewById(R.id.bIdioma);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevoform = new Intent(AddInformationActivity.this, AddLanguagesActivity.class); // ... nos lleva a tipuspartidaactivity que contiene el tipuspartida_layout.xml que es la pantalla
                startActivity(nuevoform); // Activamos el activity
            }
        });
        Button boton3 = (Button) findViewById(R.id.bParaula);
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevoform = new Intent(AddInformationActivity.this,AddWordsActivity.class); // ... nos lleva a tipuspartidaactivity que contiene el tipuspartida_layout.xml que es la pantalla
                startActivity(nuevoform); // Activamos el activity
            }
        });
        db=openOrCreateDatabase("BaseDeDatos", Context.MODE_PRIVATE, null);
        Button boton4 = (Button) findViewById(R.id.bConsultar);
        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = db.rawQuery("SELECT * FROM nomidiomes", null);
                if (c.getCount() == 0) {
                    showMessage("Error", "No hi ha idiomes");
                }
                StringBuffer buffer = new StringBuffer();
                while (c.moveToNext()) {
                    buffer.append("Idioma: " + c.getString(0) + "\n");
                }
                showMessage("Idiomes disponibles", buffer.toString());
            }
        });
        Button boton5 = (Button) findViewById(R.id.bConsultarP);
        boton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevoform = new Intent(AddInformationActivity.this,ConsultaTraduccionsActivity.class); // ... nos lleva a tipuspartidaactivity que contiene el tipuspartida_layout.xml que es la pantalla
                startActivity(nuevoform); // Activamos el activity
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
}
