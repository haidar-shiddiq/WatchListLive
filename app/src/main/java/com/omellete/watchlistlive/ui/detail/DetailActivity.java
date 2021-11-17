package com.omellete.watchlistlive.ui.detail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.omellete.watchlistlive.R;
import com.omellete.watchlistlive.data.WatchlistEntity;
import com.omellete.watchlistlive.databinding.ActivityDetailBinding;
import com.omellete.watchlistlive.db.MovieFavoriteModel;
import com.omellete.watchlistlive.db.RoomViewModel;
import com.omellete.watchlistlive.viewmodel.ViewModelFactory;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_TYPE = "EXTRA_TYPE";
    public static final String EXTRA_DATA = "EXTRA_DATA";
    public static final String EXTRA_DATA_FAV = "EXTRA_DATA_FAV";
    private int itemId;
    private MovieFavoriteModel model, modelItemFav;
    private RoomViewModel roomViewModel;
    private String favTitle, favYear, favImg, itemType;
    private WatchlistEntity modelItem;

    private ActivityDetailBinding binding;
    public String itemName;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loading = new ProgressDialog(this);
        loading.setCancelable(false);
        loading.setMessage("Wait for a moment");
        loading.show();

        DetailViewModel viewModel = obtainViewModel(this);

        modelItem = getIntent().getParcelableExtra(EXTRA_DATA);
        modelItemFav = getIntent().getParcelableExtra(EXTRA_DATA_FAV);
        if (modelItem != null) {
            favTitle = modelItem.getName();
            favYear = modelItem.getYear();
            favImg = modelItem.getImgPosterPath();
        } else if (modelItemFav != null) {
            favTitle = modelItemFav.getUsername();
            favYear = modelItemFav.getYear();
            favImg = modelItemFav.getImgPosterFav();
        }


        Bundle extras = getIntent().getExtras();
        if (extras.getString(EXTRA_TYPE) != null) {
            itemId = extras.getInt(EXTRA_ID, 0);
            itemType = extras.getString(EXTRA_TYPE);

            if (itemId != 0 && itemType != null) {
                viewModel.setItemId(itemId);
                viewModel.setItemType(itemType);
            }
            viewModel.getItem().observe(this, this::populateWatchlist);
        } else if (extras.getString(EXTRA_TYPE) != null) {
            itemId = extras.getInt(EXTRA_ID, 0);
            itemType = extras.getString(EXTRA_TYPE);

            if (itemId != 0 && itemType != null) {
                viewModel.setItemId(itemId);
                viewModel.setItemType(itemType);
            }
        }

        viewModel.getItem().observe(this, this::populateWatchlist);

        if (itemType.equals("MOVIES")) {
            setTitle("Movie Detail");
        } else {
            setTitle("Tv Show Detail");
        }

        roomViewModel = new ViewModelProvider(DetailActivity.this).get(RoomViewModel.class);
        roomViewModel.setMovieId(itemId);
        model = new MovieFavoriteModel(itemId, favTitle, favYear, itemType, favImg);

        roomViewModel.getMovieByIdRoom().observe(this, results -> {
            if (results == null) {
                binding.favButton.setText("Add to Favorite");
                binding.favButton.setOnClickListener(view -> {
                    roomViewModel.insert(model);
                    if (itemType.equals("MOVIES")) {
                        Toast.makeText(getApplicationContext(), "Movie added to Favorite", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Tv Show added to Favorite", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                binding.favButton.setText("Remove from Favorite");
                binding.favButton.setOnClickListener(view -> {
                    roomViewModel.delFav(itemId);
                    Toast.makeText(getApplicationContext(), "Removed from Favorite", Toast.LENGTH_SHORT).show();
                });
            }
        });

    }

    private DetailViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance();
        return new ViewModelProvider(activity, factory).get(DetailViewModel.class);
    }

    private void populateWatchlist(WatchlistEntity item) {
        Glide.with(getApplicationContext())
                .load(item.getBackDropPath())
                .into(binding.imgBackdrop);
        Glide.with(getApplicationContext())
                .load(item.getImgPosterPath())
                .into(binding.imgPhoto);
        binding.tvName.setText(item.getName());
        binding.tvGenres.setText(item.getGenres());
        binding.tvYear.setText(String.valueOf(item.getYear()));
        binding.nameOri.setText(String.valueOf(item.getTitleOri()));
        binding.tvDescription.setText(item.getDescription());
        binding.userVote.setText(item.getVote());
        itemName = item.getName();
        itemId = item.getId();
        loading.dismiss();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Im viewing " + itemName + " on WatchList of the Week!";
            String shareSub = "Im using Watchlist Live!";
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share using"));
        }
        return super.onOptionsItemSelected(item);
    }
}