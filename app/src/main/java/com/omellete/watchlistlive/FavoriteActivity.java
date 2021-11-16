package com.omellete.watchlistlive;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.omellete.watchlistlive.adapter.SectionsPagerAdapterFav;
import com.omellete.watchlistlive.databinding.ActivityFavoriteBinding;

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