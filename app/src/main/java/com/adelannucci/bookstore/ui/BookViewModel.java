package com.adelannucci.bookstore.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.adelannucci.bookstore.source.BookRepository;
import com.adelannucci.bookstore.source.remote.data.BookResponse;

public class BookViewModel extends ViewModel {

    private BookRepository bookRepository = new BookRepository();

    public LiveData<BookResponse> getBooks() {
        return bookRepository.fetch();
    }
}
