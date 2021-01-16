package com.adelannucci.bookstore.source;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.adelannucci.bookstore.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private MutableLiveData<List<Book>> books = new MutableLiveData<List<Book>>();

    public MutableLiveData<List<Book>> fetch() {
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
        books.setValue(bookList);
        return books;
    }
}
