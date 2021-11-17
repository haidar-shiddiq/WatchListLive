package com.omellete.watchlistlive.data;

import android.os.Parcel;
import android.os.Parcelable;

public class WatchlistEntity implements Parcelable {

    private int id;
    private String name;
    private final String imgPosterPath, backDropPath, titleOri, itemType, genres, description, year, vote;

    public static final String TYPE_MOVIE = "MOVIES";
    public static final String TYPE_SHOW = "SHOWS";

    public WatchlistEntity(int id, String imgPosterPath, String backDropPath, String titleOri, String name, String itemType, String genres, String description, String year, String vote) {
        this.id = id;
        this.imgPosterPath = imgPosterPath;
        this.backDropPath = backDropPath;
        this.titleOri = titleOri;
        this.name = name;
        this.itemType = itemType;
        this.genres = genres;
        this.description = description;
        this.year = year;
        this.vote = vote;
    }

    protected WatchlistEntity(Parcel in) {
        id = in.readInt();
        name = in.readString();
        imgPosterPath = in.readString();
        backDropPath = in.readString();
        titleOri = in.readString();
        itemType = in.readString();
        genres = in.readString();
        description = in.readString();
        year = in.readString();
        vote = in.readString();
    }

    public static final Creator<WatchlistEntity> CREATOR = new Creator<WatchlistEntity>() {
        @Override
        public WatchlistEntity createFromParcel(Parcel in) {
            return new WatchlistEntity(in);
        }

        @Override
        public WatchlistEntity[] newArray(int size) {
            return new WatchlistEntity[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgPosterPath() {
        return imgPosterPath;
    }

    public String getBackDropPath() {
        return backDropPath;
    }

    public String getTitleOri() {
        return titleOri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemType() {
        return itemType;
    }

    public String getGenres() {
        return genres;
    }

    public String getDescription() {
        return description;
    }

    public String getYear() {
        return year;
    }

    public String getVote() {
        return vote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(imgPosterPath);
        parcel.writeString(backDropPath);
        parcel.writeString(titleOri);
        parcel.writeString(itemType);
        parcel.writeString(genres);
        parcel.writeString(description);
        parcel.writeString(year);
        parcel.writeString(vote);
    }
}
