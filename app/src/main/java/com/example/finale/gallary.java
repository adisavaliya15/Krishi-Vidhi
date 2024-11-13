package com.example.finale;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class gallary extends MyBaseActivity {

    RecyclerView recyclerView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);

        View v = inflater.inflate(R.layout.activity_gallary, null, false);
        drawer.addView(v,0);
        setTitle("ગેલેરી");



        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        reference = database.getReference("Data");
        imageView = findViewById(R.id.rImageview);

    }

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerOptions<gallary_Member> options2 =
                new FirebaseRecyclerOptions.Builder<gallary_Member>()
                        .setQuery(reference, gallary_Member.class)
                        .build();

        FirebaseRecyclerAdapter<gallary_Member, gallary_ViewHolder> firebaseRecyclerAdapter2 =
                new FirebaseRecyclerAdapter<gallary_Member, gallary_ViewHolder>(options2) {
                    @Override
                    protected void onBindViewHolder(@NonNull gallary_ViewHolder holder, int position, @NonNull gallary_Member model) {

                        holder.setDetails(getApplicationContext(),model.getImage());
                        String url = getItem(position).getImage();


                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent = new Intent(gallary.this, gallary_big_image.class);
                                intent.putExtra("u",url);
                                startActivity(intent);

                            }
                        });

                    }

                    @NonNull
                    @Override
                    public gallary_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.gallary_item,parent,false);

                        return new gallary_ViewHolder(view);
                    }
                };


        firebaseRecyclerAdapter2.startListening();
//
//         GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
//        videorv.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(firebaseRecyclerAdapter2);
    }
}