package com.omellete.watchlistlive.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.omellete.watchlistlive.repository.WatchlistRepository;
import com.omellete.watchlistlive.data.Injection;
import com.omellete.watchlistlive.ui.detail.DetailViewModel;
import com.omellete.watchlistlive.ui.movies.MoviesViewModel;
import com.omellete.watchlistlive.ui.tvshows.TvShowsViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory INSTANCE;

    private final WatchlistRepository watchlistRepository;

    private ViewModelFactory(WatchlistRepository watchlistRepository) {
        this.watchlistRepository = watchlistRepository;
    }

    public static ViewModelFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null)
                    INSTANCE = new ViewModelFactory(Injection.provideRepository());
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailViewModel(watchlistRepository);
        } else if (modelClass.isAssignableFrom(MoviesViewModel.class)) {
            //noinspection unchecked
            return (T) new MoviesViewModel(watchlistRepository);
        } else if (modelClass.isAssignableFrom(TvShowsViewModel.class)) {
            //noinspection unchecked
            return (T) new TvShowsViewModel(watchlistRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class named " + modelClass.getName());
    }
}