package com.adelannucci.bookstore.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adelannucci.bookstore.databinding.BookItemBinding;
import com.adelannucci.bookstore.model.Book;
import com.adelannucci.bookstore.source.remote.data.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BookGridAdapter extends RecyclerView.Adapter<BookGridAdapter.ViewHolder> {

    private static final String TAG = BookGridAdapter.class.getName();
    private List<Item> books = new ArrayList<>();

    public void updateBooks(List<Item> books) {
        this.books = books;
        notifyDataSetChanged();
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
        holder.bindView(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private BookItemBinding bookItemBinding;

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
                    Log.i(TAG, book.getVolumeInfo().getImageLinks().getThumbnail());
                }
            });
        }
    }
}
