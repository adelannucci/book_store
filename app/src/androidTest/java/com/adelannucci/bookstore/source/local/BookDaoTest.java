package com.adelannucci.bookstore.source.local;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.adelannucci.bookstore.source.local.data.Book;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(JUnit4.class)
public class BookDaoTest extends TestCase {

    private AppDatabase db;
    private BookDao dao;
    private Book book;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        dao = db.bookDao();
        book = new Book();
        book.bookId = "...";
        book.thumbnail = "...";
        book.title = "...";
        book.description = "...";
        book.authors = "...";
        book.buyLink = "...";
        book.publisher = "...";
        book.pages = "...";
        book.ratingsCount = "...";
        book.averageRating = "...";
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void testInsertBook() throws Exception {
        dao.insert(book);
        List<Book> books = getOrAwaitValue(dao.findAll());
        assertEquals(books.size(),  1);
    }

    @Test
    public void testDeleteBook() throws Exception {
        dao.insert(book);
        List<Book> books = getOrAwaitValue(dao.findAll());
        Book book = books.get(0);
        dao.delete(book);
        books = getOrAwaitValue(dao.findAll());
        assertEquals(books.size(),  0);
    }

    public static <T> T getOrAwaitValue(final LiveData<T> liveData) throws InterruptedException {
        final Object[] data = new Object[1];
        final CountDownLatch latch = new CountDownLatch(1);
        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(@Nullable T o) {
                data[0] = o;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };
        liveData.observeForever(observer);
        latch.await(2, TimeUnit.SECONDS);
        return (T) data[0];
    }
}