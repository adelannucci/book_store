package com.adelannucci.bookstore.source;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.adelannucci.bookstore.model.Book;
import com.adelannucci.bookstore.source.remote.BookDataSource;
import com.adelannucci.bookstore.source.remote.data.BookResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRepository {
    private static final String TAG = BookRepository.class.getCanonicalName();
    private MutableLiveData<BookResponse> books = new MutableLiveData<>();
    private BookDataSource api =  new RetrofitInitializer().BookService();

    public MutableLiveData<BookResponse> fetch() {
        Call<BookResponse> call = api.getBook("android", 20L, 0L);
        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(@Nullable Call<BookResponse> call,
                                   @Nullable Response<BookResponse> response) {
                if (response != null && response.isSuccessful()) {
                    Log.i(TAG, response.body().toString());
                    books.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@Nullable Call<BookResponse> call,
                                  @Nullable Throwable t) {
                if (t != null) {
                    Log.i(TAG, t.getMessage());
                }
            }
        });

//        List<Book> bookList = new ArrayList<Book>();
//        bookList.add(new Book("Book test 1"));
//        bookList.add(new Book("Book test 2"));
//        bookList.add(new Book("Book test 3"));
//        bookList.add(new Book("Book test 4"));
//        bookList.add(new Book("Book test 5"));
//        bookList.add(new Book("Book test 6"));
//        bookList.add(new Book("Book test 7"));
//        bookList.add(new Book("Book test 8"));
//        bookList.add(new Book("Book test 9"));
//        bookList.add(new Book("Book test 10"));

        return books;
    }
}
