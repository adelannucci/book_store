package com.adelannucci.bookstore.ui.book;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.adelannucci.bookstore.source.BookDataSource;
import com.adelannucci.bookstore.source.DataSourceFactory;
import com.adelannucci.bookstore.source.remote.data.Item;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BookViewModel extends AndroidViewModel {

    LiveData<PagedList<Item>> bookPagedList;
    LiveData<BookDataSource> liveDataSource;
    private Executor executor;
    private String query = "android";
    private MutableLiveData<String> searchResults = new MutableLiveData<>();
    private NavController navController;

    public BookViewModel(Application application) {
        super(application);
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

        bookPagedList = (new LivePagedListBuilder<>(itemDataSourceFactory, config))
                .setFetchExecutor(executor)
                .build();
    }

    public MutableLiveData<String> getSearchResults() {
        return searchResults;
    }

    public void setNavController(NavController navController) {
        this.navController = navController;
    }

    public NavController getNavController() {
        return navController;
    }
}
