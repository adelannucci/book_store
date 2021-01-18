package com.adelannucci.bookstore.ui.bookmarks;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.adelannucci.bookstore.R;
import com.adelannucci.bookstore.databinding.BookItemBinding;
import com.adelannucci.bookstore.source.local.data.Book;
import com.adelannucci.bookstore.ui.BookDetail;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

public class BookmarkGridAdapter extends ListAdapter<Book, BookmarkGridAdapter.ViewHolder> {

    private static final String TAG = BookmarkGridAdapter.class.getName();

    public BookmarkGridAdapter() {
        super(Book.BOOK_COMPARATOR);
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        BookItemBinding binding = BookItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = getItem(position);
        holder.bindView(book);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final BookItemBinding bookItemBinding;

        ViewHolder(@NonNull BookItemBinding item) {
            super(item.getRoot());
            bookItemBinding = item;
        }

        public void bindView(Book book) {
            if (book.thumbnail != null) {
                Picasso.get()
                        .load(book.thumbnail)
                        .resize(128, 182)
                        .centerCrop()
                        .into(bookItemBinding.bookImage);
                bookItemBinding.bookImage.setContentDescription(book.title);
            } else {
                Picasso.get()
                        .load(R.drawable.book)
                        .resize(128, 182)
                        .centerCrop()
                        .into(bookItemBinding.bookImage);
            }

            bookItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openBookDetails(book, v.getContext());
                }
            });
        }
    }

    public static void openBookDetails(@NonNull Book book, @NonNull Context context) {
        Parcelable parcelable = Parcels.wrap(book);
        Intent intent = new Intent(context, BookDetail.class);
        intent.putExtra("BOOKMARKS_PARCELABLE", parcelable);
        context.startActivity(intent);
    }
}