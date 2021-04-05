package com.example.findmovieapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel implements Parcelable {

    private String title;
    private String original_language;
    private int id;
    private float vote_average;
    private String release_date;
    private String poster_path;
    private String overview;

    public MovieModel(String title, String original_language, int id, float vote_average, String release_date, String poster_path,
                      String overview) {
        this.title = title;
        this.original_language = original_language;
        this.id = id;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.overview = overview;
    }

    protected MovieModel(Parcel in) {
        title = in.readString();
        original_language = in.readString();
        id = in.readInt();
        vote_average = in.readFloat();
        release_date = in.readString();
        poster_path = in.readString();
        overview = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(original_language);
        dest.writeInt(id);
        dest.writeFloat(vote_average);
        dest.writeString(release_date);
        dest.writeString(poster_path);
        dest.writeString(overview);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public int getId() {
        return id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOverview() {
        return overview;
    }
}
