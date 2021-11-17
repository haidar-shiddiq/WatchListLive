package com.omellete.watchlistlive.db;

import androidx.lifecycle.LiveData;
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

    @Query("SELECT * FROM favorite_table WHERE id =:id")
    LiveData<MovieFavoriteModel> getMovieById(int id);

    @Query("SELECT * FROM favorite_table ORDER BY username ASC")
    DataSource.Factory<Integer, MovieFavoriteModel> getAllFav();

    @Query("SELECT * FROM favorite_table WHERE type = 'MOVIES' ORDER BY username ASC")
    DataSource.Factory<Integer, MovieFavoriteModel> getAllFavMovie();

    @Query("SELECT * FROM favorite_table WHERE type = 'MOVIES' ORDER BY username DESC")
    DataSource.Factory<Integer, MovieFavoriteModel> getAllFavMovieDesc();

    @Query("SELECT * FROM favorite_table WHERE type = 'SHOWS' ORDER BY username ASC")
    DataSource.Factory<Integer, MovieFavoriteModel> getAllFavShow();

    @Query("SELECT * FROM favorite_table WHERE type = 'SHOWS' ORDER BY username DESC")
    DataSource.Factory<Integer, MovieFavoriteModel> getAllFavShowDesc();

    @Query("DELETE FROM favorite_table WHERE id = :id")
    void delFav(int id);

}