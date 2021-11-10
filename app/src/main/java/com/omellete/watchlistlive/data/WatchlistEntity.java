package com.omellete.watchlistlive.data;

public class WatchlistEntity {

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
}
