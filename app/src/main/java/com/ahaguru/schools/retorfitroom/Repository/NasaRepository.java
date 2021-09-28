package com.ahaguru.schools.retorfitroom.Repository;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahaguru.schools.retorfitroom.Adapter.RecyclerAdapter;
import com.ahaguru.schools.retorfitroom.Dao.NasaDao;
import com.ahaguru.schools.retorfitroom.Entity.Nasa;
import com.ahaguru.schools.retorfitroom.Retrofit.RetrofitClient;
import com.ahaguru.schools.retorfitroom.Room.NasaRoomDatabase;
import com.ahaguru.schools.retorfitroom.Service.RetrofitService;
import com.ahaguru.schools.retorfitroom.ui.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NasaRepository {

    public String apikey="goLj8jNbuSbNDt3IRwbLGyModq3yUWPKob5zzao7";
    private LiveData<List<Nasa>> getAllNasaDatas;
    private NasaRoomDatabase nasaRoomDatabase;

    public NasaRepository(Application application) {

        nasaRoomDatabase = NasaRoomDatabase.getInstance(application);
        getAllNasaDatas = nasaRoomDatabase.nasaDao().getAllNasaDatas();


    }

    public void insert(List<Nasa> nasaList) {

        new InsertAsynTask(nasaRoomDatabase).execute(nasaList);

//        NasaRoomDatabase.executorService.execute(() -> {
//            nasaRoomDatabase.nasaDao().insert(nasaList);
//        });
    }

    public LiveData<List<Nasa>> getAllNasaDatas() {

        return getAllNasaDatas;

    }

//    static class InsertExecutor {
//
//        private NasaDao nasaDao;
//
//        private PopulateExecutor(NasaRoomDatabase db) {
//            nasaDao = db.nasaDao();
//
//            executorService.shutdown();
//        }
//    }


    static class InsertAsynTask extends AsyncTask<List<Nasa>,Void,Void>{
        private NasaDao nasaDao;

        InsertAsynTask(NasaRoomDatabase nasaRoomDatabase)
        {
            nasaDao = nasaRoomDatabase.nasaDao();

        }
        @Override
        protected Void doInBackground(List<Nasa>... lists) {
//            nasaDao.deleteAll();
            nasaDao.insert(lists[0]);
            return null;
        }
    }

    public void apiRequest(String count) {

        RetrofitClient retrofitClient = new RetrofitClient();
        Call<List<Nasa>> call =  retrofitClient.retrofitService.getAllNasaDatas(apikey, count);
        call.enqueue(new Callback<List<Nasa>>() {

            @Override
            public void onResponse(Call<List<Nasa>> call, Response<List<Nasa>> response) {
                if(response.isSuccessful())
                {

                    insert(response.body());
                    Log.d("main", "onResponse: "+response.body());

                }
            }

            @Override
            public void onFailure(Call<List<Nasa>> call, Throwable t) {

                System.out.println(t);
            }
        });

    }
}
