package com.adelannucci.bookstore.ui.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.adelannucci.bookstore.R;
import com.adelannucci.bookstore.databinding.ActivityBookDetailsBinding;
import com.adelannucci.bookstore.source.local.data.Book;
import com.adelannucci.bookstore.source.remote.data.Item;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.Objects;

public class BookDetails extends AppCompatActivity {

    private ActivityBookDetailsBinding binding;
    private BookDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        viewModel = new ViewModelProvider(this).get(BookDetailsViewModel.class);

        Parcelable parcelableItem = getIntent().getParcelableExtra("BOOK_PARCELABLE");
        Parcelable parcelableBookmarks = getIntent().getParcelableExtra("BOOKMARKS_PARCELABLE");
        if (parcelableItem != null) {
            Item item = Parcels.unwrap(parcelableItem);
            viewModel.setBookmark(item);
            viewModel.setFavorite(false);
        }

        if (parcelableBookmarks != null) {
            Book book = Parcels.unwrap(parcelableBookmarks);
            viewModel.setBookmark(book);
            viewModel.setFavorite(true);
        }
        bindingLayout();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void bindingLayout() {
        Book bookmark = viewModel.getBookmark();
        if (bookmark.thumbnail != null) {
            Picasso.get()
                    .load(bookmark.thumbnail)
                    .resize(128, 182)
                    .centerCrop()
                    .into(binding.bookImage);
        } else {
            Picasso.get()
                    .load(R.drawable.book)
                    .resize(128, 182)
                    .centerCrop()
                    .into(binding.bookImage);
        }

        binding.bookImage.setContentDescription(bookmark.title);
        binding.bookTitle.setText(bookmark.title);
        binding.bookAuthors.setText(bookmark.authors);
        binding.bookPublisher.setText(bookmark.publisher);
        binding.averageRating.setText(bookmark.averageRating);
        binding.ratingsCount.setText(bookmark.ratingsCount);
        binding.bookPages.setText(bookmark.pages);
        binding.bookDescription.setText(bookmark.description);

        if (bookmark.buyLink != null) {
            binding.buyUrl.setOnClickListener(v -> {
                String url = bookmark.buyLink;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            });
        } else {
            binding.buyUrl.setClickable(false);
            binding.buyUrl.setEnabled(false);
        }

        if (viewModel.isFavorite()) {
            binding.addRemoveBookmarks.setText(R.string.label_remove_bookmarks);
        } else {
            binding.addRemoveBookmarks.setText(R.string.label_add_bookmarks);
        }

        binding.addRemoveBookmarks.setOnClickListener(v -> {
            if (viewModel.isFavorite()) {
                binding.addRemoveBookmarks.setText(R.string.label_remove_bookmarks);
                viewModel.getRepository().delete(bookmark);
                viewModel.setFavorite(false);
                binding.addRemoveBookmarks.setText(R.string.label_add_bookmarks);
                Toast.makeText(getApplicationContext(), R.string.remove_bookmarks, Toast.LENGTH_SHORT).show();
            } else {
                binding.addRemoveBookmarks.setText(R.string.label_add_bookmarks);
                viewModel.getRepository().insert(bookmark);
                viewModel.setFavorite(true
                );
                binding.addRemoveBookmarks.setText(R.string.label_remove_bookmarks);
                Toast.makeText(getApplicationContext(), R.string.add_bookmarks, Toast.LENGTH_SHORT).show();
            }
        });
    }


}