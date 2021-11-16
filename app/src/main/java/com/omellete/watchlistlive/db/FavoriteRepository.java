package com.omellete.watchlistlive.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FavoriteRepository {

    private Dao dao;
    private LiveData<List<MovieFavoriteModel>> allFav;
    private LiveData<List<MovieFavoriteModel>> allFavMovie;
    private LiveData<List<MovieFavoriteModel>> allFavShow;

    public FavoriteRepository(Application application) {
        FavDatabase database = FavDatabase.getInstance(application);
        dao = database.Dao();
        allFav = dao.getAllFav();
        allFavMovie = dao.getAllFavMovie();
        allFavShow = dao.getAllFavShow();
    }

    public void insert(MovieFavoriteModel model) {
        new InsertFavAsyncTask(dao).execute(model);
    }

    public void update(MovieFavoriteModel model) {
        new UpdateFavAsyncTask(dao).execute(model);
    }

    public void delete(MovieFavoriteModel model) {
        new DeleteFavAsyncTask(dao).execute(model);
    }

    public void deleteAllFav() {
        new DeleteAllFavAsyncTask(dao).execute();
    }

    public LiveData<List<MovieFavoriteModel>> getAllFav() {
        return allFav;
    }
    public LiveData<List<MovieFavoriteModel>> getAllFavMovie() {
        return allFavMovie;
    }
    public LiveData<List<MovieFavoriteModel>> getAllFavShow() {
        return allFavShow;
    }

    private static class InsertFavAsyncTask extends AsyncTask<MovieFavoriteModel, Void, Void> {
        private Dao dao;

        private InsertFavAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(MovieFavoriteModel... model) {
            dao.insert(model[0]);
            return null;
        }
    }

    private static class UpdateFavAsyncTask extends AsyncTask<MovieFavoriteModel, Void, Void> {
        private Dao dao;

        private UpdateFavAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(MovieFavoriteModel... models) {
            dao.update(models[0]);
            return null;
        }
    }

    private static class DeleteFavAsyncTask extends AsyncTask<MovieFavoriteModel, Void, Void> {
        private Dao dao;

        private DeleteFavAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(MovieFavoriteModel... models) {
            dao.delete(models[0]);
            return null;
        }
    }

    private static class DeleteAllFavAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;

        private DeleteAllFavAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllFav();
            return null;
        }
    }
}