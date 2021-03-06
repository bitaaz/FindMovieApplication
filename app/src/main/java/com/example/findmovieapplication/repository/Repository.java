package com.example.findmovieapplication.repository;

import androidx.lifecycle.LiveData;

import com.example.findmovieapplication.model.MovieModel;
import com.example.findmovieapplication.request.RemoteDataSourceApi;

import java.util.List;

public class Repository {

    private RemoteDataSourceApi remoteDataSourceApi;

    private String query;

    private int pageNumber;

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

    public LiveData<List<MovieModel>> getPopMovies(){

        return remoteDataSourceApi.getPopMovies();
    }


    public void searchMovieApi(String searchedQuery, int searchedPageNumber){
        query = searchedQuery;
        pageNumber = searchedPageNumber;

        remoteDataSourceApi.searchMovieApi(query, pageNumber);

    }

    public void searchNextPage(){
        searchMovieApi(query, pageNumber + 1);
    }

    public void popMovieApi(int popPageNumber){

        pageNumber = popPageNumber;

        remoteDataSourceApi.popMovieApi(pageNumber);

    }



}
