package com.omellete.watchlistlive.ui.movies;

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
import com.omellete.watchlistlive.databinding.FragmentMoviesBinding;
import com.omellete.watchlistlive.viewmodel.ViewModelFactory;

public class MoviesFragment extends Fragment {

    public FragmentMoviesBinding binding;
    ProgressDialog loading;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMoviesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loading = new ProgressDialog(getContext());
        loading.setCancelable(false);
        loading.setMessage("Wait for a moment");

        if (getActivity() != null) {
            MoviesViewModel viewModel = obtainViewModel(this);
            WatchlistAdapter moviesAdapter = new WatchlistAdapter(getActivity());
            loading.show();

            viewModel.getMovies().observe(getViewLifecycleOwner(), itemEntities -> {
                loading.dismiss();
                moviesAdapter.setItems(itemEntities);
                moviesAdapter.notifyDataSetChanged();
            });

            binding.rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvMovies.setHasFixedSize(true);
            binding.rvMovies.setAdapter(moviesAdapter);
        }
    }

    private MoviesViewModel obtainViewModel(Fragment fragment) {
        ViewModelFactory factory = ViewModelFactory.getInstance();
        return new ViewModelProvider(fragment, factory).get(MoviesViewModel.class);
    }
}