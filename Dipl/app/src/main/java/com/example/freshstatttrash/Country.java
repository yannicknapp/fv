package com.example.freshstatttrash;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Country extends AppCompatActivity {


    public static final String EXTRA_TEXT1 ="com.example.application.example.EXTRA_TEXT1";
    Button baus;
    Button bspa;
    Button bita;
    int land;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_country);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        baus= findViewById(R.id.Austria);
        baus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                land=0;
                openLandRezepte();
            }
        });

        bspa= findViewById(R.id.Spain);
        bspa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                land=1;
                openLandRezepte();
            }
        });

        bita= findViewById(R.id.Italy);
        bita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                land=2;
                openLandRezepte();
            }
        });


    }

    private void openLandRezepte()
    {
        String l=""+land;
        Intent intent= new Intent(this, Land_Rezepte.class);
        intent.putExtra(EXTRA_TEXT1, l);

        startActivity(intent);

    }

}
