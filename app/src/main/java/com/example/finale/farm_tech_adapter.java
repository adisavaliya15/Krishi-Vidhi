package com.example.finale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class farm_tech_adapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private String[] farmtech;


    public farm_tech_adapter(Context c, String[] name){
        context = c;
        this.farmtech = name;


    }

    @Override
    public int getCount() {
        return farmtech.length;
    }

    @Override
    public Object getItem(int position) {
        return getItemId(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view==null){
            view = inflater.inflate(R.layout.farm_tech_item,null);
        }

        TextView name4 = view.findViewById(R.id.farmtech);


        name4.setText(farmtech[position] );

        return view;
    }
}
