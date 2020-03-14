package com.example.freshstatttrash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Land_Rezepte extends AppCompatActivity implements Item_Adapter.onNoteListener
{

    public static final String EXTRA_TEXT1 ="com.example.application.example.EXTRA_TEXT1";
    public static RecyclerView recycle;
    public static RecyclerView.Adapter adapter;
    public static RecyclerView.LayoutManager mlayout;
    public static ArrayList<Item_LandRezepte> itemList=new ArrayList<>();

    public static FetchData process =FetchData.getInstance();
    public static TextView data;
    TextView text;
    static Boolean count=true;



    private static Boolean country=false;
    public static String land;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landrezepte);

        Intent intent = getIntent();
        land = intent.getStringExtra(Country.EXTRA_TEXT1);
        if(land!=null)country=true;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        adapter = new Item_Adapter(itemList,this);
        recycle = findViewById(R.id.Rezepte);
        mlayout = new LinearLayoutManager(this);
        if (count==true)
        {
            process.execute();
            count=false;

        }else
        {
            process.getAll();
        }




        
        //itemList.add(new Item_LandRezepte("Line1","line2"));
        //itemList.add(new Item_LandRezepte("Line2","line3"));

    }


    @Override
    public void onNoteClick(int position)
    {
        String id;
        if(land==null)
        {
            id = process.getID(position);
        }else
        {
            id=process.getIDCountry(position);
        }
        Intent intent=new Intent(this, Rezept.class);
        intent.putExtra(EXTRA_TEXT1, id);

        startActivity(intent);
    }

    public Boolean getCountry() {
        return country;
    }

    public void setCountry(Boolean country) {
        this.country = country;
    }




}
