package com.adelannucci.bookstore.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;

import com.adelannucci.bookstore.R;
import com.adelannucci.bookstore.databinding.ActivityBookDetailBinding;
import com.adelannucci.bookstore.source.remote.data.Item;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

public class BookDetail extends AppCompatActivity {

    private ActivityBookDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Parcelable parcelable = getIntent().getParcelableExtra("BOOK_PARCELABLE");
        Item book = Parcels.unwrap(parcelable);
        bindingLayout(book);
    }

    private void bindingLayout(Item book) {
        try {
            String secureURL = book.getVolumeInfo().getImageLinks().getThumbnail()
                    .replace("http", "https");
            Picasso.get()
                    .load(secureURL)
                    .resize(128, 182)
                    .centerCrop()
                    .into(binding.bookImage);
            binding.bookImage.setContentDescription(book.getVolumeInfo().getTitle());
        } catch (Exception e) {
            Picasso.get()
                    .load(R.drawable.book)
                    .resize(128, 182)
                    .centerCrop()
                    .into(binding.bookImage);
        }

        binding.bookTitle.setText(book.getVolumeInfo().getTitle());
        if (book.getVolumeInfo().getAuthors() != null) {
            binding.bookAuthors.setText(String.join(", ", book.getVolumeInfo().getAuthors()));
            binding.bookPublisher.setText(book.getVolumeInfo().getPublisher());
        }

        String starUnicode = new String(Character.toChars(0x2B50));
        String averageRating = String.format("%s %s", book.getVolumeInfo().getAverageRating(), starUnicode);
        binding.averageRating.setText(averageRating);

        String ratingLabel = getResources().getString(R.string.label_rating);
        String ratingsCount = String.format("%s %s", book.getVolumeInfo().getRatingsCount(), ratingLabel);
        binding.ratingsCount.setText(ratingsCount);

        String pages = "0";

        if (book.getVolumeInfo().getPageCount() >= 0) {
            pages = book.getVolumeInfo().getPageCount() + "";
        }

        binding.bookPages.setText(pages);
        binding.bookDescription.setText(book.getVolumeInfo().getDescription());

        if (book.getSaleInfo().isEbook()) {
            binding.buyUrl.setOnClickListener(v -> {
                String url = book.getSaleInfo().getBuyLink();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            });
        } else {
            binding.buyUrl.setClickable(false);
            binding.buyUrl.setEnabled(false);
        }
    }
}