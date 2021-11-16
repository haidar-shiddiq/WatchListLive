package com.omellete.watchlistlive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.omellete.watchlistlive.adapter.FavoriteAdapter;
import com.omellete.watchlistlive.adapter.SectionsPagerAdapter;
import com.omellete.watchlistlive.adapter.SectionsPagerAdapterFav;
import com.omellete.watchlistlive.databinding.ActivityFavoriteBinding;
import com.omellete.watchlistlive.db.MovieFavoriteModel;
import com.omellete.watchlistlive.ui.detail.DetailActivity;

public class FavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        ActivityFavoriteBinding activityFavoriteBinding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(activityFavoriteBinding.getRoot());
        setTitle("Favorite");

        SectionsPagerAdapterFav sectionsPagerAdapter = new SectionsPagerAdapterFav(this, getSupportFragmentManager());

        activityFavoriteBinding.viewPagerFav.setAdapter(sectionsPagerAdapter);
        activityFavoriteBinding.tabsFav.setupWithViewPager(activityFavoriteBinding.viewPagerFav);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }



    }

}