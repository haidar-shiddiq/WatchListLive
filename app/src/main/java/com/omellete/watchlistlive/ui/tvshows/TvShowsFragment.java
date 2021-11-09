package com.omellete.watchlistlive.ui.tvshows;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.omellete.watchlistlive.adapter.WatchlistAdapter;
import com.omellete.watchlistlive.databinding.FragmentTvShowsBinding;
import com.omellete.watchlistlive.viewmodel.ViewModelFactory;

public class TvShowsFragment extends Fragment {

    public FragmentTvShowsBinding binding;
    ProgressDialog loading;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTvShowsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loading = new ProgressDialog(getContext());
        loading.setCancelable(false);
        loading.setMessage("Wait for a moment");

        if (getActivity() != null) {
            TvShowsViewModel viewModel = obtainViewModel(this);
            WatchlistAdapter tvShowsAdapter = new WatchlistAdapter(getActivity());
            loading.show();

            viewModel.getTvShows().observe(getViewLifecycleOwner(), itemEntities -> {
				loading.dismiss();
                tvShowsAdapter.setItems(itemEntities);
                tvShowsAdapter.notifyDataSetChanged();
            });

            binding.rvTvShows.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvTvShows.setHasFixedSize(true);
            binding.rvTvShows.setAdapter(tvShowsAdapter);
        }
    }

    private TvShowsViewModel obtainViewModel(Fragment fragment) {
        ViewModelFactory factory = ViewModelFactory.getInstance();
        return new ViewModelProvider(fragment, factory).get(TvShowsViewModel.class);
    }
}