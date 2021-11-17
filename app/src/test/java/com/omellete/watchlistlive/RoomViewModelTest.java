package com.omellete.watchlistlive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import androidx.room.Room;

import com.omellete.watchlistlive.LocalData;
import com.omellete.watchlistlive.db.FavoriteRepository;
import com.omellete.watchlistlive.db.MovieFavoriteModel;
import com.omellete.watchlistlive.db.RoomViewModel;
import com.omellete.watchlistlive.viewmodel.FavoriteViewModel;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

public class RoomViewModelTest{

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    FavoriteViewModel model;
    private final FavoriteRepository catalogueRepository = mock(FavoriteRepository.class);

    @Before
    public void setUp() { model = new FavoriteViewModel(catalogueRepository);
    }

    @Test
    public void getAllFavMovie() {
        ArrayList<MovieFavoriteModel> movieResults = LocalDataFav.getDummyFavMovies();
        DataSource.Factory<Integer, MovieFavoriteModel> dataSourceFactory = mock(DataSource.Factory.class);

        when(catalogueRepository.getAllFavMovie()).thenReturn(dataSourceFactory);
        PagedList<MovieFavoriteModel> result = PagedListTest.mockPagedList(movieResults);

        model.getAllFavMovie();

        verify(catalogueRepository).getAllFavMovie();
        assertNotNull(result);
        assertEquals(movieResults.size(), result.size());
    }

    @Test
    public void getAllFavTv() {
        ArrayList<MovieFavoriteModel> tvResults = LocalDataFav.getDummyFavTvShows();
        DataSource.Factory<Integer, MovieFavoriteModel> dataSourceFactory = mock(DataSource.Factory.class);

        when(catalogueRepository.getAllFavShow()).thenReturn(dataSourceFactory);
        PagedList<MovieFavoriteModel> result = PagedListTest.mockPagedList(tvResults);

        model.getAllFavShow();

        verify(catalogueRepository).getAllFavShow();
        assertNotNull(result);
        assertEquals(tvResults.size(), result.size());
    }
}