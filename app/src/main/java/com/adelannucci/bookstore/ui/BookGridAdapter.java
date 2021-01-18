package com.adelannucci.bookstore.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.adelannucci.bookstore.databinding.BookItemBinding;
import com.adelannucci.bookstore.source.remote.data.Item;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

public class BookGridAdapter extends PagedListAdapter<Item, BookGridAdapter.ViewHolder> {

    private static final String TAG = BookGridAdapter.class.getName();

    public BookGridAdapter() {
        super(Item.BOOK_COMPARATOR);
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
        Item book = getItem(position);
        holder.bindView(book);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final BookItemBinding bookItemBinding;

        ViewHolder(@NonNull BookItemBinding item) {
            super(item.getRoot());
            bookItemBinding = item;
        }

        public void bindView(Item book) {
            String secureURL = book.getVolumeInfo().getImageLinks().getThumbnail()
                    .replace("http", "https");

            bookItemBinding.bookImage.setContentDescription(book.getVolumeInfo().getImageLinks().getThumbnail());
            Picasso.get()
                    .load(secureURL)
                    .resize(128, 182)
                    .centerCrop()
                    .into(bookItemBinding.bookImage);

            bookItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openBookDetails(book, v.getContext());
                }
            });
        }
    }

    public static void openBookDetails(@NonNull Item book, @NonNull Context context) {
        Log.i(TAG, book.getVolumeInfo().getImageLinks().getThumbnail());
        Parcelable parcelable = Parcels.wrap(book);
        Intent intent = new Intent(context, BookDetail.class);
        intent.putExtra("BOOK_PARCELABLE", parcelable);
        context.startActivity(intent);
    }
}
