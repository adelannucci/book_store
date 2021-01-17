package com.adelannucci.bookstore.source.remote.data;

import org.parceler.Parcel;

@Parcel
public class ImageLinks {

    String smallThumbnail;
    String thumbnail;

    public ImageLinks() {
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
