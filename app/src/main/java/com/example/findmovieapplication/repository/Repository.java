package com.example.findmovieapplication.repository;

import androidx.lifecycle.LiveData;

import com.example.findmovieapplication.model.MovieModel;
import com.example.findmovieapplication.request.RemoteDataSourceApi;

import java.util.List;

public class Repository {

    private RemoteDataSourceApi remoteDataSourceApi;

    private static Repository instance;

    public static Repository getInstance(){

        if (instance == null){

            instance = new Repository();
        }

        return instance;
    }

    private Repository(){

        remoteDataSourceApi = RemoteDataSourceApi.getInstance();
    }


    public LiveData<List<MovieModel>> getSearchedMovies(){

        return remoteDataSourceApi.getSearchedMovies();

    }





}
