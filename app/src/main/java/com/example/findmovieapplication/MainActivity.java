package com.example.findmovieapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.findmovieapplication.adapter.MovieRecyclerViewAdapter;
import com.example.findmovieapplication.adapter.OnMovieListener;
import com.example.findmovieapplication.model.MovieModel;
import com.example.findmovieapplication.viewmodel.MovieViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMovieListener {

    private MovieViewModel movieViewModel;

    private RecyclerView recyclerView;

    private MovieRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        observer();
        configureRecyclerView();

        searchMovieApi("fast", 1);


    }

    private void observer(){
        movieViewModel.getSearchedMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels != null){

                    for (MovieModel movieModel: movieModels){

                        Log.v("Tag", "Titles: " + movieModel.getTitle());
                        adapter.setMovies(movieModels);
                    }
                }

            }
        });
    }

    private void searchMovieApi(String query, int pageNumber){

        movieViewModel.searchMovieApi(query, pageNumber);
    }

    private void configureRecyclerView(){

        adapter = new MovieRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {

                if(!recyclerView.canScrollVertically(1)){

                    movieViewModel.searchNextPage();


                }


            }
        });

    }

    @Override
    public void onMovieClick(int position) {

    }
}