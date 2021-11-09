package com.omellete.watchlistlive.data.source;

import androidx.lifecycle.LiveData;

import com.omellete.watchlistlive.data.entity.WatchlistEntity;

import java.util.ArrayList;

public interface MovieCatalogueDataSource {
    LiveData<ArrayList<WatchlistEntity>> getMovies();
    LiveData<ArrayList<WatchlistEntity>> getTvShows();
    LiveData<WatchlistEntity> getItem(String itemType, int id);
}
