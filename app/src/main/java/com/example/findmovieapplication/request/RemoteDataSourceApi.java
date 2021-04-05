package com.example.findmovieapplication.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.findmovieapplication.AppExecutor;
import com.example.findmovieapplication.model.MovieModel;
import com.example.findmovieapplication.response.MovieSearchResponse;
import com.example.findmovieapplication.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class RemoteDataSourceApi {

    private MutableLiveData<List<MovieModel>> searchedMovies;

    private SearchedMoviesRunnable searchedMoviesRunnable;

    private MutableLiveData<List<MovieModel>> popMovies;

    private PopMoviesRunnable popMoviesRunnable;


    private static RemoteDataSourceApi instance;

    public static RemoteDataSourceApi getInstance(){

        if (instance == null){

            instance = new RemoteDataSourceApi();
        }

        return instance;
    }

    private RemoteDataSourceApi(){

        searchedMovies = new MutableLiveData<>();
        popMovies = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getSearchedMovies(){

        return  searchedMovies;
    }

    public LiveData<List<MovieModel>> getPopMovies(){

        return popMovies;
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

        private Call<MovieSearchResponse> getMovies(String query, int pageNumber){

            return RetrofitBuilder.getRetrofitInterface().searchedMovies(Constants.API_KEY, query, pageNumber);
        }

        private void cancelRequest(){
            cancelRequest = true;
            Log.v("Tag", "Cancel search request ");
        }


        @Override
        public void run() {

            try {

                Response response = getMovies(query, pageNumber).execute();
                if (cancelRequest){
                    return;
                }

                if (response.code() == 200){

                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());

                    if (pageNumber == 1){

                        searchedMovies.postValue(list);
                    }
                    else {

                        List<MovieModel> currentMovies = searchedMovies.getValue();
                        currentMovies.addAll(list);
                        searchedMovies.postValue(currentMovies);
                    }
                }

                else {

                    String error = response.errorBody().toString();
                    Log.v("Tag", "Error: " + error);
                    searchedMovies.postValue(null);
                }

            } catch (Exception e) {
                e.printStackTrace();
                searchedMovies.postValue(null);
            }



        }
    }

    public void searchMovieApi(String query, int pageNumber){

        if (searchedMoviesRunnable != null){

            searchedMoviesRunnable = null;
        }

        searchedMoviesRunnable = new SearchedMoviesRunnable(query,pageNumber);

        //future for using thread
        final Future handler = AppExecutor.getInstance().getNetworkIO().submit(searchedMoviesRunnable);

        AppExecutor.getInstance().getNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {

                handler.cancel(true);

            }
        }, 3000, TimeUnit.MILLISECONDS);
    }


    private class PopMoviesRunnable implements Runnable{

        private int pageNumber;
        boolean cancelRequest;

        public PopMoviesRunnable(int pageNumber) {
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }


        private Call<MovieSearchResponse> getPopMovie(int pageNumber){

            return RetrofitBuilder.getRetrofitInterface().popMovies(Constants.API_KEY, pageNumber);

        }

        @Override
        public void run() {

            try {

                Response response = getPopMovie(pageNumber).execute();
                if (cancelRequest){

                    return;
                }
                if (response.code() == 200){

                    List<MovieModel> popList = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());

                    if (pageNumber == 1){

                        popMovies.postValue(popList);
                    }
                    else {

                        List<MovieModel> currentPopMovies = popMovies.getValue();
                        currentPopMovies.addAll(popList);
                        popMovies.postValue(currentPopMovies);
                    }

                }
                else {

                    Log.v("Tag", "Error: " + response.errorBody().toString());
                    popMovies.postValue(null);
                }

            } catch (Exception e) {
                e.printStackTrace();
                popMovies.postValue(null);
            }

        }
    }

    public void popMovieApi(int pageNumber){

        if (popMoviesRunnable != null){
            popMoviesRunnable = null;
        }

        popMoviesRunnable = new PopMoviesRunnable(pageNumber);

        final Future handler = AppExecutor.getInstance().getNetworkIO().submit(popMoviesRunnable);

        AppExecutor.getInstance().getNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {

                handler.cancel(true);

            }
        },3000, TimeUnit.MILLISECONDS);
    }
}