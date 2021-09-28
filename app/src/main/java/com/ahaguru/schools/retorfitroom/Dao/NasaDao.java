package com.ahaguru.schools.retorfitroom.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ahaguru.schools.retorfitroom.Entity.Nasa;

import java.util.List;

@Dao
public interface NasaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Nasa> nasaList);

    @Update
    void update(Nasa nasa);
    @Query("SELECT * FROM nasa_table ORDER BY id DESC")
    LiveData<List<Nasa>> getAllNasaDatas();

    @Delete
    void delete(Nasa nasa);

    @Query("DELETE FROM nasa_table")
    void deleteAll();


}
