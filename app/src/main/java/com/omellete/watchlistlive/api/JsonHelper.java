package com.omellete.watchlistlive.api;

import static com.omellete.watchlistlive.data.WatchlistEntity.TYPE_MOVIE;
import static com.omellete.watchlistlive.data.WatchlistEntity.TYPE_SHOW;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.omellete.watchlistlive.data.GenresId;
import com.omellete.watchlistlive.repository.RemoteRepository;
import com.omellete.watchlistlive.EspressoIdlingResource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class JsonHelper {

    String API_KEY = "YOUR API KEY HERE";

    public void loadMovies(RemoteRepository.LoadItemsCallback callback) {
        String url = "https://api.themoviedb.org/3/trending/movie/week?api_key=" + API_KEY;
        new AsyncHttpClient().get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                ArrayList<WatchlistResponse> watchlistRespons = parseJsonToArrayList(new String(responseBody), TYPE_MOVIE);
                callback.onItemsReceived(watchlistRespons);
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                callback.onDataNotAvailable();
                Log.e(this.getClass().getSimpleName(), "onFailure: request movies failed", error);
                EspressoIdlingResource.decrement();
            }
        });
    }

    public void loadTvShows(RemoteRepository.LoadItemsCallback callback) {
        String url = "https://api.themoviedb.org/3/trending/tv/week?api_key=" + API_KEY;
        new AsyncHttpClient().get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                ArrayList<WatchlistResponse> watchlistRespons = parseJsonToArrayList(new String(responseBody), TYPE_SHOW);
                callback.onItemsReceived(watchlistRespons);
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                callback.onDataNotAvailable();
                Log.e(this.getClass().getSimpleName(), "onFailure: request movies failed", error);
                EspressoIdlingResource.decrement();
            }
        });
    }

    private ArrayList<WatchlistResponse> parseJsonToArrayList(String responseJson, String itemsType) {
        ArrayList<WatchlistResponse> watchlistRespons = new ArrayList<>();

        try {
            JSONObject responseObject = new JSONObject(responseJson);
            JSONArray responseArray = responseObject.getJSONArray("results");

            for (int i = 0; i < responseArray.length(); ++i) {
                JSONObject response = responseArray.getJSONObject(i);

                int id = response.getInt("id");
                String name = itemsType.equals(TYPE_MOVIE) ? response.getString("title") : response.getString("name");
                String year = itemsType.equals(TYPE_MOVIE) ? response.getString("release_date") : response.getString("first_air_date");
                year = year.length() > 4 ? year.substring(0, 4) : year;
                year= year + " ";
                String imgPosterPath = "https://image.tmdb.org/t/p/w342" + response.getString("poster_path");
                String backDropPath = "https://image.tmdb.org/t/p/w342" + response.getString("backdrop_path");
                JSONArray idGenres = response.getJSONArray("genre_ids");
                HashMap<Integer, String> dataGenres = GenresId.getGenres();
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < idGenres.length(); ++j) {
                    int idGenre = idGenres.getInt(j);
                    String valueGenre = dataGenres.get(idGenre);
                    stringBuilder.append(valueGenre);
                    if (j != (idGenres.length() - 1))
                        stringBuilder.append(", ");
                }
                String genres = stringBuilder.toString();
                String description = response.getString("overview");
                String titleOri = itemsType.equals(TYPE_MOVIE) ? response.getString("original_title") : response.getString("original_name");
                String vote = response.getString("vote_average")+"%";
                vote = vote.replace(".","");
                if(vote.equals("00%")){
                    vote = "0%";
                }

                WatchlistResponse watchlistResponse = new WatchlistResponse(id, imgPosterPath, backDropPath, titleOri, name, itemsType, genres, description, year, vote);
                watchlistRespons.add(watchlistResponse);
            }
        } catch (JSONException e) {
            Log.e(this.getClass().getSimpleName(), "parseJsonToArrayList: " + e.getMessage());
        }

        return watchlistRespons;
    }

}
