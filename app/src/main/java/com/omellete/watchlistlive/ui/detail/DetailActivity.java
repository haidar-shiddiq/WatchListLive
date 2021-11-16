package com.omellete.watchlistlive.ui.detail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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
    public static final String EXTRA_ID_FAV = "EXTRA_ID_FAV";
    public static final String EXTRA_TYPE_FAV = "EXTRA_TYPE_FAV";
    public boolean flag;
    private String itemType, imgFav;
    private int itemId;

    private ActivityDetailBinding binding;
    public String itemName;
    ProgressDialog loading;
    String favUnamee, favNamee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Detail");
        loading = new ProgressDialog(this);
        loading.setCancelable(false);
        loading.setMessage("Wait for a moment");
        loading.show();

        DetailViewModel viewModel = obtainViewModel(this);

        Bundle extras = getIntent().getExtras();
        if (extras.getString(EXTRA_TYPE) != null) {
            itemId = extras.getInt(EXTRA_ID, 0);
            itemType = extras.getString(EXTRA_TYPE);

            if (itemId != 0 && itemType != null) {
                viewModel.setItemId(itemId);
                viewModel.setItemType(itemType);
            }
            viewModel.getItem().observe(this, this::populateWatchlist);
        }else if (extras.getString(EXTRA_TYPE) != null) {
            itemId = extras.getInt(EXTRA_ID, 0);
            itemType = extras.getString(EXTRA_TYPE);

            if (itemId != 0 && itemType != null) {
                viewModel.setItemId(itemId);
                viewModel.setItemType(itemType);
            }
        }

        viewModel.getItem().observe(this, this::populateWatchlist);

        flag = true;
        binding.fabFavorite.setOnClickListener(v -> {
            RoomViewModel roomViewModel = new ViewModelProvider(DetailActivity.this).get(RoomViewModel.class);
            String favUname = favUnamee;
            String favName = favNamee;
            MovieFavoriteModel model = new MovieFavoriteModel(itemId, favUname, favName, itemType,imgFav);
            if (flag) {
                binding.fabFavorite.setText("Added to Favorite");
                flag = false;
                roomViewModel.insert(model);
                Toast.makeText(getApplicationContext(), "Success added to Favorite", Toast.LENGTH_SHORT).show();
            } else {
                binding.fabFavorite.setText("Add to Favorite");
                flag = true;
                roomViewModel.deleteAllFav();
                Toast.makeText(getApplicationContext(), "All user removed from Favorite", Toast.LENGTH_SHORT).show();
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
        favUnamee = item.getTitleOri();
        favNamee = item.getYear();
        binding.tvGenres.setText(item.getGenres());
        binding.tvYear.setText(String.valueOf(item.getYear()));
        binding.nameOri.setText(String.valueOf(item.getTitleOri()));
        binding.tvDescription.setText(item.getDescription());
        binding.userVote.setText(item.getVote());
        itemName = item.getTitleOri();
        imgFav = item.getImgPosterPath();
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