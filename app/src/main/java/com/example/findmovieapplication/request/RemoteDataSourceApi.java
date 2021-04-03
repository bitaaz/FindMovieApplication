package com.example.findmovieapplication.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.findmovieapplication.model.MovieModel;

import java.util.List;

public class RemoteDataSourceApi {

    private MutableLiveData<List<MovieModel>> searchedMovies;

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

}