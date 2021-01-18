package com.adelannucci.bookstore.source.local;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.adelannucci.bookstore.source.local.data.Book;

import java.util.List;

public class BookRepository {

    private BookDao dao;
    private LiveData<List<Book>> books;

    public BookRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        dao = database.bookDao();
        books = dao.findAll();
    }

    public LiveData<List<Book>> getAllBooks() {
        return books;
    }

    public void insert(Book book) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            dao.insert(book);
        });
    }

    public void delete(Book book) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            dao.delete(book);
        });
    }
}