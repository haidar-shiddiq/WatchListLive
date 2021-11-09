package com.omellete.watchlistlive.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.omellete.watchlistlive.data.source.MovieCatalogueRepository;
import com.omellete.watchlistlive.di.Injection;
import com.omellete.watchlistlive.ui.detail.DetailItemViewModel;
import com.omellete.watchlistlive.ui.movies.MoviesViewModel;
import com.omellete.watchlistlive.ui.tvshows.TvShowsViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory INSTANCE;

    private final MovieCatalogueRepository movieCatalogueRepository;

    private ViewModelFactory(MovieCatalogueRepository movieCatalogueRepository) {
        this.movieCatalogueRepository = movieCatalogueRepository;
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
        if (modelClass.isAssignableFrom(DetailItemViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailItemViewModel(movieCatalogueRepository);
        } else if (modelClass.isAssignableFrom(MoviesViewModel.class)) {
            //noinspection unchecked
            return (T) new MoviesViewModel(movieCatalogueRepository);
        } else if (modelClass.isAssignableFrom(TvShowsViewModel.class)) {
            //noinspection unchecked
            return (T) new TvShowsViewModel(movieCatalogueRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class named " + modelClass.getName());
    }
}