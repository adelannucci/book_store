package com.adelannucci.bookstore.ui.bookmarks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.adelannucci.bookstore.databinding.FragmentBookmarksBinding;

public class BookmarksFragment extends Fragment {

    private BookmarksViewModel bookmarksViewModel;
    private FragmentBookmarksBinding binding;
    private BookmarkGridAdapter bookmarkGridAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        bookmarksViewModel = new ViewModelProvider(requireActivity()).get(BookmarksViewModel.class);

        binding = FragmentBookmarksBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        bookmarkGridAdapter = new BookmarkGridAdapter();
        StaggeredGridLayoutManager layoutManager;
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binding.recyclerViewBookmarks.setLayoutManager(layoutManager);
        binding.recyclerViewBookmarks.setAdapter(bookmarkGridAdapter);

        bookmarksViewModel.getBooks().observe(getViewLifecycleOwner(), books -> {
            bookmarkGridAdapter.submitList(books);
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}