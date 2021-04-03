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

    private RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

    public RetrofitInterface getRetrofitInterface() {
        return retrofitInterface;
    }
}
