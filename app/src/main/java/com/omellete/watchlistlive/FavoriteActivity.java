package com.omellete.watchlistlive;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.omellete.watchlistlive.adapter.SectionsPagerAdapterFav;
import com.omellete.watchlistlive.databinding.ActivityFavoriteBinding;
import com.omellete.watchlistlive.db.RoomViewModel;

public class FavoriteActivity extends AppCompatActivity {

    private RoomViewModel roomViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        ActivityFavoriteBinding activityFavoriteBinding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(activityFavoriteBinding.getRoot());
        setTitle("Favorite");
        roomViewModel = new ViewModelProvider(FavoriteActivity.this).get(RoomViewModel.class);

        SectionsPagerAdapterFav sectionsPagerAdapter = new SectionsPagerAdapterFav(this, getSupportFragmentManager());

        activityFavoriteBinding.viewPagerFav.setAdapter(sectionsPagerAdapter);
        activityFavoriteBinding.tabsFav.setupWithViewPager(activityFavoriteBinding.viewPagerFav);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fav_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delAll) {
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setIcon(R.drawable.warning)
                    .setTitle("Are you sure to remove all favorites?")
                    .setMessage("This will remove both Movie and Tv Show Favorite")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            roomViewModel.deleteAllFav();
                            Toast.makeText(getApplicationContext(), "Removed all favorite", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), "Removing cancelled", Toast.LENGTH_LONG).show();
                        }
                    })
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }

}