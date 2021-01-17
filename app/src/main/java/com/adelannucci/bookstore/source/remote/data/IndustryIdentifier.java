package com.adelannucci.bookstore.source.remote.data;

import org.parceler.Parcel;

@Parcel
public class IndustryIdentifier {
    String type;
    String identifier;

    public IndustryIdentifier() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
