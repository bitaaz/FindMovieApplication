package com.example.findmovieapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.findmovieapplication.model.MovieModel;
import com.example.findmovieapplication.repository.Repository;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private Repository repository;

    public MovieViewModel(){

        repository = Repository.getInstance();
    }

    public LiveData<List<MovieModel>> getSearchedMovies(){

        return repository.getSearchedMovies();
    }
}