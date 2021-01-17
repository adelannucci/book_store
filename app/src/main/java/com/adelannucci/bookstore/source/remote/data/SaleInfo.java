package com.adelannucci.bookstore.source.remote.data;

import org.parceler.Parcel;

@Parcel
public class SaleInfo {

    String buyLink;
    String country;
    String saleability;
    boolean isEbook;

    public SaleInfo() {
    }

    public String getBuyLink() {
        return buyLink;
    }

    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSaleability() {
        return saleability;
    }

    public void setSaleability(String saleability) {
        this.saleability = saleability;
    }

    public boolean isEbook() {
        return isEbook;
    }

    public void setEbook(boolean ebook) {
        isEbook = ebook;
    }
}
