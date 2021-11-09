package com.omellete.watchlistlive.ui.movies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.omellete.watchlistlive.data.entity.WatchlistEntity;
import com.omellete.watchlistlive.data.source.MovieCatalogueRepository;

import java.util.ArrayList;

public class MoviesViewModel extends ViewModel {

    private final MovieCatalogueRepository movieCatalogueRepository;

    public MoviesViewModel(MovieCatalogueRepository movieCatalogueRepository) {
        this.movieCatalogueRepository = movieCatalogueRepository;
    }

    LiveData<ArrayList<WatchlistEntity>> getMovies() {
        return movieCatalogueRepository.getMovies();
    }
}
