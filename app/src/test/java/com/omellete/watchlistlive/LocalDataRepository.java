package com.omellete.watchlistlive;

import static com.omellete.watchlistlive.data.WatchlistEntity.TYPE_MOVIE;
import static com.omellete.watchlistlive.data.WatchlistEntity.TYPE_SHOW;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.omellete.watchlistlive.api.WatchlistResponse;
import com.omellete.watchlistlive.data.WatchlistData;
import com.omellete.watchlistlive.data.WatchlistEntity;
import com.omellete.watchlistlive.repository.RemoteRepository;

import java.util.ArrayList;

public class LocalDataRepository implements WatchlistData {

    private volatile static LocalDataRepository INSTANCE = null;

    private final RemoteRepository remoteRepository;

    LocalDataRepository(@NonNull RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    public static LocalDataRepository getInstance(RemoteRepository remoteRepository) {
        if (INSTANCE == null) {
            synchronized (LocalDataRepository.class) {
                if (INSTANCE == null)
                    INSTANCE = new LocalDataRepository(remoteRepository);
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<ArrayList<WatchlistEntity>> getMovies() {
        MutableLiveData<ArrayList<WatchlistEntity>> movieResults = new MutableLiveData<>();

        remoteRepository.getMovies(new RemoteRepository.LoadItemsCallback() {
            @Override
            public void onItemsReceived(ArrayList<WatchlistResponse> itemResponses) {
                ArrayList<WatchlistEntity> movies = new ArrayList<>();

                for (WatchlistResponse watchlistResponse : itemResponses) {
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
                Log.e(this.getClass().getSimpleName(), "onDataNotAvailable: getDummyMovies: Request failed");
            }
        });

        return movieResults;
    }

    @Override
    public LiveData<ArrayList<WatchlistEntity>> getTvShows() {
        MutableLiveData<ArrayList<WatchlistEntity>> tvShowResults = new MutableLiveData<>();

        remoteRepository.getTvShows(new RemoteRepository.LoadItemsCallback() {
            @Override
            public void onItemsReceived(ArrayList<WatchlistResponse> watchlistResponses) {
                ArrayList<WatchlistEntity> tvShows = new ArrayList<>();

                for (WatchlistResponse watchlistResponse : watchlistResponses) {
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
                Log.e(this.getClass().getSimpleName(), "onDataNotAvailable: getDummyTvShows: Request failed");
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
                public void onItemsReceived(ArrayList<WatchlistResponse> watchlistResponses) {
                    for (WatchlistResponse watchlistResponse : watchlistResponses) {
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
                    Log.e(this.getClass().getSimpleName(), "onDataNotAvailable: getItem: getDummyMovies: Request failed");
                }
            });
        } else if (itemType.equals(TYPE_SHOW)) {
            remoteRepository.getTvShows(new RemoteRepository.LoadItemsCallback() {
                @Override
                public void onItemsReceived(ArrayList<WatchlistResponse> watchlistResponses) {
                    for (WatchlistResponse watchlistResponse : watchlistResponses) {
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
                    Log.e(this.getClass().getSimpleName(), "onDataNotAvailable: getItem: getDummyTvShows: Request failed");
                }
            });
        }

        return itemResult;
    }
}
