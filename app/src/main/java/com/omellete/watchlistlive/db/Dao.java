package com.omellete.watchlistlive.db;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MovieFavoriteModel model);

    @Update
    void update(MovieFavoriteModel model);

    @Delete
    void delete(MovieFavoriteModel model);

    @Query("DELETE FROM favorite_table")
    void deleteAllFav();

    @Query("SELECT * FROM favorite_table ORDER BY username ASC")
    LiveData<List<MovieFavoriteModel>> getAllFav();

    @Query("SELECT * FROM favorite_table WHERE type = 'MOVIES'")
    LiveData<List<MovieFavoriteModel>> getAllFavMovie();

    @Query("SELECT * FROM favorite_table WHERE type = 'SHOWS'")
    LiveData<List<MovieFavoriteModel>> getAllFavShow();
}