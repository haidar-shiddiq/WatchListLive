package com.omellete.watchlistlive;

import static com.omellete.watchlistlive.data.WatchlistEntity.TYPE_MOVIE;
import static com.omellete.watchlistlive.data.WatchlistEntity.TYPE_SHOW;
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
import com.omellete.watchlistlive.ui.detail.DetailViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

public class DetailViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private DetailViewModel viewModel;
    private final WatchlistEntity localMovie = LocalData.getDummyMovies().get(0);
    private final WatchlistEntity localTvShow = LocalData.getDummyTvShows().get(0);
    private final WatchlistRepository watchlistRepository = mock(WatchlistRepository.class);

    @Mock
    private Observer<WatchlistEntity> observer = mock(Observer.class);

    @Before
    public void setUp() {
        viewModel = new DetailViewModel(watchlistRepository);
    }

    @Test
    public void getMovie() {
        MutableLiveData<WatchlistEntity> movie = new MutableLiveData<>();
        movie.setValue(localMovie);
        int movieId = localMovie.getId();
        when(watchlistRepository.getItem(TYPE_MOVIE, movieId)).thenReturn(movie);
        viewModel.setItemType(TYPE_MOVIE);
        viewModel.setItemId(movieId);

        WatchlistEntity movieResult = viewModel.getItem().getValue();
        assertNotNull(movieResult);
        assertEquals(localMovie.getId(), movieResult.getId());
        assertEquals(localMovie.getImgPosterPath(), movieResult.getImgPosterPath());
        assertEquals(localMovie.getBackDropPath(), movieResult.getBackDropPath());
        assertEquals(localMovie.getTitleOri(), movieResult.getTitleOri());
        assertEquals(localMovie.getName(), movieResult.getName());
        assertEquals(localMovie.getItemType(), movieResult.getItemType());
        assertEquals(localMovie.getGenres(), movieResult.getGenres());
        assertEquals(localMovie.getDescription(), movieResult.getDescription());
        assertEquals(localMovie.getYear(), movieResult.getYear());
        assertEquals(localMovie.getVote(), movieResult.getVote());

        viewModel.getItem().observeForever(observer);
        verify(observer).onChanged(localMovie);
    }

    @Test
    public void getTvShow() {
        MutableLiveData<WatchlistEntity> tvShow = new MutableLiveData<>();
        tvShow.setValue(localTvShow);
        int tvShowId = localTvShow.getId();
        when(watchlistRepository.getItem(TYPE_SHOW, tvShowId)).thenReturn(tvShow);
        viewModel.setItemType(TYPE_SHOW);
        viewModel.setItemId(tvShowId);
        observer = mock(Observer.class);

        WatchlistEntity tvShowResult = viewModel.getItem().getValue();
        assertNotNull(tvShowResult);
        assertEquals(localTvShow.getId(), tvShowResult.getId());
        assertEquals(localTvShow.getImgPosterPath(), tvShowResult.getImgPosterPath());
        assertEquals(localTvShow.getBackDropPath(), tvShowResult.getBackDropPath());
        assertEquals(localTvShow.getTitleOri(), tvShowResult.getTitleOri());
        assertEquals(localTvShow.getName(), tvShowResult.getName());
        assertEquals(localTvShow.getItemType(), tvShowResult.getItemType());
        assertEquals(localTvShow.getGenres(), tvShowResult.getGenres());
        assertEquals(localTvShow.getDescription(), tvShowResult.getDescription());
        assertEquals(localTvShow.getYear(), tvShowResult.getYear());
        assertEquals(localTvShow.getVote(), tvShowResult.getVote());

        viewModel.getItem().observeForever(observer);
        verify(observer).onChanged(localTvShow);
    }
}