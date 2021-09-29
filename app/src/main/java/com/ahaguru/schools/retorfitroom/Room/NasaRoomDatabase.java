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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Nasa.class}, version = 2, exportSchema = false)
public abstract class NasaRoomDatabase extends RoomDatabase {

    private static NasaRoomDatabase instance;

    public abstract NasaDao nasaDao();

    private static final ExecutorService service = Executors.newSingleThreadExecutor();

    public static synchronized NasaRoomDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NasaRoomDatabase.class, "nasa_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;

    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {

        NasaDao nasaDao;

        @Override
        public void onCreate(@NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateExecutor(instance);

        }
    };

    private static class PopulateExecutor {
        private NasaDao nasaDao;

        private PopulateExecutor(NasaRoomDatabase db) {
            nasaDao = db.nasaDao();

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    nasaDao.deleteAll();
                }
            });
        }
    }



}
