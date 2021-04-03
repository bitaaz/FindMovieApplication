package com.example.findmovieapplication.viewmodel;

import androidx.lifecycle.LiveData;

import com.example.findmovieapplication.model.MovieModel;
import com.example.findmovieapplication.repository.Repository;

import java.util.List;

public class ViewModel {

    private Repository repository;

    public ViewModel(){

        repository = Repository.getInstance();
    }

    public LiveData<List<MovieModel>> getSearchedMovies(){

        return repository.getSearchedMovies();
    }
}
