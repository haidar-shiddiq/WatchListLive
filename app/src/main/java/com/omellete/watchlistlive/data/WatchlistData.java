package com.omellete.watchlistlive.data;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;

public interface WatchlistData {
    LiveData<ArrayList<WatchlistEntity>> getMovies();
    LiveData<ArrayList<WatchlistEntity>> getTvShows();
    LiveData<WatchlistEntity> getItem(String itemType, int id);
}
