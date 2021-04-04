package com.example.findmovieapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.findmovieapplication.model.MovieModel;
import com.example.findmovieapplication.viewmodel.MovieViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MovieViewModel movieViewModel;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        observer();

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movieViewModel.searchMovieApi("fast", 1);

            }
        });
    }

    private void observer(){
        movieViewModel.getSearchedMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels != null){

                    for (MovieModel movieModel: movieModels){

                        Log.v("Tag", "Titles: " + movieModel.getTitle());
                    }
                }

            }
        });
    }
}