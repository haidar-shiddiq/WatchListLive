package com.omellete.watchlistlive.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.omellete.watchlistlive.R;
import com.omellete.watchlistlive.adapter.SectionsPagerAdapter;
import com.omellete.watchlistlive.databinding.ActivityMainBinding;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        setTitle("Weekly Popular");

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        activityMainBinding.viewPager.setAdapter(sectionsPagerAdapter);
        activityMainBinding.tabs.setupWithViewPager(activityMainBinding.viewPager);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
    }
}