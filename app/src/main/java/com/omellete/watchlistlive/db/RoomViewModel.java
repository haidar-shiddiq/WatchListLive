package com.omellete.watchlistlive.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RoomViewModel extends AndroidViewModel {

    private FavoriteRepository repository;

    private LiveData<List<MovieFavoriteModel>> allFav;
    private LiveData<List<MovieFavoriteModel>> allFavMovie;
    private LiveData<List<MovieFavoriteModel>> allFavShow;

    public RoomViewModel(@NonNull Application application) {
        super(application);
        repository = new FavoriteRepository(application);
        allFav = repository.getAllFav();
        allFavMovie = repository.getAllFavMovie();
        allFavShow = repository.getAllFavShow();
    }

    public void insert(MovieFavoriteModel model) {
        repository.insert(model);
    }

    public void update(MovieFavoriteModel model) {
        repository.update(model);
    }

    public void delete(MovieFavoriteModel model) {
        repository.delete(model);
    }

    public void deleteAllFav() {
        repository.deleteAllFav();
    }

    public LiveData<List<MovieFavoriteModel>> getAllFav() {
        return allFav;
    }
    public LiveData<List<MovieFavoriteModel>> getAllFavMovie() {
        return allFavMovie;
    }
    public LiveData<List<MovieFavoriteModel>> getAllFavShow() {
        return allFavShow;
    }
}
