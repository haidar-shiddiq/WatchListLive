package com.omellete.watchlistlive.ui.movies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.omellete.watchlistlive.data.WatchlistEntity;
import com.omellete.watchlistlive.repository.WatchlistRepository;

import java.util.ArrayList;

public class MoviesViewModel extends ViewModel {

    private final WatchlistRepository watchlistRepository;

    public MoviesViewModel(WatchlistRepository watchlistRepository) {
        this.watchlistRepository = watchlistRepository;
    }

    public LiveData<ArrayList<WatchlistEntity>> getMovies() {
        return watchlistRepository.getMovies();
    }
}
