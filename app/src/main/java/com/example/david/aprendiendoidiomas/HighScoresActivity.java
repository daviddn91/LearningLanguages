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
import android.widget.Button;
import android.widget.TextView;


public class HighScoresActivity extends ActionBarActivity {
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscores);
        Button boton = (Button) findViewById(R.id.bEnrere);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HighScoresActivity.this.finish(); // ... cerramos el activity actual
            }
        });

        // Funcion de los botones
        db=openOrCreateDatabase("BaseDeDatos", Context.MODE_PRIVATE, null);

        Button boton2 = (Button) findViewById(R.id.bEsborrar);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            // Falta mensaje de confirmacion del DELETE
            public void onClick(View view) {

                showMessageConfirmacio("Esborrat", "Segur que vols esborrar les millors puntuacions?");
            }
        });


        // Actualizamos las mejores puntuaciones segun la BD

        // Partida a 5 palabras

        String textoNombre5par1 = "      ";
        String textoPalabras5par1 = "     ";
        String textoSegundos5par1 = "     ";
        Cursor c=db.rawQuery("SELECT * FROM highscores5par WHERE id = 1", null);
        while(c.moveToNext()) {
            textoNombre5par1 = textoNombre5par1 + c.getString(1);
            textoPalabras5par1 = textoPalabras5par1 + c.getString(2);
            textoSegundos5par1 = textoSegundos5par1 + c.getString(3);
        }
        // Cogemos el resultado de la query

        TextView textview12 =(TextView)findViewById(R.id.textView12);
        textview12.setText(textoNombre5par1);
        TextView textview13 =(TextView)findViewById(R.id.textView13);
        textview13.setText(textoPalabras5par1);
        TextView textview15 =(TextView)findViewById(R.id.textView15);
        textview15.setText(textoSegundos5par1);

        String textoNombre5par2 = "     ";
        String textoPalabras5par2 = "     ";
        String textoSegundos5par2 = "     ";
        Cursor c2=db.rawQuery("SELECT * FROM highscores5par WHERE id = 2", null);
        while(c2.moveToNext()) {
            textoNombre5par2 = textoNombre5par2 + c2.getString(1);
            textoPalabras5par2 = textoPalabras5par2 + c2.getString(2);
            textoSegundos5par2 = textoSegundos5par2 + c2.getString(3);
        }
        // Cogemos el resultado de la query

        TextView textview22 =(TextView)findViewById(R.id.textView22);
        textview22.setText(textoNombre5par2);
        TextView textview23 =(TextView)findViewById(R.id.textView23);
        textview23.setText(textoPalabras5par2);
        TextView textview25 =(TextView)findViewById(R.id.textView25);
        textview25.setText(textoSegundos5par2);

        String textoNombre5par3 = "      ";
        String textoPalabras5par3 = "     ";
        String textoSegundos5par3 = "     ";
        Cursor c3=db.rawQuery("SELECT * FROM highscores5par WHERE id = 3", null);
        while(c3.moveToNext()) {
            textoNombre5par3 = textoNombre5par3 + c3.getString(1);
            textoPalabras5par3 = textoPalabras5par3 + c3.getString(2);
            textoSegundos5par3 = textoSegundos5par3 + c3.getString(3);
        }
        // Cogemos el resultado de la query

        TextView textview32 =(TextView)findViewById(R.id.textView32);
        textview32.setText(textoNombre5par3);
        TextView textview33 =(TextView)findViewById(R.id.textView33);
        textview33.setText(textoPalabras5par3);
        TextView textview35 =(TextView)findViewById(R.id.textView35);
        textview35.setText(textoSegundos5par3);


        // Partida a 10 palabras

        String textoNombre10par1 = "      ";
        String textoPalabras10par1 = "     ";
        String textoSegundos10par1 = "     ";
        Cursor c4=db.rawQuery("SELECT * FROM highscores10par WHERE id = 1", null);
        while(c4.moveToNext()) {
            textoNombre10par1 = textoNombre10par1 + c4.getString(1);
            textoPalabras10par1 = textoPalabras10par1 + c4.getString(2);
            textoSegundos10par1 = textoSegundos10par1 + c4.getString(3);
        }
        // Cogemos el resultado de la query

        TextView textview42 =(TextView)findViewById(R.id.textView42);
        textview42.setText(textoNombre10par1);
        TextView textview43 =(TextView)findViewById(R.id.textView43);
        textview43.setText(textoPalabras10par1);
        TextView textview45 =(TextView)findViewById(R.id.textView45);
        textview45.setText(textoSegundos10par1);

        String textoNombre10par2 = "     ";
        String textoPalabras10par2 = "     ";
        String textoSegundos10par2 = "     ";
        Cursor c5=db.rawQuery("SELECT * FROM highscores10par WHERE id = 2", null);
        while(c5.moveToNext()) {
            textoNombre10par2 = textoNombre10par2 + c5.getString(1);
            textoPalabras10par2 = textoPalabras10par2 + c5.getString(2);
            textoSegundos10par2 = textoSegundos10par2 + c5.getString(3);
        }
        // Cogemos el resultado de la query

        TextView textview52 =(TextView)findViewById(R.id.textView52);
        textview52.setText(textoNombre10par2);
        TextView textview53 =(TextView)findViewById(R.id.textView53);
        textview53.setText(textoPalabras10par2);
        TextView textview55 =(TextView)findViewById(R.id.textView55);
        textview55.setText(textoSegundos10par2);

        String textoNombre10par3 = "      ";
        String textoPalabras10par3 = "     ";
        String textoSegundos10par3 = "     ";
        Cursor c6=db.rawQuery("SELECT * FROM highscores10par WHERE id = 3", null);
        while(c6.moveToNext()) {
            textoNombre10par3 = textoNombre10par3 + c6.getString(1);
            textoPalabras10par3 = textoPalabras10par3 + c6.getString(2);
            textoSegundos10par3 = textoSegundos10par3 + c6.getString(3);
        }
        // Cogemos el resultado de la query

        TextView textview62 =(TextView)findViewById(R.id.textView62);
        textview62.setText(textoNombre10par3);
        TextView textview63 =(TextView)findViewById(R.id.textView63);
        textview63.setText(textoPalabras10par3);
        TextView textview65 =(TextView)findViewById(R.id.textView65);
        textview65.setText(textoSegundos10par3);


        // Partida a 1 min

        String textoNombre1min1 = "      ";
        String textoPalabras1min1 = "     ";
        Cursor c7=db.rawQuery("SELECT * FROM highscores1min WHERE id = 1", null);
        while(c7.moveToNext()) {
            textoNombre1min1 = textoNombre1min1 + c7.getString(1);
            textoPalabras1min1 = textoPalabras1min1 + c7.getString(2);
        }
        // Cogemos el resultado de la query

        TextView textview72 =(TextView)findViewById(R.id.textView72);
        textview72.setText(textoNombre1min1);
        TextView textview73 =(TextView)findViewById(R.id.textView73);
        textview73.setText(textoPalabras1min1);

        String textoNombre1min2 = "     ";
        String textoPalabras1min2 = "     ";
        Cursor c8=db.rawQuery("SELECT * FROM highscores1min WHERE id = 2", null);
        while(c8.moveToNext()) {
            textoNombre1min2 = textoNombre1min2 + c8.getString(1);
            textoPalabras1min2 = textoPalabras1min2 + c8.getString(2);
        }
        // Cogemos el resultado de la query

        TextView textview82 =(TextView)findViewById(R.id.textView82);
        textview82.setText(textoNombre1min2);
        TextView textview83 =(TextView)findViewById(R.id.textView83);
        textview83.setText(textoPalabras1min2);

        String textoNombre1min3 = "      ";
        String textoPalabras1min3 = "     ";
        Cursor c9=db.rawQuery("SELECT * FROM highscores1min WHERE id = 3", null);
        while(c9.moveToNext()) {
            textoNombre1min3 = textoNombre1min3 + c9.getString(1);
            textoPalabras1min3 = textoPalabras1min3 + c9.getString(2);
        }
        // Cogemos el resultado de la query

        TextView textview92 =(TextView)findViewById(R.id.textView92);
        textview92.setText(textoNombre1min3);
        TextView textview93 =(TextView)findViewById(R.id.textView93);
        textview93.setText(textoPalabras1min3);

    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                HighScoresActivity.this.finish();
            }
        });
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
                db.execSQL("DELETE FROM highscores5par");
                db.execSQL("INSERT INTO highscores5par(id, nom, paraules, segons) VALUES (1,'-', 0,999);");
                db.execSQL("INSERT INTO highscores5par(id, nom, paraules, segons) VALUES (2,'-', 0,999);");
                db.execSQL("INSERT INTO highscores5par(id, nom, paraules, segons) VALUES (3,'-', 0,999);");
                db.execSQL("DELETE FROM highscores10par");
                db.execSQL("INSERT INTO highscores10par(id, nom, paraules, segons) VALUES (1,'-', 0,999);");
                db.execSQL("INSERT INTO highscores10par(id, nom, paraules, segons) VALUES (2,'-', 0,999);");
                db.execSQL("INSERT INTO highscores10par(id, nom, paraules, segons) VALUES (3,'-', 0,999);");
                db.execSQL("DELETE FROM highscores1min");
                db.execSQL("INSERT INTO highscores1min(id, nom, paraules) VALUES (1,'-', 0);");
                db.execSQL("INSERT INTO highscores1min(id, nom, paraules) VALUES (2,'-', 0);");
                db.execSQL("INSERT INTO highscores1min(id, nom, paraules) VALUES (3,'-', 0);");
                showMessage("Millors puntuacions esborrades", "S'han esborrat correctament les millors puntuacions.");
            }
        });
        builder.show();
    }
}
