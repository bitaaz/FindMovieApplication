package com.example.findmovieapplication.request;

import com.example.findmovieapplication.utils.Constants;
import com.example.findmovieapplication.utils.RetrofitInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

    public static RetrofitInterface getRetrofitInterface() {
        return retrofitInterface;
    }
}
