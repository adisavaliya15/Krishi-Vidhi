package com.example.finale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.finale.Adapter.Mandi_Commodity_Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Mandi_Commodity_Activity extends MyBaseActivity {
    ListView listView;
    final String url = "https://api.data.gov.in/resource/9ef84268-d588-465a-a308-a864a43d0070?api-key=579b464db66ec23bdd000001748460be39d04040670c1930e7ed4f37&format=json&offset=0&limit=10000";
    List<Mandi_Records> heroList;
    Mandi_Commodity_Adapter adapter;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.mandi_commodity_acivity, null, false);
        drawer.addView(v, 0);
        setTitle("પાક");


        listView = (ListView) findViewById(R.id.listView);
        heroList = new ArrayList<>();

        loadHeroList();
    }
    private void loadHeroList() {
        //getting the progressbar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);


                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);
//                            System.out.println(response);
                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            JSONArray heroArray = obj.getJSONArray("records");
                            int current = heroArray.length();

                            //now looping through all the elements of the json array
                            for (int i = 0; i < heroArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                //creating a hero object and giving them the values from json object
                                Mandi_Records hero = new Mandi_Records(heroObject.getString("state"),
                                        heroObject.getString("state"),
                                        heroObject.getString("district"),
                                        heroObject.getString("market"),
                                        heroObject.getString("commodity"),
                                        heroObject.getString("variety"),
                                        heroObject.getString("arrival_date"),
                                        heroObject.getString("min_price"),
                                        heroObject.getString("max_price"),
                                        heroObject.getString("modal_price")
                                );

                                //adding the hero to herolist
                                heroList.add(hero);
                            }
//                            HashSet hs=new HashSet(Collections.singleton(hero));
                            //creating custom adapter object
                            adapter = new Mandi_Commodity_Adapter(heroList, getApplicationContext());

                            //adding the adapter to listview
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}