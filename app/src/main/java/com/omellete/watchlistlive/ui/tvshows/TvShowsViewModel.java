package com.omellete.watchlistlive.ui.tvshows;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.omellete.watchlistlive.data.WatchlistEntity;
import com.omellete.watchlistlive.repository.WatchlistRepository;

import java.util.ArrayList;

public class TvShowsViewModel extends ViewModel {

    private final WatchlistRepository watchlistRepository;

    public TvShowsViewModel(WatchlistRepository watchlistRepository) {
        this.watchlistRepository = watchlistRepository;
    }

    public LiveData<ArrayList<WatchlistEntity>> getTvShows() {
        return watchlistRepository.getTvShows();
    }
}