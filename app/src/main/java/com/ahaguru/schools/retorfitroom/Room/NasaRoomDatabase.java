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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Nasa.class}, version = 1, exportSchema = false)
public abstract class NasaRoomDatabase extends RoomDatabase {

    private static NasaRoomDatabase instance;

    public abstract NasaDao nasaDao();

    public static final ExecutorService executorService =
            Executors.newSingleThreadExecutor();

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
        @Override
        public void onCreate(@NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateAsyncTask(instance);

        }
    };

    private static class PopulateAsyncTask extends AsyncTask<Void, Void, Void> {
        private NasaDao nasaDao;

        private PopulateAsyncTask(NasaRoomDatabase db) {
            nasaDao = db.nasaDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
    
//    public static class PopulateExecutor {
//
//        private NasaDao nasaDao;
//
//        private PopulateExecutor(NasaRoomDatabase db) {
//            nasaDao = db.nasaDao();
//
//            executorService.shutdown();
//        }
//
//    }
}
