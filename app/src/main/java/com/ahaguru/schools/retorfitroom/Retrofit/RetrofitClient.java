package com.ahaguru.schools.retorfitroom.Retrofit;

import com.ahaguru.schools.retorfitroom.Service.RetrofitService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.nasa.gov/planetary/";


    public RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static RetrofitClient getInstance(){
        if(retrofitClient == null){
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    public RetrofitService retrofitService(){
        return retrofit.create(RetrofitService.class);
    }
}
