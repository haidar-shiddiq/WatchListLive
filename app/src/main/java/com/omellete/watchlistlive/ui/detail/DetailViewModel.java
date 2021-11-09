package com.omellete.watchlistlive.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.omellete.watchlistlive.data.WatchlistEntity;
import com.omellete.watchlistlive.repository.WatchlistRepository;

public class DetailViewModel extends ViewModel {

    private int itemId;
    private String itemType;
    private final WatchlistRepository watchlistRepository;

    public DetailViewModel(WatchlistRepository watchlistRepository) {
        this.watchlistRepository = watchlistRepository;
    }

    void setItemId(int itemId) {
        this.itemId = itemId;
    }

    void setItemType(String itemType) {
        this.itemType = itemType;
    }

    LiveData<WatchlistEntity> getItem() {
        return watchlistRepository.getItem(itemType, itemId);
    }
}
