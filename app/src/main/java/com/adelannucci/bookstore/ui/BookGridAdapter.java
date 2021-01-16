package com.adelannucci.bookstore.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adelannucci.bookstore.databinding.BookItemBinding;
import com.adelannucci.bookstore.model.Book;

import java.util.List;

public class BookGridAdapter extends RecyclerView.Adapter<BookGridAdapter.ViewHolder> {

    private static final String TAG = BookGridAdapter.class.getName();
    private List<Book> books;

    public void updateBooks(List<Book> books) {
        this.books = books;
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

        public void bindView(Book book) {
            bookItemBinding.bookTitle.setText(book.getTitle());
            bookItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, bookItemBinding.bookTitle.getText().toString());
                }
            });
        }
    }
}
