package com.omellete.watchlistlive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.omellete.watchlistlive.data.WatchlistEntity;
import com.omellete.watchlistlive.repository.WatchlistRepository;
import com.omellete.watchlistlive.ui.movies.MoviesViewModel;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

public class MoviesViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private MoviesViewModel viewModel;
    private final WatchlistRepository watchlistRepository = mock(WatchlistRepository.class);

    @Mock
    private final Observer<ArrayList<WatchlistEntity>> observer = mock(Observer.class);

    @Before
    public void setUp() {
        viewModel = new MoviesViewModel(watchlistRepository);
    }

    @Test
    public void getMovies() {
        ArrayList<WatchlistEntity> dummyMovies = LocalData.getDummyMovies();
        MutableLiveData<ArrayList<WatchlistEntity>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);
        when(watchlistRepository.getMovies()).thenReturn(movies);

        ArrayList<WatchlistEntity> movieResults = viewModel.getMovies().getValue();
        assertNotNull(movieResults);
        assertEquals(3, movieResults.size());

        viewModel.getMovies().observeForever(observer);
        verify(observer).onChanged(dummyMovies);
    }
}