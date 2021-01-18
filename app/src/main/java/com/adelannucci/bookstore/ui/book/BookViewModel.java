package com.adelannucci.bookstore.ui.book;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.adelannucci.bookstore.source.BookDataSource;
import com.adelannucci.bookstore.source.DataSourceFactory;
import com.adelannucci.bookstore.source.remote.data.Item;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BookViewModel extends ViewModel {

    LiveData<PagedList<Item>> bookPagedList;
    LiveData<BookDataSource> liveDataSource;
    private Executor executor;
    private String query = "android";
    private MutableLiveData<String> searchResults = new MutableLiveData<>();

    public BookViewModel() {
        super();
        init();
    }

    public void setQuery(String query) {
        this.query = query;
        init();
    }

    private void init() {
        DataSourceFactory itemDataSourceFactory = new DataSourceFactory(query);
        liveDataSource = itemDataSourceFactory.getBookLiveDataSource();
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(BookDataSource.PAGE_SIZE)
                .build();

        executor = Executors.newFixedThreadPool(5);

        bookPagedList = (new LivePagedListBuilder<Long, Item>(itemDataSourceFactory, config))
                .setFetchExecutor(executor)
                .build();
    }

    public MutableLiveData<String> getSearchResults() {
        return searchResults;
    }
}
