package com.omellete.watchlistlive.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

public class FavoriteRepository {

    private Dao dao;

    public FavoriteRepository(Application application) {
        FavDatabase database = FavDatabase.getInstance(application);
        dao = database.Dao();
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

    public void delFav(int id) {
        new DelFavAsyncTask(dao).execute(id);
    }


    public void deleteAllFav() {
        new DeleteAllFavAsyncTask(dao).execute();
    }

    public LiveData<MovieFavoriteModel> getMovieByIdRoom(int id) {
        return dao.getMovieById(id);
    }

    public DataSource.Factory<Integer, MovieFavoriteModel> getAllFav() {
        return dao.getAllFav();
    }

    public DataSource.Factory<Integer, MovieFavoriteModel> getAllFavMovie() {
        return dao.getAllFavMovie();
    }

    public DataSource.Factory<Integer, MovieFavoriteModel> getAllFavMovieDesc() {
        return dao.getAllFavMovieDesc();
    }

    public DataSource.Factory<Integer, MovieFavoriteModel> getAllFavShow() {
        return dao.getAllFavShow();
    }

    public DataSource.Factory<Integer, MovieFavoriteModel> getAllFavShowDesc() {
        return dao.getAllFavShowDesc();
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

    private static class DelFavAsyncTask extends AsyncTask<Integer, Void, Void> {
        private Dao dao;

        private DelFavAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Integer... id) {
            dao.delFav(id[0]);
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