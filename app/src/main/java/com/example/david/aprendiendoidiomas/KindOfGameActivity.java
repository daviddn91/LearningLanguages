package com.example.david.aprendiendoidiomas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class KindOfGameActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kindofgame);
        Button boton = (Button) findViewById(R.id.bEnrere);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KindOfGameActivity.this.finish(); // ... cerramos el activity actual
            }
        });
        Button boton2 = (Button) findViewById(R.id.b5par);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevoform = new Intent(KindOfGameActivity.this,Languages5WordsActivity.class);
                startActivity(nuevoform); // Activamos el activity
            }
        });
        Button boton3 = (Button) findViewById(R.id.b10par);
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevoform = new Intent(KindOfGameActivity.this,Languages10WordsActivity.class);
                startActivity(nuevoform); // Activamos el activity
            }
        });
        Button boton4 = (Button) findViewById(R.id.b1min);
        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevoform = new Intent(KindOfGameActivity.this,Languages1MinActivity.class);
                startActivity(nuevoform); // Activamos el activity
            }
        });
    }
}
