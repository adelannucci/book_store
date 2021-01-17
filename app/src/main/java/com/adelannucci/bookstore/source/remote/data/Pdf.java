package com.adelannucci.bookstore.source.remote.data;

import org.parceler.Parcel;

@Parcel
public class Pdf {

    boolean isAvailable;
    String acsTokenLink;

    public Pdf() {
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getAcsTokenLink() {
        return acsTokenLink;
    }

    public void setAcsTokenLink(String acsTokenLink) {
        this.acsTokenLink = acsTokenLink;
    }
}
