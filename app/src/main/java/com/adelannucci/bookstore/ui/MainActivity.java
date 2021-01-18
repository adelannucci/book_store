package com.adelannucci.bookstore.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.adelannucci.bookstore.R;
import com.adelannucci.bookstore.databinding.ActivityMainBinding;
import com.adelannucci.bookstore.source.remote.data.Item;

public class MainActivity extends AppCompatActivity {
    private BookGridAdapter bookGridAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private BookViewModel bookViewModel = new BookViewModel(this.getApplication());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bookGridAdapter = new BookGridAdapter();
        StaggeredGridLayoutManager layoutManager;
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binding.recyclerViewBooks.setLayoutManager(layoutManager);
        binding.recyclerViewBooks.setAdapter(bookGridAdapter);

        swipeRefreshLayout = binding.swipeLayout;
        swipeRefreshLayout.setOnRefreshListener(this::getBooks);
        getBooks();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                bookViewModel.setQuery(query);
                getBooks();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void getBooks() {
        bookViewModel.bookPagedList.observe(this, this::showOnRecyclerView);
    }

    private void showOnRecyclerView(PagedList<Item> books) {
        bookGridAdapter.submitList(books);
        bookGridAdapter.notifyDataSetChanged();
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}