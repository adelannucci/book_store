package com.adelannucci.bookstore.source.remote.data;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import org.parceler.Parcel;

@Parcel
public class Item {

    String kind;
    String id;
    String etag;
    String selfLink;
    VolumeInfo volumeInfo;
    SaleInfo saleInfo;
    AccessInfo accessInfo;
    SearchInfo searchInfo;

    public Item() {}

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public SaleInfo getSaleInfo() {
        return saleInfo;
    }

    public void setSaleInfo(SaleInfo saleInfo) {
        this.saleInfo = saleInfo;
    }

    public AccessInfo getAccessInfo() {
        return accessInfo;
    }

    public void setAccessInfo(AccessInfo accessInfo) {
        this.accessInfo = accessInfo;
    }

    public SearchInfo getSearchInfo() {
        return searchInfo;
    }

    public void setSearchInfo(SearchInfo searchInfo) {
        this.searchInfo = searchInfo;
    }

    public static final DiffUtil.ItemCallback<Item> BOOK_COMPARATOR = new DiffUtil.ItemCallback<Item>() {
        @SuppressLint("DiffUtilEquals")
        @Override public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.getId() == newItem.getId();
        }
    };
}
