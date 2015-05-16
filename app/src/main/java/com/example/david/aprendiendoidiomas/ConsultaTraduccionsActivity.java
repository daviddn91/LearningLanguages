package com.example.david.aprendiendoidiomas;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ConsultaTraduccionsActivity extends ActionBarActivity {

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db=openOrCreateDatabase("BaseDeDatos", Context.MODE_PRIVATE, null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultatraduccions);
        Button boton = (Button) findViewById(R.id.bEnrere);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConsultaTraduccionsActivity.this.finish(); // ... cerramos el activity actual
            }
        });

        Button boton2 = (Button) findViewById(R.id.bConsultar);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText text = (EditText) findViewById(R.id.editTextTrad);
                String texto = text.getText().toString();
                if (texto.matches("")) {
                    showMessage("Error", "Has d'escriure un nom per cercar traduccions.");
                }
                else {
                    Cursor c = db.rawQuery("SELECT * FROM idiomes WHERE upper(Catala) like upper('"+texto+"')", null);
                    int nc = c.getColumnCount();
                    int i = 0;
                    if (c.getCount() == 0) {
                        showMessage("Error", "No hi ha paraules amb aquest nom.");
                    }
                    else {
                        StringBuffer buffer = new StringBuffer();
                        Boolean titol = false;

                        while (c.moveToNext()) {
                            if (!titol) {
                                buffer.append("Paraula "+ c.getString(0) + ":"  + "\n" + "\n");
                                titol = true;
                            }
                            i = 1;
                            while (i < nc) {
                                if (c.isNull(i)) {

                                } else {
                                    buffer.append("  - " + c.getColumnName(i) + ": " + c.getString(i) + "\n");
                                }
                                ++i;
                            }
                        }
                        showMessage("Consultar traduccions", buffer.toString());
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
}
