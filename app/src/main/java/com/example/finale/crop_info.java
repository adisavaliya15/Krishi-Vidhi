package com.example.finale;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class crop_info extends MyBaseActivity {
    private static long mLastClickTime = 0;
    int[] myimages = {R.drawable.ic_rokadiya, R.drawable.ic_telibiya, R.drawable.ic_kathol, R.drawable.ic_anaj, R.drawable.ic_masala, R.drawable.ic_veg, R.drawable.ic_fruit};
    String[] names = {"રોકડિયા", "તેલિબિયા", "કઠોળ", "અનાજ", "મસાલા", "શાક્ભાજી", "ફળો"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=LayoutInflater.from(this);
        View v =inflater.inflate(R.layout.activity_crop_info,null,false);
        drawer.addView(v,0);
        setTitle("પાકની વિગતો");

        GridView gridView = findViewById(R.id.cropGrid);

        MainAdapter cropadapter = new MainAdapter(crop_info.this,myimages,names);
        gridView.setAdapter(cropadapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                mLastClickTime = SystemClock.elapsedRealtime();
                if (position == 0) {
                    Intent intent = new Intent(crop_info.this, pak_rokadiya.class);
                    startActivity(intent);

                } else if (position == 1) {
                    Intent intent = new Intent(crop_info.this, pak_telibiya.class);
                    startActivity(intent);

                } else if (position == 2) {
                    Intent intent = new Intent(crop_info.this, pak_kathol.class);
                    startActivity(intent);

                } else if (position == 3) {
                    Intent intent = new Intent(crop_info.this, pak_anaj.class);
                    startActivity(intent);

                } else if (position == 4) {
                    Intent intent = new Intent(crop_info.this, pak_masala.class);
                    startActivity(intent);

                } else if (position == 5) {
                    Intent intent = new Intent(crop_info.this, pak_vegetable.class);
                    startActivity(intent);

                } else if (position == 6) {
                    Intent intent = new Intent(crop_info.this, pak_fruit.class);
                    startActivity(intent);
                }
            }
        });
    }
}