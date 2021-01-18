package com.adelannucci.bookstore.ui.details;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.adelannucci.bookstore.R;
import com.adelannucci.bookstore.source.local.BookRepository;
import com.adelannucci.bookstore.source.local.data.Book;
import com.adelannucci.bookstore.source.remote.data.Item;

public class BookDetailsViewModel extends AndroidViewModel {

    private BookRepository repository;
    private Book bookmark = new Book();
    private Boolean isFavorite = false;

    public BookDetailsViewModel(Application application) {
        super(application);
        repository = new BookRepository(application);
    }

    public BookRepository getRepository() {
        return repository;
    }

    public Book getBookmark() {
        return bookmark;
    }

    public void setBookmark(Book bookmark) {
        this.bookmark = bookmark;
    }

    public void setBookmark(Item book) {
        try {
            bookmark.thumbnail = book.getVolumeInfo().getImageLinks().getThumbnail()
                    .replace("http", "https");
        } catch (Exception e) {
            bookmark.thumbnail = null;
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

        String ratingLabel = super.getApplication().getString(R.string.label_rating);
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

    public Boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }
}
