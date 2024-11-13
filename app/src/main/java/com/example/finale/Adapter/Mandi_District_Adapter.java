package com.example.finale.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.finale.Mandi_Commodity_Activity;
import com.example.finale.Mandi_Records;
import com.example.finale.R;

import java.util.List;

public class Mandi_District_Adapter extends ArrayAdapter<Mandi_Records> {

    //the hero list that will be displayed
    private List<Mandi_Records> heroList;

    //the context object
    private Context mCtx;

    //here we are getting the herolist and context
    //so while creating the object of this adapter class we need to give herolist and context
    public Mandi_District_Adapter(List<Mandi_Records> heroList, Context mCtx) {
        super(mCtx, R.layout.mandi_custom_cell, heroList);
        this.heroList = heroList;
        this.mCtx = mCtx;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.mandi_custom_cell, null, true);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext());
        //getting text views
        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        TextView textViewImageUrl = listViewItem.findViewById(R.id.textViewImageUrl);
        LinearLayout recordsLists = listViewItem.findViewById(R.id.recordsLists);

        //Getting the hero for the specified position
       // Records hero = heroList.get(position);
        System.out.println(preferences.getString("StateName", ""));
//        for (int i = 0; i < heroList.size(); i++) {
//            if (!preferences.getString("StateName", "").equals(heroList.get(i).getState())) {
//                textViewName.setText(heroList.get(i).getState());
//                textViewImageUrl.setText(heroList.get(i).getCommodity());
//            }
//
//        }
        for (int i = 0; i < heroList.size(); i++) {

                //if (!preferences.getString("StateName", "").equals(heroList.get(j).getState())) {
                if (!preferences.getString("StateName", "").equals(heroList.get(i).getState())) {
                    heroList.remove(i);
                    i--;

                }else {
                    for (int j = i +1; j < heroList.size(); j++) {
                        if (heroList.get(i).getDistrict().contains(heroList.get(j).getDistrict())) {
                            heroList.remove(j);
                            j--;
                        }
                    }
                }
        }
        //setting hero values to textviews
        notifyDataSetChanged();
        textViewName.setText(heroList.get(position).getDistrict());
        recordsLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("DistrictName",heroList.get(position).getDistrict());
                System.out.println(preferences.getString("DistrictName", ""));
                editor.apply();
                Intent intent = new Intent(getContext().getApplicationContext(), Mandi_Commodity_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().getApplicationContext().startActivity(intent);
            }
        });
        //returning the listitem
        return listViewItem;
    }
}