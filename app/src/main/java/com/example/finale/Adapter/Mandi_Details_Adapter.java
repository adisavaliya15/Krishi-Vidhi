package com.example.finale.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.finale.Mandi_Records;
import com.example.finale.R;

import java.util.List;

public class Mandi_Details_Adapter extends ArrayAdapter<Mandi_Records> {

    //the hero list that will be displayed
    private List<Mandi_Records> heroList;

    //the context object
    private Context mCtx;

    //here we are getting the herolist and context
    //so while creating the object of this adapter class we need to give herolist and context
    public Mandi_Details_Adapter(List<Mandi_Records> heroList, Context mCtx) {
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

        //Getting the hero for the specified position
        // Records hero = heroList.get(position);
        System.out.println(preferences.getString("CommodityName", ""));
//        for (int i = 0; i < heroList.size(); i++) {
//            if (!preferences.getString("StateName", "").equals(heroList.get(i).getState())) {
//                textViewName.setText(heroList.get(i).getState());
//                textViewImageUrl.setText(heroList.get(i).getCommodity());
//            }
//
//        }
        for (int i = 0; i < heroList.size(); i++) {
            if (!preferences.getString("DistrictName", "").equals(heroList.get(i).getDistrict()) || !preferences.getString("CommodityName", "").equals(heroList.get(i).getCommodity())) {
                heroList.remove(i);
                i--;
            }
//             for (int j = i ; j < heroList.size(); j++) {
//                //if (!preferences.getString("StateName", "").equals(heroList.get(j).getState())) {
//                if (!preferences.getString("CommodityName", "").equals(heroList.get(j).getCommodity())) {
//                    heroList.remove(j);
//                    j--;
//                }
//            }

        }
        //setting hero values to textviews
        notifyDataSetChanged();
        textViewName.setText(heroList.get(position).getState() + "\n" + heroList.get(position).getDistrict());

        textViewImageUrl.setText(heroList.get(position).getCommodity() + "\n" + heroList.get(position).getMarket()
                + "\n" + heroList.get(position).getVariety() + "\n" + heroList.get(position).getArrival_date() + "\n" + heroList.get(position).getMin_price() + "\n" + heroList.get(position).getMax_price());
        //returning the listitem
        return listViewItem;
    }
}