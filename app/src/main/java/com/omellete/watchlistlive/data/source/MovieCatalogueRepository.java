package com.omellete.watchlistlive.data.source;

import static com.omellete.watchlistlive.data.entity.WatchlistEntity.TYPE_MOVIE;
import static com.omellete.watchlistlive.data.entity.WatchlistEntity.TYPE_TV_SHOW;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.omellete.watchlistlive.data.entity.WatchlistEntity;
import com.omellete.watchlistlive.data.source.remote.RemoteRepository;
import com.omellete.watchlistlive.data.source.remote.response.WatchlistResponse;

import java.util.ArrayList;

public class MovieCatalogueRepository implements MovieCatalogueDataSource {

    private volatile static MovieCatalogueRepository INSTANCE = null;

    private final RemoteRepository remoteRepository;

    private MovieCatalogueRepository(@NonNull RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    public static MovieCatalogueRepository getInstance(RemoteRepository remoteRepository) {
        if (INSTANCE == null) {
            synchronized (MovieCatalogueRepository.class) {
                if (INSTANCE == null)
                    INSTANCE = new MovieCatalogueRepository(remoteRepository);
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<ArrayList<WatchlistEntity>> getMovies() {
        MutableLiveData<ArrayList<WatchlistEntity>> movieResults = new MutableLiveData<>();

        remoteRepository.getMovies(new RemoteRepository.LoadItemsCallback() {
            @Override
            public void onItemsReceived(ArrayList<WatchlistResponse> watchlistRespons) {
                ArrayList<WatchlistEntity> movies = new ArrayList<>();

                for (WatchlistResponse watchlistResponse : watchlistRespons) {
                    WatchlistEntity movie = new WatchlistEntity(
                            watchlistResponse.getId(),
                            watchlistResponse.getImgPosterPath(),
                            watchlistResponse.getBackDropPath(),
                            watchlistResponse.getTitleOri(),
                            watchlistResponse.getName(),
                            watchlistResponse.getItemType(),
                            watchlistResponse.getGenres(),
                            watchlistResponse.getDescription(),
                            watchlistResponse.getYear(),
                            watchlistResponse.getVote()
                    );
                    movies.add(movie);
                }

                movieResults.postValue(movies);
            }

            @Override
            public void onDataNotAvailable() {
                Log.e(this.getClass().getSimpleName(), "onDataNotAvailable: getMovies: Request failed");
            }
        });

        return movieResults;
    }

    @Override
    public LiveData<ArrayList<WatchlistEntity>> getTvShows() {
        MutableLiveData<ArrayList<WatchlistEntity>> tvShowResults = new MutableLiveData<>();

        remoteRepository.getTvShows(new RemoteRepository.LoadItemsCallback() {
            @Override
            public void onItemsReceived(ArrayList<WatchlistResponse> watchlistRespons) {
                ArrayList<WatchlistEntity> tvShows = new ArrayList<>();

                for (WatchlistResponse watchlistResponse : watchlistRespons) {
                    WatchlistEntity tvShow = new WatchlistEntity(
                            watchlistResponse.getId(),
                            watchlistResponse.getImgPosterPath(),
                            watchlistResponse.getBackDropPath(),
                            watchlistResponse.getTitleOri(),
                            watchlistResponse.getName(),
                            watchlistResponse.getItemType(),
                            watchlistResponse.getGenres(),
                            watchlistResponse.getDescription(),
                            watchlistResponse.getYear(),
                            watchlistResponse.getVote()
                    );
                    tvShows.add(tvShow);
                }

                tvShowResults.postValue(tvShows);
            }

            @Override
            public void onDataNotAvailable() {
                Log.e(this.getClass().getSimpleName(), "onDataNotAvailable: getTvShows: Request failed");
            }
        });

        return tvShowResults;
    }

    @Override
    public LiveData<WatchlistEntity> getItem(String itemType, int id) {
        MutableLiveData<WatchlistEntity> itemResult = new MutableLiveData<>();

        if (itemType.equals(TYPE_MOVIE)) {
            remoteRepository.getMovies(new RemoteRepository.LoadItemsCallback() {
                @Override
                public void onItemsReceived(ArrayList<WatchlistResponse> watchlistRespons) {
                    for (WatchlistResponse watchlistResponse : watchlistRespons) {
                        if (watchlistResponse.getId() == id) {
                            WatchlistEntity movie = new WatchlistEntity(
                                    watchlistResponse.getId(),
                                    watchlistResponse.getImgPosterPath(),
                                    watchlistResponse.getBackDropPath(),
                                    watchlistResponse.getTitleOri(),
                                    watchlistResponse.getName(),
                                    watchlistResponse.getItemType(),
                                    watchlistResponse.getGenres(),
                                    watchlistResponse.getDescription(),
                                    watchlistResponse.getYear(),
                                    watchlistResponse.getVote()
                            );
                            itemResult.postValue(movie);
                            break;
                        }
                    }
                }

                @Override
                public void onDataNotAvailable() {
                    Log.e(this.getClass().getSimpleName(), "onDataNotAvailable: getItem: getMovies: Request failed");
                }
            });
        } else if (itemType.equals(TYPE_TV_SHOW)) {
            remoteRepository.getTvShows(new RemoteRepository.LoadItemsCallback() {
                @Override
                public void onItemsReceived(ArrayList<WatchlistResponse> watchlistRespons) {
                    for (WatchlistResponse watchlistResponse : watchlistRespons) {
                        if (watchlistResponse.getId() == id) {
                            WatchlistEntity tvShow = new WatchlistEntity(
                                    watchlistResponse.getId(),
                                    watchlistResponse.getImgPosterPath(),
                                    watchlistResponse.getBackDropPath(),
                                    watchlistResponse.getTitleOri(),
                                    watchlistResponse.getName(),
                                    watchlistResponse.getItemType(),
                                    watchlistResponse.getGenres(),
                                    watchlistResponse.getDescription(),
                                    watchlistResponse.getYear(),
                                    watchlistResponse.getVote()
                            );
                            itemResult.postValue(tvShow);
                            break;
                        }
                    }
                }

                @Override
                public void onDataNotAvailable() {
                    Log.e(this.getClass().getSimpleName(), "onDataNotAvailable: getItem: getTvShows: Request failed");
                }
            });
        }

        return itemResult;
    }
}
