package com.adelannucci.bookstore.source;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.adelannucci.bookstore.source.remote.data.Item;

public class DataSourceFactory extends DataSource.Factory<Long, Item> {
    private MutableLiveData<BookDataSource> bookLiveDataSource = new MutableLiveData<>();
    private BookDataSource dataSource;

    public DataSourceFactory(String query) {
        dataSource = new BookDataSource(query);
    }

    @Override
    public DataSource<Long, Item> create() {
        bookLiveDataSource.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<BookDataSource> getBookLiveDataSource() {
        return bookLiveDataSource;
    }
}