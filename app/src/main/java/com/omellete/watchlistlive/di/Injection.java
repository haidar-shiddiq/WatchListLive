package com.omellete.watchlistlive.di;

import com.omellete.watchlistlive.data.source.MovieCatalogueRepository;
import com.omellete.watchlistlive.data.source.remote.RemoteRepository;
import com.omellete.watchlistlive.utils.JsonHelper;

public class Injection {

    public static MovieCatalogueRepository provideRepository() {
        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper());
        return MovieCatalogueRepository.getInstance(remoteRepository);
    }
}
