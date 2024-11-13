package com.example.finale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class gallary_big_image extends MyBaseActivity {

    ImageView imageView;
    String name,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_gallary_big_image, null, false);
        drawer.addView(v,0);
        setTitle("ગેલેરી");

        imageView = findViewById(R.id.iv_full);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            name = extras.getString("n");
            url = extras.getString("u");

        }else {
            //   Toast.makeText(this, "Privact account", Toast.LENGTH_SHORT).show();
        }

        Picasso.get().load(url).into(imageView);

    }
}