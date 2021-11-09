package com.omellete.watchlistlive.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.omellete.watchlistlive.data.entity.WatchlistEntity;
import com.omellete.watchlistlive.data.source.MovieCatalogueRepository;

public class DetailItemViewModel extends ViewModel {

    private int itemId;
    private String itemType;
    private final MovieCatalogueRepository movieCatalogueRepository;

    public DetailItemViewModel(MovieCatalogueRepository movieCatalogueRepository) {
        this.movieCatalogueRepository = movieCatalogueRepository;
    }

    void setItemId(int itemId) {
        this.itemId = itemId;
    }

    void setItemType(String itemType) {
        this.itemType = itemType;
    }

    LiveData<WatchlistEntity> getItem() {
        return movieCatalogueRepository.getItem(itemType, itemId);
    }
}
