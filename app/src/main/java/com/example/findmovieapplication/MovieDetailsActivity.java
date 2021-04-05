package com.example.findmovieapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.findmovieapplication.model.MovieModel;

import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView title, overview;
    private RatingBar ratingBar;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        title = findViewById(R.id.title_details);
        ratingBar = findViewById(R.id.rating_details);
        overview = findViewById(R.id.overview);
        imageView = findViewById(R.id.image_details);

        getDataFromIntent();
    }

    private void getDataFromIntent(){

        if (getIntent().hasExtra("movie")){

            MovieModel movieModel = getIntent().getParcelableExtra("movie");

            title.setText(movieModel.getTitle());
            ratingBar.setRating((movieModel.getVote_average())/2);
            overview.setText(movieModel.getOverview());

            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500/" + movieModel.getPoster_path())
                    .into(imageView);
        }

    }
}