package com.adelannucci.bookstore.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adelannucci.bookstore.R;
import com.adelannucci.bookstore.databinding.ActivityBookDetailBinding;
import com.adelannucci.bookstore.source.local.BookRepository;
import com.adelannucci.bookstore.source.local.data.Book;
import com.adelannucci.bookstore.source.remote.data.Item;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

public class BookDetail extends AppCompatActivity {

    private ActivityBookDetailBinding binding;
    private BookRepository repository;
    Book bookmark = new Book();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Parcelable parcelable = getIntent().getParcelableExtra("BOOK_PARCELABLE");
        if(parcelable != null) {
            Item book = Parcels.unwrap(parcelable);
            parseToBookmark(book);

            binding.addRemoveBookmarks.setText(R.string.label_add_bookmarks);
            binding.addRemoveBookmarks.setOnClickListener(v -> {
                repository.insert(bookmark);
                Toast.makeText(getApplicationContext(), R.string.label_add_bookmarks, Toast.LENGTH_LONG).show();
            });
        } else {
            parcelable = getIntent().getParcelableExtra("BOOKMARKS_PARCELABLE");
            bookmark = Parcels.unwrap(parcelable);
            binding.addRemoveBookmarks.setText(R.string.label_remove_bookmarks);
            binding.addRemoveBookmarks.setOnClickListener(v -> {
                repository.delete(bookmark);
                Toast.makeText(getApplicationContext(), R.string.label_remove_bookmarks, Toast.LENGTH_LONG).show();
            });
        }

        repository = new BookRepository(getApplication());
        bindingLayout();
    }

    private void bindingLayout() {

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
    }

    private void parseToBookmark(Item book) {
        try {
            String secureURL = book.getVolumeInfo().getImageLinks().getThumbnail()
                    .replace("http", "https");
            bookmark.thumbnail = secureURL;
        } catch (Exception e) {
            Picasso.get()
                    .load(R.drawable.book)
                    .resize(128, 182)
                    .centerCrop()
                    .into(binding.bookImage);
        }

        if (book.getVolumeInfo().getAuthors() != null) {
            bookmark.authors = String.join(", ", book.getVolumeInfo().getAuthors());
        }

        bookmark.bookId = book.getId();
        bookmark.title = book.getVolumeInfo().getTitle();
        bookmark.publisher = book.getVolumeInfo().getPublisher();

        String starUnicode = new String(Character.toChars(0x2B50));
        String averageRating = String.format("%s %s", book.getVolumeInfo().getAverageRating(), starUnicode);
        bookmark.averageRating = averageRating;

        String ratingLabel = getResources().getString(R.string.label_rating);
        String ratingsCount = String.format("%s %s", book.getVolumeInfo().getRatingsCount(), ratingLabel);
        bookmark.ratingsCount = ratingsCount;

        String pages = "0";

        if (book.getVolumeInfo().getPageCount() >= 0) {
            pages = book.getVolumeInfo().getPageCount() + "";
            bookmark.pages = pages;
        }

        bookmark.description = book.getVolumeInfo().getDescription();

        if (book.getSaleInfo().isEbook()) {
            bookmark.buyLink = book.getSaleInfo().getBuyLink();
        }
    }
}