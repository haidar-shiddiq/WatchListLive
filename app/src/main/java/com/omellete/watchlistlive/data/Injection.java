package com.omellete.watchlistlive.data;

import com.omellete.watchlistlive.repository.WatchlistRepository;
import com.omellete.watchlistlive.repository.RemoteRepository;
import com.omellete.watchlistlive.api.JsonHelper;

public class Injection {

    public static WatchlistRepository provideRepository() {
        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper());
        return WatchlistRepository.getInstance(remoteRepository);
    }
}
