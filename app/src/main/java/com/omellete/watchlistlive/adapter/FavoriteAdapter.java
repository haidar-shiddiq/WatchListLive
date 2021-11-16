package com.omellete.watchlistlive.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.omellete.watchlistlive.data.WatchlistEntity;
import com.omellete.watchlistlive.databinding.ItemFavBinding;
import com.omellete.watchlistlive.db.MovieFavoriteModel;
import com.omellete.watchlistlive.ui.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends ListAdapter<MovieFavoriteModel, FavoriteAdapter.ViewHolder> {

    private OnItemClickListener listener;
    private final List<MovieFavoriteModel> items = new ArrayList<>();

    public FavoriteAdapter() {
        super(DIFF_CALLBACK);
    }

    private List<MovieFavoriteModel> getItems() {
        return items;
    }

    public void setItems(List<MovieFavoriteModel> items) {
        if (items != null) {
            this.items.clear();
            this.items.addAll(items);
        }
    }

    private static final DiffUtil.ItemCallback<MovieFavoriteModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<MovieFavoriteModel>() {
        @Override
        public boolean areItemsTheSame(MovieFavoriteModel oldItem, MovieFavoriteModel newItem) {
            return oldItem.getUid() == newItem.getUid();
        }

        @Override
        public boolean areContentsTheSame(MovieFavoriteModel oldItem, MovieFavoriteModel newItem) {
            return oldItem.getUsername().equals(newItem.getUsername()) &&
                    oldItem.getHtmlurl().equals(newItem.getHtmlurl());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFavBinding binding = ItemFavBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FavoriteAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieFavoriteModel model = getFavAt(position);
//        holder.binding.tvItemTitle.setText(getItems().get(position).getName());
//        holder.binding.tvItemDate.setText(String.valueOf(getItems().get(position).getYear()));
        holder.binding.tvItemTitleFav.setText(model.getUsername());
        holder.binding.tvItemDateFav.setText(model.getHtmlurl());
        Glide.with(holder.itemView.getContext())
                .load(model.getImgPosterFav())
                .into(holder.binding.imgFav);
//        holder.binding.itemFav.setOnClickListener(view -> {
//            Intent intent = new Intent(view.getContext(), DetailActivity.class);
//            intent.putExtra(DetailActivity.EXTRA_ID_FAV, getItems().get(position).getId());
//            intent.putExtra(DetailActivity.EXTRA_TYPE_FAV, getItems().get(position).getItemType());
//            view.getContext().startActivity(intent);
//        });

    }

    public MovieFavoriteModel getFavAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemFavBinding binding;


        ViewHolder(ItemFavBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(MovieFavoriteModel model);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}