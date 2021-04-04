package com.example.findmovieapplication.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findmovieapplication.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title, releaseDate, language;
    RatingBar ratingBar;
    ImageView imageView;

    OnMovieListener onMovieListener;


    public MovieViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {

        super(itemView);

        this.onMovieListener = onMovieListener;

        title = itemView.findViewById(R.id.movie_title);
        releaseDate = itemView.findViewById(R.id.movie_release_date);
        language = itemView.findViewById(R.id.movie_language);
        ratingBar = itemView.findViewById(R.id.movie_rating);
        imageView = itemView.findViewById(R.id.movie_img);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        onMovieListener.onMovieClick(getAdapterPosition());

    }
}
