package com.adelannucci.bookstore.source.remote.data;

import org.parceler.Parcel;

@Parcel
public class Epub {

    boolean isAvailable;

    public Epub() {
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
