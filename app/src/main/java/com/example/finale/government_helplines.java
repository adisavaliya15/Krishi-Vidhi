package com.example.finale;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class government_helplines extends MyBaseActivity {

    private static long mLastClickTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=LayoutInflater.from(this);
        View v =inflater.inflate(R.layout.activity_government_helplines,null,false);
        drawer.addView(v,0);
        setTitle("સરકારી ક્ચેરીની વિગતો");

        ListView listView = findViewById(R.id.Helplinelistview);
        String[]  kacheri_names = getResources().getStringArray(R.array.helplines);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.activity_helpline_detail,R.id.row_txt,kacheri_names);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) { // 1000 = 1second
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Intent intent = new Intent(government_helplines.this, government_help2.class);
                intent.putExtra("kacheri_key",position);
                startActivity(intent);

            }
        });
             }
        }