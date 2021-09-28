package com.ahaguru.schools.retorfitroom.Service;

import com.ahaguru.schools.retorfitroom.Entity.Nasa;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("apod")
    Call<List<Nasa>> getAllNasaDatas(@Query("api_key") String apiKey, @Query("count") String count);
}
