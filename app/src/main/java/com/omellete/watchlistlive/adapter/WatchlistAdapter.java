package com.omellete.watchlistlive.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.omellete.watchlistlive.data.entity.WatchlistEntity;
import com.omellete.watchlistlive.databinding.ItemMovieShowBinding;
import com.omellete.watchlistlive.ui.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class WatchlistAdapter extends RecyclerView.Adapter<WatchlistAdapter.WatchlistViewHolder> {

    private final Activity activity;
    private final List<WatchlistEntity> items = new ArrayList<>();

    public WatchlistAdapter(Activity activity) {
        this.activity = activity;
    }

    private List<WatchlistEntity> getItems() {
        return items;
    }

    public void setItems(List<WatchlistEntity> items) {
        if (items != null) {
            this.items.clear();
            this.items.addAll(items);
        }
    }

    @NonNull
    @Override
    public WatchlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieShowBinding binding = ItemMovieShowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new WatchlistViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull WatchlistViewHolder holder, int position) {

        Glide.with(holder.itemView.getContext())
                .load(getItems().get(position).getImgPosterPath())
                .into(holder.binding.imgPoster);
        holder.binding.tvItemTitle.setText(getItems().get(position).getName());
        holder.binding.tvItemDate.setText(String.valueOf(getItems().get(position).getYear()));
        holder.binding.userScore.setText(String.valueOf(getItems().get(position).getVote()));
        holder.binding.genre.setText(String.valueOf(getItems().get(position).getGenres()));
        holder.itemView.setOnClickListener(view -> {
            Intent moveToDetailItem = new Intent(activity, DetailActivity.class);
            moveToDetailItem.putExtra(DetailActivity.EXTRA_ITEM_ID, getItems().get(position).getId());
            moveToDetailItem.putExtra(DetailActivity.EXTRA_ITEM_TYPE, getItems().get(position).getItemType());
            activity.startActivity(moveToDetailItem);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class WatchlistViewHolder extends RecyclerView.ViewHolder {

        ItemMovieShowBinding binding;

        WatchlistViewHolder(ItemMovieShowBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }

}
