package com.adelannucci.bookstore.ui.details;

import android.app.Application;

import com.adelannucci.bookstore.source.local.BookRepository;
import com.adelannucci.bookstore.source.local.data.Book;
import com.adelannucci.bookstore.source.remote.data.ImageLinks;
import com.adelannucci.bookstore.source.remote.data.Item;
import com.adelannucci.bookstore.source.remote.data.SaleInfo;
import com.adelannucci.bookstore.source.remote.data.VolumeInfo;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

@RunWith(JUnit4.class)
public class BookDetailsViewModelTest extends TestCase {

    @Mock
    private BookDetailsViewModel viewModel;
    @Mock
    private BookRepository repository;

    @Mock
    private Application application;

    Item item = new Item();
    VolumeInfo volumeInfo = new VolumeInfo();
    ImageLinks imageLinks = new ImageLinks();
    SaleInfo saleInfo = new SaleInfo();


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        application = Mockito.mock(Application.class);
        repository = Mockito.mock(BookRepository.class);
        viewModel = new BookDetailsViewModel(application, repository);

        imageLinks.setSmallThumbnail("http://books.google.com/books/content?id=4RCEGbMrZ7oC&printsec=frontcover&img=1&zoom=5&edge=curl&imgtk=AFLRE71Um0aZueL-2toCX590ZelYXfhKrO5Z6-Yg9UQwUA9bKwHSLs0R6fq7VX9AygEctPSFuPrtmc-o4WVnkIh04HjG2GssL6HSxmMqTNK9LXLadi6g8GHuzBEIQ35Sh5ZOWRHkdy8-&source=gbs_api");
        imageLinks.setThumbnail("http://books.google.com/books/content?id=4RCEGbMrZ7oC&printsec=frontcover&img=1&zoom=1&edge=curl&imgtk=AFLRE70COA-rTeJxshhBVbnr74qwcNBCw2QDF_tEiAY50HummkD3hW1JUlq-9pQ53jwpzMNndRNTvOnVELCAZjlcRZzJYyMvu1zWGXiP2dEV7VnGNA_pLCNr1RFXbB8zAGUV41sPEQyG&source=gbs_api");
        volumeInfo.setImageLinks(imageLinks);

        volumeInfo.setAuthors(Arrays.asList("Jonathan Stark",
                "Paco Nathan",
                "John Papaconstantinou",
                "Paco Lagerstrom",
                "Paco Hope"));

        volumeInfo.setTitle("Building Android Apps with HTML, CSS, and JavaScript");
        volumeInfo.setAverageRating(2D);
        volumeInfo.setRatingsCount(5);
        volumeInfo.setImageLinks(imageLinks);

        saleInfo.setEbook(false);
        item.setVolumeInfo(volumeInfo);
        item.setId("4RCEGbMrZ7oC");
        item.setSaleInfo(saleInfo);

//        when(application.getString(R.string.label_rating))
//                .thenReturn("rating");
    }

    @Test
    public void testBookmarkIsNonNull() {
        viewModel.setBookmark(item);
        Book book = viewModel.getBookmark();
        assertNotNull(book);
    }

    @Test
    public void testBookmarkWhenItemNonHaveImageLinks() {
        item.getVolumeInfo().setImageLinks(new ImageLinks());
        viewModel.setBookmark(item);
        Book book = viewModel.getBookmark();
        assertNotNull(book);
    }

    @Test
    public void testBookmarkWhenItemNonHaveAuthors() {
        item.getVolumeInfo().setAuthors(null);
        ;
        viewModel.setBookmark(item);
        Book book = viewModel.getBookmark();
        assertNotNull(book);
    }

    @After
    public void tearDown() throws Exception {
        viewModel = null;
        volumeInfo = null;
        imageLinks = null;
        saleInfo = null;
    }
}