package com.example.finale;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class FarmingTech extends MyBaseActivity {
    String[] farmtech = {"બાગાયતી ખેતી", "હાયડ્રોપોનિક્સ", "ટેરેસ બગીચો", "હર્બલ ગાર્ડન", "નેટ હાઉસ","ઇઝરાઇલ ટેકનોલોજી"};
    private static long mLastClickTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=LayoutInflater.from(this);
        View v =inflater.inflate(R.layout.activity_farming_tech,null,false);
        drawer.addView(v,0);
        setTitle("ખેતીની તકનીકીઓ");

        ListView listView5 = findViewById(R.id.farmTechList);
        farm_tech_adapter farmtech_adapter = new farm_tech_adapter(FarmingTech.this, farmtech);
        listView5.setAdapter(farmtech_adapter);
        listView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) { // 1000 = 1second
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Intent intent = new Intent(FarmingTech.this, FarminTech2.class);
                intent.putExtra("Farming",position);
                startActivity(intent);

            }
        });

    }
}