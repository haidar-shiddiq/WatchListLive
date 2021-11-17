package com.omellete.watchlistlive.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.omellete.watchlistlive.databinding.ItemFavBinding;
import com.omellete.watchlistlive.db.MovieFavoriteModel;

public class FavoritePagedListAdapter extends PagedListAdapter<MovieFavoriteModel, FavoritePagedListAdapter.NoteViewHolder> {

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
        MovieFavoriteModel model = getFavAt(position);
        holder.binding.tvItemTitleFav.setText(model.getUsername());
        holder.binding.tvItemDateFav.setText(model.getYear());
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

    }

    public void setOnItemClickListener(FavoriteAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    private static DiffUtil.ItemCallback<MovieFavoriteModel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieFavoriteModel>() {
                @Override
                public boolean areItemsTheSame(MovieFavoriteModel oldNote, MovieFavoriteModel newNote) {
                    return oldNote.getUsername().equals(newNote.getUsername()) && oldNote.getYear().equals(newNote.getYear());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(MovieFavoriteModel oldNote, @NonNull MovieFavoriteModel newNote) {
                    return oldNote.equals(newNote);
                }
            };
}