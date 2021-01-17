package com.adelannucci.bookstore.source.remote.data;

import org.parceler.Parcel;

@Parcel
public class SearchInfo {

    String textSnippet;

    public SearchInfo() {
    }

    public String getTextSnippet() {
        return textSnippet;
    }

    public void setTextSnippet(String textSnippet) {
        this.textSnippet = textSnippet;
    }
}
