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
import com.omellete.watchlistlive.ui.tvshows.TvShowsViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

public class TvShowsViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private TvShowsViewModel viewModel;
    private final WatchlistRepository watchlistRepository = mock(WatchlistRepository.class);

    @Mock
    private final Observer<ArrayList<WatchlistEntity>> observer = mock(Observer.class);

    @Before
    public void setUp() {
        viewModel = new TvShowsViewModel(watchlistRepository);
    }

    @Test
    public void getShows() {
        ArrayList<WatchlistEntity> dummyShows = LocalData.getDummyTvShows();
        MutableLiveData<ArrayList<WatchlistEntity>> shows = new MutableLiveData<>();
        shows.setValue(dummyShows);
        when(watchlistRepository.getTvShows()).thenReturn(shows);

        ArrayList<WatchlistEntity> showResults = viewModel.getTvShows().getValue();
        assertNotNull(showResults);
        assertEquals(3, showResults.size());

        viewModel.getTvShows().observeForever(observer);
        verify(observer).onChanged(dummyShows);
    }
}