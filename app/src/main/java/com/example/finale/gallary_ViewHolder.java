package com.example.finale;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class gallary_ViewHolder extends RecyclerView.ViewHolder {

    ProgressBar progressBar;
    CardView cardView;
    public gallary_ViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void setDetails(Context context,String image){

        ImageView imageView = itemView.findViewById(R.id.iv_item);
        cardView = itemView.findViewById(R.id.cv_item);

        Picasso.get().load(image).placeholder(R.drawable.progress_animation).error(R.drawable.common_google_signin_btn_icon_dark).into(imageView);


    }
}
