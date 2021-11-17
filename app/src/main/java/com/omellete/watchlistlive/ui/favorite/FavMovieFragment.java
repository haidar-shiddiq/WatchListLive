package com.omellete.watchlistlive.ui.favorite;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.omellete.watchlistlive.adapter.FavoriteAdapter;
import com.omellete.watchlistlive.databinding.FragmentFavMovieBinding;
import com.omellete.watchlistlive.adapter.FavoritePagedListAdapter;
import com.omellete.watchlistlive.db.MovieFavoriteModel;
import com.omellete.watchlistlive.db.RoomViewModel;
import com.omellete.watchlistlive.ui.detail.DetailActivity;

public class FavMovieFragment extends Fragment {

    public FragmentFavMovieBinding binding;
    ProgressDialog loading;
    private RoomViewModel roomViewModel;
    FavoriteAdapter favoriteAdapter;
    FavoritePagedListAdapter adapter;
    private boolean flag;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavMovieBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loading = new ProgressDialog(getContext());
        loading.setCancelable(false);
        loading.setMessage("Wait for a moment");

        favoriteAdapter = new FavoriteAdapter();
        adapter = new FavoritePagedListAdapter();

        binding.rvFavoriteMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvFavoriteMovie.setAdapter(adapter);
        binding.rvFavoriteMovie.setHasFixedSize(true);

        roomViewModel = new ViewModelProvider(this).get(RoomViewModel.class);

        roomViewModel.getAllFavMovie().observe(getViewLifecycleOwner(), new Observer<PagedList<MovieFavoriteModel>>() {
            @Override
            public void onChanged(PagedList<MovieFavoriteModel> models) {
                adapter.submitList(models);
            }
        });

        flag = true;
        binding.buttonSort.setOnClickListener(v -> {
            RoomViewModel roomViewModel = new ViewModelProvider(this).get(RoomViewModel.class);
            if (flag) {
                float deg = binding.buttonSort.getRotation() + 180F;
                binding.buttonSort.animate().rotation(deg).setInterpolator(new AccelerateDecelerateInterpolator());
                roomViewModel.getAllFavMovieDesc().observe(getViewLifecycleOwner(), new Observer<PagedList<MovieFavoriteModel>>() {
                    @Override
                    public void onChanged(PagedList<MovieFavoriteModel> models) {
                        adapter.submitList(models);
                    }
                });
                flag = false;
            } else {
                float deg = binding.buttonSort.getRotation() + 180F;
                binding.buttonSort.animate().rotation(deg).setInterpolator(new AccelerateDecelerateInterpolator());
                roomViewModel.getAllFavMovie().observe(getViewLifecycleOwner(), new Observer<PagedList<MovieFavoriteModel>>() {
                    @Override
                    public void onChanged(PagedList<MovieFavoriteModel> models) {
                        adapter.submitList(models);
                    }
                });
                flag = true;
            }

        });

        adapter.setOnItemClickListener(new FavoriteAdapter.OnItemClickListener() {
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
                roomViewModel.delete(adapter.getFavAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "Movie removed from Favorite", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.rvFavoriteMovie);

    }
}