package com.example.david.aprendiendoidiomas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button boton = (Button) findViewById(R.id.bAjuda);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevoform = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(nuevoform);
            }
        });
        Button boton2 = (Button) findViewById(R.id.bAbout); // Seleccionamos el boton About con id bAbout
        boton2.setOnClickListener(new View.OnClickListener() { // Cuando apretamos...
            @Override
            public void onClick (View view) {
                Intent nuevoform = new Intent(MainActivity.this,AboutActivity.class); // ... nos lleva a AboutActivty que contiene el about_layout.xml que es la pantalla
                startActivity(nuevoform); // Activamos el activity
            }
        });
        Button boton3 = (Button) findViewById(R.id.bSortir); // Seleccionamos el boton Sortir con id bSortir
        boton3.setOnClickListener(new View.OnClickListener() { // Cuando apretamos...
            @Override
            public void onClick (View view) {
                showMessageConfirmacio("Sortir", "Segur que vols sortir del joc?");
            }
        });
        Button boton4 = (Button) findViewById(R.id.bAfegir); // Seleccionamos el boton Afegir idioma con id bAfegir
        boton4.setOnClickListener(new View.OnClickListener() { // Cuando apretamos...
            @Override
            public void onClick (View view) {
                Intent nuevoform = new Intent(MainActivity.this,AddInformationActivity.class); // ... nos lleva a AddIdioma que contiene el addparaules_layout.xml que es la pantalla
                startActivity(nuevoform); // Activamos el activity
            }
        });
        Button boton5 = (Button) findViewById(R.id.bJugar); // Seleccionamos el boton Jugar con id bJugar
        boton5.setOnClickListener(new View.OnClickListener() { // Cuando apretamos...
            @Override
            public void onClick (View view) {
                Intent nuevoform = new Intent(MainActivity.this,KindOfGameActivity.class); // ... nos lleva a tipuspartidaactivity que contiene el tipuspartida_layout.xml que es la pantalla
                startActivity(nuevoform); // Activamos el activity
            }
        });
        Button boton6 = (Button) findViewById(R.id.bHighscores); // Seleccionamos el boton Millors temps con id bHighscores
        boton6.setOnClickListener(new View.OnClickListener() { // Cuando apretamos...
            @Override
            public void onClick (View view) {
                Intent nuevoform = new Intent(MainActivity.this,HighScoresActivity.class); // ... nos lleva a HighScoresActivity que contiene el highscores_layour.xml que es la pantalla
                startActivity(nuevoform); // Activamos el activity
            }
        });

        // Creamos las tablas correspondientes e insertamos los records malos si no existian antes

        db=openOrCreateDatabase("BaseDeDatos", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS highscores5par(id INTEGER, nom VARCHAR,paraules INTEGER, segons INTEGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS highscores10par(id INTEGER, nom VARCHAR,paraules INTEGER, segons INTEGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS highscores1min(id INTEGER, nom VARCHAR,paraules INTEGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS nomidiomes(id VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS idiomes(Catala VARCHAR, Castella VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS partida_actual(idioma1 VARCHAR, idioma2 VARCHAR);");


        Cursor c=db.rawQuery("SELECT * FROM highscores5par WHERE id = 1", null);
        if(!c.moveToFirst())
        {
            db.execSQL("INSERT INTO highscores5par(id, nom, paraules, segons) VALUES (1,'-', 0,999);");
            db.execSQL("INSERT INTO highscores5par(id, nom, paraules, segons) VALUES (2,'-', 0,999);");
            db.execSQL("INSERT INTO highscores5par(id, nom, paraules, segons) VALUES (3,'-', 0,999);");
        }

        Cursor c2=db.rawQuery("SELECT * FROM highscores10par WHERE id = 1", null);
        if(!c2.moveToFirst())
        {
            db.execSQL("INSERT INTO highscores10par(id, nom, paraules, segons) VALUES (1,'-', 0,999);");
            db.execSQL("INSERT INTO highscores10par(id, nom, paraules, segons) VALUES (2,'-', 0,999);");
            db.execSQL("INSERT INTO highscores10par(id, nom, paraules, segons) VALUES (3,'-', 0,999);");
        }

        Cursor c3=db.rawQuery("SELECT * FROM highscores1min WHERE id = 1", null);
        if(!c3.moveToFirst())
        {
            db.execSQL("INSERT INTO highscores1min(id, nom, paraules) VALUES (1,'-', 0);");
            db.execSQL("INSERT INTO highscores1min(id, nom, paraules) VALUES (2,'-', 0);");
            db.execSQL("INSERT INTO highscores1min(id, nom, paraules) VALUES (3,'-', 0);");
        }

        Cursor c4=db.rawQuery("SELECT * FROM nomidiomes", null);
        if(!c4.moveToFirst())
        {
            db.execSQL("INSERT INTO nomidiomes(id) VALUES ('Catala');");
            db.execSQL("INSERT INTO nomidiomes(id) VALUES ('Castella');");
        }

        Cursor c5=db.rawQuery("SELECT * FROM idiomes", null);
        if(!c5.moveToFirst())
        {
            db.execSQL("INSERT INTO idiomes(Catala,Castella) VALUES ('Cotxe','Coche');");
            db.execSQL("INSERT INTO idiomes(Catala,Castella) VALUES ('Gos','Perro');");
            db.execSQL("INSERT INTO idiomes(Catala,Castella) VALUES ('Universitat','Universidad');");
            db.execSQL("INSERT INTO idiomes(Catala,Castella) VALUES ('Peix','Pez');");
            db.execSQL("INSERT INTO idiomes(Catala,Castella) VALUES ('Aigua','Agua');");
            db.execSQL("INSERT INTO idiomes(Catala,Castella) VALUES ('Foc','Fuego');");
            db.execSQL("INSERT INTO idiomes(Catala,Castella) VALUES ('Terra','Tierra');");
            db.execSQL("INSERT INTO idiomes(Catala,Castella) VALUES ('Planta','Planta');");
            db.execSQL("INSERT INTO idiomes(Catala,Castella) VALUES ('Electricitat','Electricidad');");
            db.execSQL("INSERT INTO idiomes(Catala,Castella) VALUES ('Gel','Hielo');");
            db.execSQL("INSERT INTO idiomes(Catala,Castella) VALUES ('Roca','Roca');");
        }
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
                MainActivity.this.finish();
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        showMessageConfirmacio("Sortir", "Segur que vols sortir del joc?");
    }
}
