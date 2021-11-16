package com.omellete.watchlistlive.ui.favorite;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.omellete.watchlistlive.R;
import com.omellete.watchlistlive.adapter.FavoriteAdapter;
import com.omellete.watchlistlive.databinding.FragmentFavMovieBinding;
import com.omellete.watchlistlive.databinding.FragmentFavTvShowBinding;
import com.omellete.watchlistlive.db.MovieFavoriteModel;
import com.omellete.watchlistlive.db.RoomViewModel;
import com.omellete.watchlistlive.ui.detail.DetailActivity;

import java.util.List;

public class FavTvShowFragment extends Fragment {

    public FragmentFavTvShowBinding binding;
    ProgressDialog loading;
    private RoomViewModel roomViewModel;
    FavoriteAdapter favoriteAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavTvShowBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loading = new ProgressDialog(getContext());
        loading.setCancelable(false);
        loading.setMessage("Wait for a moment");

        favoriteAdapter = new FavoriteAdapter();
        binding.rvFavoriteShow.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvFavoriteShow.setAdapter(favoriteAdapter);
        binding.rvFavoriteShow.setHasFixedSize(true);

        roomViewModel = new ViewModelProvider(this).get(RoomViewModel.class);

        roomViewModel.getAllFavShow().observe(getViewLifecycleOwner(), new Observer<List<MovieFavoriteModel>>() {
            @Override
            public void onChanged(List<MovieFavoriteModel> models) {
                favoriteAdapter.submitList(models);
            }
        });

        favoriteAdapter.setOnItemClickListener(new FavoriteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MovieFavoriteModel model) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_ID, model.getId());
                intent.putExtra(DetailActivity.EXTRA_TYPE, model.getItemType());
                getContext().startActivity(intent);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                roomViewModel.delete(favoriteAdapter.getFavAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "User removed from Favorite", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.rvFavoriteShow);

    }
}