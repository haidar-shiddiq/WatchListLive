package com.omellete.watchlistlive.db;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.omellete.watchlistlive.adapter.FavoriteAdapter;
import com.omellete.watchlistlive.databinding.ItemFavBinding;

public class FavoritePagedListAdapter extends PagedListAdapter<MovieFavoriteModel, FavoritePagedListAdapter.NoteViewHolder> {
//    private final Activity activity;

    private FavoriteAdapter.OnItemClickListener listener;
    public FavoritePagedListAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public FavoritePagedListAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFavBinding binding = ItemFavBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FavoritePagedListAdapter.NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final NoteViewHolder holder, int position) {
//        holder.bind(getItem(position));
        MovieFavoriteModel model = getFavAt(position);
        holder.binding.tvItemTitleFav.setText(model.getUsername());
        holder.binding.tvItemDateFav.setText(model.getHtmlurl());
        Glide.with(holder.itemView.getContext())
                .load(model.getImgPosterFav())
                .into(holder.binding.imgFav);
    }

    public MovieFavoriteModel getFavAt(int position) {
        return getItem(position);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        final ItemFavBinding binding;

        NoteViewHolder(ItemFavBinding binding) {
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



//        public void bind(MovieFavoriteModel note) {
//            holder.binding.tvItemTitleFav.setText(model.getUsername());
//            holder.binding.tvItemDateFav.setText(model.getHtmlurl());
//            Glide.with(holder.itemView.getContext())
//                    .load(model.getImgPosterFav())
//                    .into(holder.binding.imgFav);
//        }
    }

    public void setOnItemClickListener(FavoriteAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    private static DiffUtil.ItemCallback<MovieFavoriteModel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieFavoriteModel>() {
                @Override
                public boolean areItemsTheSame(MovieFavoriteModel oldNote, MovieFavoriteModel newNote) {
                    return oldNote.getUsername().equals(newNote.getUsername()) && oldNote.getHtmlurl().equals(newNote.getHtmlurl());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(MovieFavoriteModel oldNote, @NonNull MovieFavoriteModel newNote) {
                    return oldNote.equals(newNote);
                }
            };
}