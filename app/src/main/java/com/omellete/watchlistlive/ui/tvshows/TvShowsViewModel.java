package com.omellete.watchlistlive.ui.tvshows;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.omellete.watchlistlive.data.entity.WatchlistEntity;
import com.omellete.watchlistlive.data.source.MovieCatalogueRepository;

import java.util.ArrayList;

public class TvShowsViewModel extends ViewModel {

    private final MovieCatalogueRepository movieCatalogueRepository;

    public TvShowsViewModel(MovieCatalogueRepository movieCatalogueRepository) {
        this.movieCatalogueRepository = movieCatalogueRepository;
    }

    LiveData<ArrayList<WatchlistEntity>> getTvShows() {
        return movieCatalogueRepository.getTvShows();
    }
}