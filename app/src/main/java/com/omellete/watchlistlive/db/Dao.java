package com.omellete.watchlistlive.db;

import androidx.paging.DataSource;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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
    DataSource.Factory<Integer, MovieFavoriteModel> getAllFav();

    @Query("SELECT * FROM favorite_table WHERE type = 'MOVIES'")
    DataSource.Factory<Integer, MovieFavoriteModel> getAllFavMovie();
//    LiveData<List<MovieFavoriteModel>> getAllFavMovie();

    @Query("SELECT * FROM favorite_table WHERE type = 'SHOWS'")
    DataSource.Factory<Integer, MovieFavoriteModel> getAllFavShow();
//    LiveData<List<MovieFavoriteModel>> getAllFavShow();
}