package com.example.freshstatttrash;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class Rezept extends AppCompatActivity {


    public static TextView rezept;
    public static TextView name;
    public static TextView cal;
    public static TextView vegan;
    public static TextView veget;
    public static TextView price;
    public static TextView duration;
    public static TextView regional;
    public static TextView origin;
    public static TextView instructions;
    public static TextView ingredients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezept);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rezept=findViewById(R.id.Name);
        name=findViewById(R.id.Name);
        cal=findViewById(R.id.calories);
        vegan=findViewById(R.id.vegan);
        veget=findViewById(R.id.vegetarian);
        price=findViewById(R.id.price);
        duration=findViewById(R.id.duration);
        regional=findViewById(R.id.regional);
        origin=findViewById(R.id.origin);
        instructions=findViewById(R.id.instructions);
        ingredients=findViewById(R.id.ingrediants);

        Intent intent = getIntent();
        String position = intent.getStringExtra(Country.EXTRA_TEXT1);
        System.out.println(position);

        FetchData process=Land_Rezepte.process;

        process.getRezept(position);

    }

}
