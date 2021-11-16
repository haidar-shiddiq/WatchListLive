package com.omellete.watchlistlive.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.omellete.watchlistlive.R;
import com.omellete.watchlistlive.ui.favorite.FavMovieFragment;
import com.omellete.watchlistlive.ui.favorite.FavTvShowFragment;
import com.omellete.watchlistlive.ui.movies.MoviesFragment;
import com.omellete.watchlistlive.ui.tvshows.TvShowsFragment;

public class SectionsPagerAdapterFav extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.movies, R.string.tv_show};
    private final Context mContext;

    public SectionsPagerAdapterFav(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FavMovieFragment();
            case 1:
                return new FavTvShowFragment();
            default:
                return new Fragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
