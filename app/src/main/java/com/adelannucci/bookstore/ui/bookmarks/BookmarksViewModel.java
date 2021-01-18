package com.adelannucci.bookstore.ui.bookmarks;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.adelannucci.bookstore.source.local.BookRepository;
import com.adelannucci.bookstore.source.local.data.Book;

import java.util.List;

public class BookmarksViewModel extends AndroidViewModel {

    private BookRepository repository;
    private final LiveData<List<Book>> books;

    public BookmarksViewModel(Application application) {
        super(application);
        repository = new BookRepository(application);
        books = repository.getAllBooks();
    }

    public LiveData<List<Book>> getBooks() {
        return books;
    }

    public void insert(Book book) {
        repository.insert(book);
    }

}