package com.omellete.watchlistlive.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.List;

public class RoomViewModel extends AndroidViewModel {

    private FavoriteRepository repository;

    private DataSource.Factory<Integer, MovieFavoriteModel> allFav;
    private DataSource.Factory<Integer, MovieFavoriteModel> allFavMovie;
    private DataSource.Factory<Integer, MovieFavoriteModel> allFavShow;

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

    LiveData<PagedList<MovieFavoriteModel>> getAllFav() {
        return new LivePagedListBuilder<>(repository.getAllFav(), 20).build();
    }
    public LiveData<PagedList<MovieFavoriteModel>> getAllFavMovie() {
        return new LivePagedListBuilder<>(repository.getAllFavMovie(), 20).build();
    }
    public LiveData<PagedList<MovieFavoriteModel>> getAllFavShow() {
        return new LivePagedListBuilder<>(repository.getAllFavShow(), 20).build();
    }

    //    public LiveData<List<MovieFavoriteModel>> getAllFav() {
//        return allFav;
//    }
//    public LiveData<List<MovieFavoriteModel>> getAllFavMovie() {
//    return allFavMovie;
//}
//    public LiveData<List<MovieFavoriteModel>> getAllFavShow() {
//        return allFavShow;
//    }

}
