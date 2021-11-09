package com.omellete.watchlistlive.ui.detail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.omellete.watchlistlive.R;
import com.omellete.watchlistlive.data.WatchlistEntity;
import com.omellete.watchlistlive.databinding.ActivityDetailBinding;
import com.omellete.watchlistlive.viewmodel.ViewModelFactory;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_ITEM_ID = "extra_item_id";
    public static final String EXTRA_ITEM_TYPE = "extra_item_type";

    private ActivityDetailBinding binding;
    public String itemName;
    ProgressDialog loading;

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
        if (extras != null) {
            int itemId = extras.getInt(EXTRA_ITEM_ID, 0);
            String itemType = extras.getString(EXTRA_ITEM_TYPE);

            if (itemId != 0 && itemType != null) {
                viewModel.setItemId(itemId);
                viewModel.setItemType(itemType);
            }
        }

        viewModel.getItem().observe(this, this::populateDetail);
    }

    private DetailViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance();
        return new ViewModelProvider(activity, factory).get(DetailViewModel.class);
    }

    private void populateDetail(WatchlistEntity item) {
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
        itemName = item.getTitleOri();
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
            String shareBody = "Hey! Im viewing " + itemName + " on WatchList of the Week";
            String shareSub = "Hey! Im using TMDB Movie and Tv Show";
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share using"));
        }
        return super.onOptionsItemSelected(item);
    }
}