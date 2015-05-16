package com.example.david.aprendiendoidiomas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class Game5WordsActivity extends ActionBarActivity {

    SQLiteDatabase db;
    TextView encerts = null;
    TextView fallades = null;
    String idioma1 = null;
    String idioma2 = null;
    String answer = null;
    String ansmayus = null;
    String p2mayus = null;
    String paraula = null;
    ArrayList<String> paraulespartida = new ArrayList<String>();
    TextView paraulaex = null;
    // Variable para tiempo de la partida
    Integer t = 0;
    // Variable para el nombre
    String nombre = "";
    Integer e = null;
    Integer f = null;
    Chronometer chrono = null;
    Chronometer mycm = null;
    Random randomGenerator = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game5words);
        db=openOrCreateDatabase("BaseDeDatos", Context.MODE_PRIVATE, null);
        encerts = (TextView) findViewById(R.id.textViewEncerts);
        fallades = (TextView) findViewById(R.id.textViewFallades);
        // Chronometer
        chrono = (Chronometer) findViewById(R.id.chronometer);
        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.start();
        chrono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                t += 1;
            }
        });
        paraulespartida = new ArrayList();
        Cursor c=db.rawQuery("SELECT * FROM partida_actual", null);

        while(c.moveToNext())
        {
            idioma1 = c.getString(0);
            idioma2 = c.getString(1);
        }


        Cursor clpar=db.rawQuery("SELECT "+idioma1+" FROM idiomes WHERE "+idioma1+" is not null and "+idioma2+" is not null", null);
        while(clpar.moveToNext())
        {
            paraulespartida.add(clpar.getString(0));
        }
        int valor = paraulespartida.size();
        int randomInt = randomGenerator.nextInt(valor);
        String paraulaact = paraulespartida.get(randomInt);
        paraulaex = (TextView) findViewById(R.id.textViewParaula);
        paraulaex.setText(paraulaact);


        // Boto enrere
        Button boton = (Button) findViewById(R.id.bEnrere);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessageConfirmacio("Sortir", "Segur que vols sortir de la partida? Es perdran les dades actuals.");
            }
        });


        // Boto Validar paraula
        Button boton2 = (Button) findViewById(R.id.bValidar); // Seleccionamos el boton Millors temps con id bHighscores
        boton2.setOnClickListener(new View.OnClickListener() { // Cuando apretamos...
            @Override
            public void onClick(View view) {
                String nume = encerts.getText().toString();
                // Variable para acierto
                e = Integer.parseInt(nume);
                String numf = fallades.getText().toString();
                // Variable para fallo
                f = Integer.parseInt(numf);

                // Aqui comprobar si es acierto o no
                TextView paraula1 = (TextView) findViewById(R.id.textViewParaula);
                String p1 = paraula1.getText().toString();
                EditText paraula2 = (EditText) findViewById(R.id.editTextParaulaIntroduida);
                String p2 = paraula2.getText().toString();
                Boolean correctans = false;
                if (!p2.matches("")) {
                    // Comprovem correctesa de la paraula
                    Cursor c1 = db.rawQuery("SELECT " + idioma2 + " FROM idiomes WHERE upper(" + idioma1 + ") like upper('" + p1 + "') and upper(" + idioma2 + ") like upper('" + p2 + "')", null);
                    while (c1.moveToNext()) {
                        correctans = true;
                    }
                    if (correctans) {
                        correctans = false;
                        e = e + 1;
                        encerts.setText(e.toString());
                    } else {
                        f = f + 1;
                        fallades.setText(f.toString());
                    }

                    paraula2.setText("");
                    if ((e + f) >= 5) {
                        //t = t/6;
                        Cursor c2 = db.rawQuery("SELECT * FROM highscores5par WHERE id == 1", null);
                        while (c2.moveToNext()) {
                            Integer paraules = Integer.parseInt(c2.getString(2));
                            Integer temps = Integer.parseInt(c2.getString(3));
                            if ((e > paraules) || (e == paraules && t < temps)) {
                                showMessageRecordsName1("Felicitats!", "Has aconseguit el primer lloc! Introdueix el teu nom:");
                            } else {
                                Cursor c3 = db.rawQuery("SELECT * FROM highscores5par WHERE id == 2", null);
                                while (c3.moveToNext()) {
                                    Integer paraules2 = Integer.parseInt(c3.getString(2));
                                    Integer temps2 = Integer.parseInt(c3.getString(3));
                                    if ((e > paraules2) || (e == paraules2 && t < temps2)) {
                                        showMessageRecordsName2("Felicitats!", "Has aconseguit el segon lloc! Introdueix el teu nom:");
                                    } else {
                                        Cursor c4 = db.rawQuery("SELECT * FROM highscores5par WHERE id == 2", null);
                                        while (c4.moveToNext()) {
                                            Integer paraules3 = Integer.parseInt(c4.getString(2));
                                            Integer temps3 = Integer.parseInt(c4.getString(3));
                                            if ((e > paraules3) || (e == paraules3 && t < temps3)) {
                                                showMessageRecordsName3("Felicitats!", "Has aconseguit el tercer lloc! Introdueix el teu nom:");
                                            } else {
                                                showMessageRecords("Ohh...", "No has aconseguit una de les millors puntuacions.");
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        int valor = paraulespartida.size();
                        int randomInt = randomGenerator.nextInt(valor);
                        String paraulaact = paraulespartida.get(randomInt);
                        paraulaex = (TextView) findViewById(R.id.textViewParaula);
                        paraulaex.setText(paraulaact);
                    }
                }else {
                    showMessage("Error", "Has d'escriure una paraula.");
                }
            }
        });


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
                Game5WordsActivity.this.finish();
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        showMessageConfirmacio("Sortir", "Segur que vols sortir de la partida? Es perdran les dades actuals.");
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.show();
    }

    public void showMessageRecords(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Game5WordsActivity.this.finish();
                Intent nuevoform = new Intent(Game5WordsActivity.this, HighScoresActivity.class);
                startActivity(nuevoform); // Activamos el activity
            }
        });
        builder.show();
    }
    public void showMessageRecordsName1(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Editable value = input.getText();
                nombre = value.toString();
                db.execSQL("UPDATE highscores5par SET id = 4 WHERE id = 3");
                db.execSQL("UPDATE highscores5par SET id = 3 WHERE id = 2");
                db.execSQL("UPDATE highscores5par SET id = 2 WHERE id = 1");
                db.execSQL("UPDATE highscores5par SET id = 1 WHERE id = 4");
                db.execSQL("UPDATE highscores5par SET paraules=" + e + ", segons=" + t + ", nom = '" + nombre + "' WHERE id = 1");
                Game5WordsActivity.this.finish();
                Intent nuevoform = new Intent(Game5WordsActivity.this, HighScoresActivity.class);
                startActivity(nuevoform); // Activamos el activity
            }
        });
        // Set an EditText view to get user input
        builder.show();
    }
    public void showMessageRecordsName2(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Editable value = input.getText();
                nombre = value.toString();
                db.execSQL("UPDATE highscores5par SET id = 4 WHERE id = 3");
                db.execSQL("UPDATE highscores5par SET id = 3 WHERE id = 2");
                db.execSQL("UPDATE highscores5par SET id = 2 WHERE id = 4");
                db.execSQL("UPDATE highscores5par SET paraules=" + e + ", segons=" + t + ", nom = '" + nombre + "' WHERE id = 2");
                Game5WordsActivity.this.finish();
                Intent nuevoform = new Intent(Game5WordsActivity.this, HighScoresActivity.class);
                startActivity(nuevoform); // Activamos el activity
            }
        });
        // Set an EditText view to get user input
        builder.show();
    }
    public void showMessageRecordsName3(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Editable value = input.getText();
                nombre = value.toString();
                db.execSQL("UPDATE highscores5par SET paraules=" + e + ", segons=" + t + ", nom = '" + nombre + "' WHERE id = 3");
                Game5WordsActivity.this.finish();
                Intent nuevoform = new Intent(Game5WordsActivity.this, HighScoresActivity.class);
                startActivity(nuevoform); // Activamos el activity
            }
        });
        // Set an EditText view to get user input
        builder.show();
    }
}
