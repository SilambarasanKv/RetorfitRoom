package com.ahaguru.schools.retorfitroom.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ahaguru.schools.retorfitroom.Adapter.RecyclerAdapter;
import com.ahaguru.schools.retorfitroom.Entity.Nasa;
import com.ahaguru.schools.retorfitroom.R;
import com.ahaguru.schools.retorfitroom.Repository.NasaRepository;
import com.ahaguru.schools.retorfitroom.Retrofit.RetrofitClient;
import com.ahaguru.schools.retorfitroom.Service.RetrofitService;
import com.ahaguru.schools.retorfitroom.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1, button2, button3, button4;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    NasaRepository nasaRepository;
    MainViewModel mainViewModel;
    List<Nasa> nasaList;
    ActivityMainBinding binding;
    public String count = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        button1 = binding.btn1;
        button2 = binding.btn2;
        button3 = binding.btn3;
        button4 = binding.btn4;
        recyclerView = binding.recyclerView;


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter(nasaList, this);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.allNasa().observe(this, new Observer<List<Nasa>>() {
            @Override
            public void onChanged(List<Nasa> nasaList) {

                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.getAllNasaDatas(nasaList);
                recyclerAdapter.updateList(nasaList);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn1:

                count="1";
                mainViewModel.retrofitCall(count);
                Toast.makeText(MainActivity.this, "One new data inserted", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn2:

                count="2";
                mainViewModel.retrofitCall(count);
                Toast.makeText(MainActivity.this, "Two new data inserted", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn3:

                count="3";
                mainViewModel.retrofitCall(count);
                Toast.makeText(MainActivity.this, "Three new data inserted", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn4:

                count="4";
                mainViewModel.retrofitCall(count);
                Toast.makeText(MainActivity.this, "Four new data inserted", Toast.LENGTH_SHORT).show();
                break;


        }

    }

}