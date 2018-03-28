package com.example.android.imageloader;

import android.graphics.Bitmap;

/**
 * Created by inoue on 24/03/2018.
 */

public class Movie {

    private String title, genre, year;
    private Bitmap thumbnailBmp;

    public Movie(){}

    public Movie(String title, String genre, String year){
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    public Movie(String title, String genre, String year, Bitmap thumbnailBmp){
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.thumbnailBmp = thumbnailBmp;
    }

    public String getTitle() {return title;}
    public void setTitle(String name) {this.title = name;}
    public String getYear() {return year;}
    public void setYear(String year) {this.year = year;}
    public String getGenre() {return genre;}
    public void setGenre(String genre) {this.genre = genre;}
    public Bitmap getThumbnailBmp(){return this.thumbnailBmp;}
    public void setThumbnailBmp(Bitmap thumbnailBmp){this.thumbnailBmp = thumbnailBmp;}

}
