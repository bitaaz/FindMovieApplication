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

    public LiveData<List<MovieModel>> getPopMovies(){

        return repository.getPopMovies();
    }

    public void searchMovieApi(String query, int pageNumber){

        repository.searchMovieApi(query, pageNumber);
    }

    public void searchNextPage(){

        repository.searchNextPage();
    }

    public void popMovieApi(int pageNumber){
        repository.popMovieApi(pageNumber);
    }
}
