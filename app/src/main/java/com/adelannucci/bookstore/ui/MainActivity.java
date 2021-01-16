package com.adelannucci.bookstore.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.adelannucci.bookstore.databinding.ActivityMainBinding;
import com.adelannucci.bookstore.model.Book;

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
            bookGridAdapter.updateBooks(books);
        });
    }

    private void initBookList() {
        List<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book("Book test 1"));
        bookList.add(new Book("Book test 2"));
        bookList.add(new Book("Book test 3"));
        bookList.add(new Book("Book test 4"));
        bookList.add(new Book("Book test 5"));
        bookList.add(new Book("Book test 6"));
        bookList.add(new Book("Book test 7"));
        bookList.add(new Book("Book test 8"));
        bookList.add(new Book("Book test 9"));
        bookList.add(new Book("Book test 10"));
        bookGridAdapter.updateBooks(bookList);

    }
}