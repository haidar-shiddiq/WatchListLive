package com.omellete.watchlistlive.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_table", indices = @Index(value = {"username"}, unique = true))
public class MovieFavoriteModel implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "type")
    private String itemType;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "htmlurl")
    private String year;

    @ColumnInfo(name = "posterPath")
    private String imgPosterFav;

    public MovieFavoriteModel(int id, String username, String year, String itemType, String imgPosterFav) {
        this.id = id;
        this.username = username;
        this.year = year;
        this.itemType = itemType;
        this.imgPosterFav = imgPosterFav;
    }

    protected MovieFavoriteModel(Parcel in) {
        uid = in.readInt();
        id = in.readInt();
        username = in.readString();
        year = in.readString();
        itemType = in.readString();
        imgPosterFav = in.readString();
    }

    public static final Creator<MovieFavoriteModel> CREATOR = new Creator<MovieFavoriteModel>() {
        @Override
        public MovieFavoriteModel createFromParcel(Parcel in) {
            return new MovieFavoriteModel(in);
        }

        @Override
        public MovieFavoriteModel[] newArray(int size) {
            return new MovieFavoriteModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public String getItemType() {
        return itemType;
    }

    public String getYear() {
        return year;
    }

    public String getImgPosterFav() {
        return imgPosterFav;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(uid);
        parcel.writeInt(id);
        parcel.writeString(itemType);
        parcel.writeString(username);
        parcel.writeString(year);
        parcel.writeString(imgPosterFav);
    }

}
