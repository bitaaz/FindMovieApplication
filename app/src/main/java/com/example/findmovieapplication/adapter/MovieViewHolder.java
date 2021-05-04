package com.example.findmovieapplication.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findmovieapplication.R;
import com.example.findmovieapplication.databinding.MovieListItemBinding;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title, releaseDate, language;
    RatingBar ratingBar;
    ImageView imageView;

    OnMovieListener onMovieListener;


    public MovieViewHolder(MovieListItemBinding binding, OnMovieListener onMovieListener) {

        super(binding.getRoot());

        this.onMovieListener = onMovieListener;

        title = binding.movieTitle;
        releaseDate = binding.movieReleaseDate;
        language = binding.movieLanguage;
        ratingBar = binding.movieRating;
        imageView = binding.movieImg;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        onMovieListener.onMovieClick(getAdapterPosition());

    }
}
