package com.adelannucci.bookstore.ui.book;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.adelannucci.bookstore.databinding.FragmentBookBinding;
import com.adelannucci.bookstore.source.remote.data.Item;

public class BookFragment extends Fragment {
    private BookGridAdapter bookGridAdapter;
    private BookViewModel bookViewModel;
    private FragmentBookBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        bookViewModel = new ViewModelProvider(requireActivity()).get(BookViewModel.class);

        binding = FragmentBookBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        bookGridAdapter = new BookGridAdapter();
        StaggeredGridLayoutManager layoutManager;
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binding.recyclerViewBooks.setLayoutManager(layoutManager);
        binding.recyclerViewBooks.setAdapter(bookGridAdapter);

        bookViewModel.getSearchResults().observe(getViewLifecycleOwner(), query -> {
            bookViewModel.setQuery(query);
            getBooks();
        });

        getBooks();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void getBooks() {
        bookViewModel.bookPagedList.observe(getViewLifecycleOwner(), this::updateBooks);
    }

    private void updateBooks(@NonNull PagedList<Item> books) {
        bookGridAdapter.submitList(books);
        bookGridAdapter.notifyDataSetChanged();
    }

}