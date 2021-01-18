package com.adelannucci.bookstore.source.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.adelannucci.bookstore.source.local.data.Book;

import java.util.List;

@Dao
public interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Book book);

    @Update
    void update(Book book);

    @Query("SELECT * FROM book")
    LiveData<List<Book>> findAll();

    @Delete
    void delete(Book book);
}
