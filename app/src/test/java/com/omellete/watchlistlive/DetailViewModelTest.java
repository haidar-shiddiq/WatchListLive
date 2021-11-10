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

public class DetailViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailViewModel viewModel;
    private final WatchlistEntity dummyMovie = LocalData.getDummyMovies().get(0);
    private final WatchlistEntity dummyTvShow = LocalData.getDummyTvShows().get(0);
    private final WatchlistRepository watchlistRepository = mock(WatchlistRepository.class);

    @Before
    public void setUp() {
        viewModel = new DetailViewModel(watchlistRepository);
    }

    @Test
    public void getMovie() {
        MutableLiveData<WatchlistEntity> movie = new MutableLiveData<>();
        movie.setValue(dummyMovie);
        int movieId = dummyMovie.getId();
        when(watchlistRepository.getItem(TYPE_MOVIE, movieId)).thenReturn(movie);

        viewModel.setItemType(TYPE_MOVIE);
        viewModel.setItemId(movieId);

        Observer<WatchlistEntity> observer = mock(Observer.class);
        viewModel.getItem().observeForever(observer);
        verify(observer).onChanged(dummyMovie);

        WatchlistEntity movieResult = viewModel.getItem().getValue();
        assertNotNull(movieResult);
        assertEquals(dummyMovie.getId(), movieResult.getId());
        assertEquals(dummyMovie.getImgPosterPath(), movieResult.getImgPosterPath());
        assertEquals(dummyMovie.getBackDropPath(), movieResult.getBackDropPath());
        assertEquals(dummyMovie.getTitleOri(), movieResult.getTitleOri());
        assertEquals(dummyMovie.getName(), movieResult.getName());
        assertEquals(dummyMovie.getItemType(), movieResult.getItemType());
        assertEquals(dummyMovie.getGenres(), movieResult.getGenres());
        assertEquals(dummyMovie.getDescription(), movieResult.getDescription());
        assertEquals(dummyMovie.getYear(), movieResult.getYear());
        assertEquals(dummyMovie.getVote(), movieResult.getVote());
    }

    @Test
    public void getTvShow() {
        MutableLiveData<WatchlistEntity> tvShow = new MutableLiveData<>();
        tvShow.setValue(dummyTvShow);
        int tvShowId = dummyTvShow.getId();
        when(watchlistRepository.getItem(TYPE_SHOW, tvShowId)).thenReturn(tvShow);

        viewModel.setItemType(TYPE_SHOW);
        viewModel.setItemId(tvShowId);

        Observer<WatchlistEntity> observer = mock(Observer.class);
        viewModel.getItem().observeForever(observer);
        verify(observer).onChanged(dummyTvShow);

        WatchlistEntity tvShowResult = viewModel.getItem().getValue();
        assertNotNull(tvShowResult);
        assertEquals(dummyTvShow.getId(), tvShowResult.getId());
        assertEquals(dummyTvShow.getImgPosterPath(), tvShowResult.getImgPosterPath());
        assertEquals(dummyTvShow.getBackDropPath(), tvShowResult.getBackDropPath());
        assertEquals(dummyTvShow.getTitleOri(), tvShowResult.getTitleOri());
        assertEquals(dummyTvShow.getName(), tvShowResult.getName());
        assertEquals(dummyTvShow.getItemType(), tvShowResult.getItemType());
        assertEquals(dummyTvShow.getGenres(), tvShowResult.getGenres());
        assertEquals(dummyTvShow.getDescription(), tvShowResult.getDescription());
        assertEquals(dummyTvShow.getYear(), tvShowResult.getYear());
        assertEquals(dummyTvShow.getVote(), tvShowResult.getVote());

    }
}