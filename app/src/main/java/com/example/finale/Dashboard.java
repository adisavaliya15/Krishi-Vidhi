package com.example.finale;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Dashboard extends MyBaseActivity {

    private LocationRequest locationRequest;
    public static final int REQUEST_CHECK_SETTING = 1001;
    private static long mLastClickTime = 0;

    int[] myimages = {R.drawable.ic_growing_plant, R.drawable.farmer, R.drawable.ic_cow, R.drawable.ic_stock_market, R.drawable.ic_placeholder, R.drawable.ic_scheme, R.drawable.ic_gallery, R.drawable.ic_helpline, R.drawable.ic_growing_plant};
    String[] names = {"પાકની વિગતો", "ખેતીની તકનીકીઓ", "પશુપાલન", "બજાર ભાવ", "હવામાન", "સરકારી યોજનાઓ", "ગેલેરી", "સરકારી ક્ચેરીની વિગતો", "7x12 ઉતારા"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_dashboard, null, false);
        drawer.addView(v, 0);

        GridView gridView = findViewById(R.id.gridview);
        MainAdapter adapter = new MainAdapter(Dashboard.this, myimages, names);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) { // 1000 = 1second
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                if (position == 0) {
                    Intent intent = new Intent(Dashboard.this, crop_info.class);
                    startActivity(intent);

                } else if (position == 1) {
                    Intent intent = new Intent(Dashboard.this, FarmingTech.class);
                    startActivity(intent);

                } else if (position == 2) {
                    Intent intent = new Intent(Dashboard.this, animal_husbandry.class);
                    startActivity(intent);

                } else if (position == 3) {
                    Intent intent = new Intent(Dashboard.this, Mandi_Activity.class);
                    startActivity(intent);

                } else if (position == 4) {
                    Intent intent = new Intent(Dashboard.this, weather.class);
                    startActivity(intent);

                } else if (position == 5) {

                    Intent intent = new Intent(Dashboard.this, government_schemes.class);
                    startActivity(intent);

                } else if (position == 6) {
                    Intent intent = new Intent(Dashboard.this, gallary.class);
                    startActivity(intent);

                } else if (position == 7) {
                    Intent intent = new Intent(Dashboard.this, government_helplines.class);
                    startActivity(intent);

                } else if (position == 8) {
                    Intent intent = new Intent(Dashboard.this, utaara.class);
                    startActivity(intent);

                }
            }
        });
        
    }

    //To prevent user to going to previous activity
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}
