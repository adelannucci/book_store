package com.adelannucci.bookstore.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.adelannucci.bookstore.R;
import com.adelannucci.bookstore.databinding.ActivityMainBinding;
import com.adelannucci.bookstore.ui.book.BookViewModel;
import com.adelannucci.bookstore.ui.bookmarks.BookmarksViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private BookViewModel bookViewModel;
    private BookmarksViewModel bookmarksViewModel;
    private NavController navController;
    private MenuItem menuItem;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.topBar.toolbar);

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_book_store, R.id.nav_bookmarks)
                .setOpenableLayout(binding.drawerLayout)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        binding.navView.setNavigationItemSelectedListener(this);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        bookmarksViewModel = new ViewModelProvider(this).get(BookmarksViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                bookViewModel.getSearchResults().postValue(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        bookViewModel.setNavController(navController);
        bookmarksViewModel.setNavController(navController);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.nav_book_store) {
            menuItem.setVisible(true);
        } else {
            menuItem.setVisible(false);
        }
        Objects.requireNonNull(mAppBarConfiguration.getOpenableLayout()).close();
        navController.navigate(item.getItemId());
        return true;
    }
}