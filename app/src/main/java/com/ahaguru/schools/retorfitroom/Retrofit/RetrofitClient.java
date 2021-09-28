package com.ahaguru.schools.retorfitroom.Retrofit;

import com.ahaguru.schools.retorfitroom.Service.RetrofitService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://api.nasa.gov/planetary/";

    Retrofit retrofit = new retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RetrofitService retrofitService = retrofit.create(RetrofitService.class);

}
