package com.adelannucci.bookstore.source.remote.data;

import org.parceler.Parcel;

@Parcel
public class ReadingModes {
    boolean text;
    boolean image;

    public ReadingModes() {
    }

    public boolean isText() {
        return text;
    }

    public void setText(boolean text) {
        this.text = text;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }
}
