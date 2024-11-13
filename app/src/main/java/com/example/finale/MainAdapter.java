package com.example.finale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private int[] myimages;
    private String[] myname;


    public MainAdapter(Context c, int[] myimages, String[] name){
        context = c;
        this.myimages = myimages;
        this.myname = name;
    }

    @Override
    public int getCount() {
        return myname.length;
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
            view = inflater.inflate(R.layout.item,null);
        }

        ImageView imageView = view.findViewById(R.id.img1);
        TextView name1 = view.findViewById(R.id.name);

        imageView.setImageResource(myimages[position]);
        name1.setText(myname[position] );

        return view;
    }
}

