package com.ahaguru.schools.retorfitroom.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ahaguru.schools.retorfitroom.Entity.Nasa;
import com.ahaguru.schools.retorfitroom.Repository.NasaRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private NasaRepository repository;

    private LiveData<List<Nasa>> allNasa;

    public MainViewModel(@NonNull Application application) {
        super(application);

        repository = new NasaRepository(application);
        allNasa = repository.getAllNasaDatas();
    }

    public void retrofitCall(String count) {
        repository.apiRequest(count);
    }
    public void insert(List<Nasa> list)
    {
        repository.insert(list);
    }

    public LiveData<List<Nasa>> allNasa()
    {
        return allNasa;
    }
}
