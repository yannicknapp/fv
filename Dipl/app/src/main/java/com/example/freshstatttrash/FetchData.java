package com.example.freshstatttrash;


import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;

import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLSocketFactory;


public class FetchData extends AsyncTask<Void,Void,ArrayList<String>>{
    String datas="";
    String singleParsed="";
    static ArrayList<String> dataParsed=new ArrayList<>();
    JSONObject ob;
    JSONArray jastandings;
    JSONObject jostandinglist;
    JSONObject jo;
    ArrayList<String> id=new ArrayList<>();
    int fertig;
    String position;
    static FetchData instance;

    private ArrayList<String> name=new ArrayList<>();
    private ArrayList<Integer> calories=new ArrayList<>();
    private ArrayList<Boolean> vegan=new ArrayList<>();
    private ArrayList<Boolean> vegetarian=new ArrayList<>();
    private ArrayList<Integer> price=new ArrayList<>();
    private ArrayList<Integer> duration=new ArrayList<>();
    private ArrayList<Boolean> regional=new ArrayList<>();
    private ArrayList<String> origin=new ArrayList<>();
    private ArrayList<String> instructions=new ArrayList<>();
    private ArrayList<String> ingredients=new ArrayList<>();
    private String splittedtext;
    private ArrayList<Integer> zaehler=new ArrayList<>();
    private Land_Rezepte land_rezepte=new Land_Rezepte();
    public FetchData()
    {

    }

    public static FetchData getInstance()
    {
        if(instance==null)
        {
            instance=new FetchData();
        }
        return instance;
    }

    @Override
    protected ArrayList<String> doInBackground(Void... voids) {
        try {
            URL url = new URL("http://172.20.10.5:5000/getallrecipes");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";

            while(line !=null)
            {
                line=bufferedReader.readLine();
                datas =datas + line;

            }

            jastandings=new JSONArray(datas);
            //jostandinglist= (JSONObject) jo.getJSONObject("MRData").getJSONObject("StandingsTable").getJSONArray("StandingsLists").get(0);

            //jastandings= jostandinglist.getJSONArray("DriverStandings");
            for(int i=0;i<jastandings.length();i++)
            {

                ob=(JSONObject) jastandings.get(i);
                //JSONObject jteam= (JSONObject) ob.getJSONArray("Constructors").get(0);
                singleParsed =  "Name: " + ob.get("recipename").toString();
                id.add(ob.get("recipeID").toString());
                name.add(ob.get("recipename").toString());
                calories.add((int) ob.get("calories"));
                vegan.add((boolean) ob.get("vegan"));
                vegetarian.add((boolean) ob.get("vegetarian"));
                price.add((int) ob.get("price"));
                duration.add((int) ob.get("duration"));
                regional.add((boolean) ob.get("regional"));
                origin.add((String) ""+ob.get("origin"));
                instructions.add(ob.get("instructions").toString());
                ingredients.add(ob.get("ingredients").toString());

                Log.d("test",singleParsed);


                dataParsed.add(singleParsed);

            }

        }catch (MalformedURLException e)
        {
            e.printStackTrace();
        }catch (IOException e)
        {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return dataParsed;
    }


    /*public String getData()
    {
        //dataParsed="Hallo Yannic";
        return dataParsed;
    }*/

   @Override
    protected void onPostExecute(ArrayList<String> stringData)
    {
        super.onPostExecute(stringData);

            getAll();

    }


    public void getCountry(int country)
    {
        fertig=dataParsed.size();
        Land_Rezepte.itemList.clear();


            String dbg = dataParsed.get(country);
            Land_Rezepte.itemList.add(new Item_LandRezepte(dbg, "unterschrift"));
            //Land_Rezepte.data.setText(stringData);



        Land_Rezepte.recycle.setHasFixedSize(true);


        Land_Rezepte.recycle.setLayoutManager(Land_Rezepte.mlayout);
        Land_Rezepte.recycle.setAdapter(Land_Rezepte.adapter);
    }
    public void getAll()
    {
        if(land_rezepte.getCountry()==true) {
            for (int i = 0; i < dataParsed.size(); i++) {
                if (origin.get(i).equals(Land_Rezepte.land)) {
                    getCountry(i);
                    zaehler.add(i);
                }
            }
            land_rezepte.setCountry(false);
        }else {
            fertig = dataParsed.size();
            Land_Rezepte.itemList.clear();
            for (int i = 0; i < dataParsed.size(); i++) {

                String dbg = dataParsed.get(i);
                Land_Rezepte.itemList.add(new Item_LandRezepte(dbg, "unterschrift"));
                //Land_Rezepte.data.setText(stringData);

            }

            Land_Rezepte.recycle.setHasFixedSize(true);


            Land_Rezepte.recycle.setLayoutManager(Land_Rezepte.mlayout);
            Land_Rezepte.recycle.setAdapter(Land_Rezepte.adapter);
        }

    }

    public void getRezept(String postition)
    {
        System.out.println("es geht nicht");
        for(int i=0;i<=id.size()-1;i++)
        {
            System.out.println("warum");
            if(postition.equals(id.get(i))) {

                //Rezept.rezept.setText((String) jastandings.get(i).toString());
                Rezept.name.setText(name.get(i));
                Rezept.cal.setText("Calories: "+calories.get(i));
                Rezept.price.setText("Price: "+price.get(i));
                Rezept.duration.setText("Duration: "+duration.get(i));
                //Rezept.origin.setText(origin.get(i));
                String[] splitted=ingredients.get(i).split("\\,");
                splittedtext="";
                for (int s =0;s<splitted.length;s++)
                {
                    splittedtext+=splitted[s]+"\n";
                }
                Rezept.ingredients.setText(splittedtext);
                splittedtext="";
                String[] splitteding=instructions.get(i).split("\\,");
                for (int s =0;s<splitteding.length;s++)
                {

                    splittedtext+="Step "+(s+1)+":"+splitteding[s]+"\n";
                }
                Rezept.instructions.setText(splittedtext);
                System.out.println("na");

            }
        }

    }

    public String getID(int position)
    {
        return id.get(position);
    }

    public String getIDCountry(int position) {
       return id.get(position+zaehler.get(position));
    }
}
