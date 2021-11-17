package com.omellete.watchlistlive.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class RoomViewModel extends AndroidViewModel {

    private FavoriteRepository repository;

    private DataSource.Factory<Integer, MovieFavoriteModel> allFav;
    private DataSource.Factory<Integer, MovieFavoriteModel> allFavMovie;
    private DataSource.Factory<Integer, MovieFavoriteModel> getSearch;
    private DataSource.Factory<Integer, MovieFavoriteModel> allFavMovieDesc;
    private DataSource.Factory<Integer, MovieFavoriteModel> allFavShow;
    private DataSource.Factory<Integer, MovieFavoriteModel> delFav;
    private Integer movieId;

    public RoomViewModel(@NonNull Application application) {
        super(application);
        repository = new FavoriteRepository(application);
        allFav = repository.getAllFav();
        allFavMovie = repository.getAllFavMovie();
        allFavMovie = repository.getAllFavMovieDesc();
        allFavShow = repository.getAllFavShow();
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public void insert(MovieFavoriteModel model) {
        repository.insert(model);
    }

    public void delete(MovieFavoriteModel model) {
        repository.delete(model);
    }

    public void delFav(int id) {
        repository.delFav(id);
    }


    public void deleteAllFav() {
        repository.deleteAllFav();
    }

    public LiveData<MovieFavoriteModel> getMovieByIdRoom() {
        return repository.getMovieByIdRoom(movieId);
    }

    LiveData<PagedList<MovieFavoriteModel>> getAllFav() {
        return new LivePagedListBuilder<>(repository.getAllFav(), 20).build();
    }

    public LiveData<PagedList<MovieFavoriteModel>> getAllFavMovie() {
        return new LivePagedListBuilder<>(repository.getAllFavMovie(), 20).build();
    }

    public LiveData<PagedList<MovieFavoriteModel>> getAllFavMovieDesc() {
        return new LivePagedListBuilder<>(repository.getAllFavMovieDesc(), 20).build();
    }

    public LiveData<PagedList<MovieFavoriteModel>> getAllFavShow() {
        return new LivePagedListBuilder<>(repository.getAllFavShow(), 20).build();
    }

    public LiveData<PagedList<MovieFavoriteModel>> getAllFavShowDesc() {
        return new LivePagedListBuilder<>(repository.getAllFavShowDesc(), 20).build();
    }

}
