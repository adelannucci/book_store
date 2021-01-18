package com.adelannucci.bookstore.source.local.data;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.parceler.Parcel;

@Parcel
@Entity(indices = {@Index(value = {"book_id"}, unique = true)})
public class Book {

    @PrimaryKey
    public Long id;

    @ColumnInfo(name = "book_id")
    public String bookId;
    public String title;
    public String authors;
    public String publisher;
    public String thumbnail;
    public String pages;
    public String description;
    @ColumnInfo(name = "average_rating")
    public String averageRating;
    @ColumnInfo(name = "ratings_count")
    public String ratingsCount;
    @ColumnInfo(name = "buy_link")
    public String buyLink;

    public static final DiffUtil.ItemCallback<Book> BOOK_COMPARATOR = new DiffUtil.ItemCallback<Book>() {
        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areItemsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem.bookId == newItem.bookId;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem.bookId == newItem.bookId;
        }
    };
}
