package com.omellete.watchlistlive.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.omellete.watchlistlive.db.FavoriteRepository;
import com.omellete.watchlistlive.db.MovieFavoriteModel;

public class FavoriteViewModel extends ViewModel {
    private final FavoriteRepository catalogueRepository;

    public FavoriteViewModel(@NonNull FavoriteRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("xxx", "data cleared");
    }

    public LiveData<PagedList<MovieFavoriteModel>> getAllFavMovie() {
        return new LivePagedListBuilder<>(catalogueRepository.getAllFavMovie(), 10).build();
    }

    public LiveData<PagedList<MovieFavoriteModel>> getAllFavShow() {
        return new LivePagedListBuilder<>(catalogueRepository.getAllFavShow(), 10).build();
    }
}