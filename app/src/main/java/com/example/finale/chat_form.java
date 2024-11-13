package com.example.finale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class chat_form extends MyBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=LayoutInflater.from(this);
        View v =inflater.inflate(R.layout.activity_chat_form,null,false);
        drawer.addView(v,0);
        setTitle("કૃષિ-ચર્ચા");
    }
}