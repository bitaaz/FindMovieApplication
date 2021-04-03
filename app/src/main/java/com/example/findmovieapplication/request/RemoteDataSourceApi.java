package com.example.findmovieapplication.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.findmovieapplication.model.MovieModel;

import java.util.List;

public class RemoteDataSourceApi {

    private MutableLiveData<List<MovieModel>> searchedMovies;

    private SearchedMoviesRunnable searchedMoviesRunnable;


    private static RemoteDataSourceApi instance;

    public static RemoteDataSourceApi getInstance(){

        if (instance == null){

            instance = new RemoteDataSourceApi();
        }

        return instance;
    }

    private RemoteDataSourceApi(){

        searchedMovies = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getSearchedMovies(){

        return  searchedMovies;
    }


    private class SearchedMoviesRunnable implements Runnable{

        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public SearchedMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            this.cancelRequest = false;
        }




        @Override
        public void run() {

        }
    }

}