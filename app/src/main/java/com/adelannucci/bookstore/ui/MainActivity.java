package com.adelannucci.bookstore.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.adelannucci.bookstore.databinding.ActivityMainBinding;
import com.adelannucci.bookstore.model.Book;
import com.adelannucci.bookstore.source.remote.data.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private BookGridAdapter bookGridAdapter;
    private BookViewModel bookViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bookGridAdapter = new BookGridAdapter();
        StaggeredGridLayoutManager layoutManager;
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binding.recyclerViewBooks.setLayoutManager(layoutManager);
        binding.recyclerViewBooks.setAdapter(bookGridAdapter);
        

        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        bookViewModel.getBooks().observe(this, books -> {
            List<Item> list = new ArrayList<>();
            if (books.getItems() != null || books.getItems().size() > 0) {
                list.addAll(books.getItems());
            }

            bookGridAdapter.updateBooks(list);
        });
    }
}