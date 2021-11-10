package com.omellete.watchlistlive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.omellete.watchlistlive.api.WatchlistResponse;
import com.omellete.watchlistlive.data.WatchlistEntity;
import com.omellete.watchlistlive.repository.RemoteRepository;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

public class WatchlistRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private final RemoteRepository remote = mock(RemoteRepository.class);
    private LocalDataRepository movieCatalogueRepository = new LocalDataRepository(remote);

    private ArrayList<WatchlistResponse> movieResponses = LocalData.getRemoteDummyMovies();
    private ArrayList<WatchlistResponse> tvShowResponses = LocalData.getRemoteDummyTvShows();
    private int movieId = movieResponses.get(0).getId();
    private int tvShowId = tvShowResponses.get(0).getId();

    @Test
    public void getMovies() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadItemsCallback) invocation.getArguments()[0])
                    .onItemsReceived(movieResponses);
            return null;
        }).when(remote).getMovies(any(RemoteRepository.LoadItemsCallback.class));

        ArrayList<WatchlistEntity> movieResult = movieCatalogueRepository.getMovies().getValue();

        verify(remote, times(1)).getMovies(any(RemoteRepository.LoadItemsCallback.class));

        assertNotNull(movieResult);
        assertEquals(movieResponses.size(), movieResult.size());
    }

    @Test
    public void getTvShows() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadItemsCallback) invocation.getArguments()[0])
                    .onItemsReceived(tvShowResponses);
            return null;
        }).when(remote).getTvShows(any(RemoteRepository.LoadItemsCallback.class));

        ArrayList<WatchlistEntity> tvShowResult = movieCatalogueRepository.getTvShows().getValue();

        verify(remote, times(1)).getTvShows(any(RemoteRepository.LoadItemsCallback.class));

        assertNotNull(tvShowResult);
        assertEquals(tvShowResponses.size(), tvShowResult.size());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void getItem() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadItemsCallback) invocation.getArguments()[0])
                    .onItemsReceived(movieResponses);
            return null;
        }).when(remote).getMovies(any(RemoteRepository.LoadItemsCallback.class));

        doAnswer(invocation -> {
            ((RemoteRepository.LoadItemsCallback) invocation.getArguments()[0])
                    .onItemsReceived(tvShowResponses);
            return null;
        }).when(remote).getTvShows(any(RemoteRepository.LoadItemsCallback.class));

        WatchlistEntity movieResult = movieCatalogueRepository.getMovies().getValue().get(0);
        WatchlistEntity tvShowResult = movieCatalogueRepository.getTvShows().getValue().get(0);

        verify(remote, times(1)).getMovies(any(RemoteRepository.LoadItemsCallback.class));
        verify(remote, times(1)).getTvShows(any(RemoteRepository.LoadItemsCallback.class));

        assertNotNull(movieResult);
        assertNotNull(tvShowResult);
        assertEquals(movieId, movieResult.getId());
        assertEquals(tvShowId, tvShowResult.getId());
    }

}