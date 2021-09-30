package com.ahaguru.schools.retorfitroom.Room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ahaguru.schools.retorfitroom.Dao.NasaDao;
import com.ahaguru.schools.retorfitroom.Entity.Nasa;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Nasa.class}, version = 3, exportSchema = false)
public abstract class NasaRoomDatabase extends RoomDatabase {

    private static volatile NasaRoomDatabase INSTANCE;

    public abstract NasaDao nasaDao();

    public Executor executor = Executors.newSingleThreadExecutor();

    public static NasaRoomDatabase getInstance(Context context){
        if(INSTANCE == null)
        {
            synchronized (NasaRoomDatabase.class){
                if(INSTANCE == null)
                {
                    INSTANCE= Room.databaseBuilder(context,NasaRoomDatabase.class,
                            "nasa_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
