package com.omellete.watchlistlive;

import static com.omellete.watchlistlive.data.WatchlistEntity.TYPE_MOVIE;
import static com.omellete.watchlistlive.data.WatchlistEntity.TYPE_SHOW;

import com.omellete.watchlistlive.api.WatchlistResponse;
import com.omellete.watchlistlive.data.WatchlistEntity;
import com.omellete.watchlistlive.db.MovieFavoriteModel;

import java.util.ArrayList;

public class LocalDataFav {
    public static ArrayList<MovieFavoriteModel> getDummyFavMovies() {
        ArrayList<MovieFavoriteModel> movies = new ArrayList<>();

        movies.add(new MovieFavoriteModel(
                370172,
                "No Time to Die",
                "2021",
                "MOVIE",
                "/iUgygt3fscRoKWCV1d0C7FbM9TP.jpg"
        ));

        return movies;
    }

    public static ArrayList<MovieFavoriteModel> getDummyFavTvShows() {
        ArrayList<MovieFavoriteModel> tvShows = new ArrayList<>();

        tvShows.add(new MovieFavoriteModel(
                93405,
                "Squid Game",
                "2021",
                "TV_SHOW",
                "/dDlEmu3EZ0Pgg93K2SVNLCjCSvE.jpg"
        ));

        return tvShows;
    }
}
