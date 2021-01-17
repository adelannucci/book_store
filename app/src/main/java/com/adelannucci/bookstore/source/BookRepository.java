package com.adelannucci.bookstore.source;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.adelannucci.bookstore.source.remote.BookDataSource;
import com.adelannucci.bookstore.source.remote.data.BookResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRepository {
    private static final String TAG = BookRepository.class.getName();
    private final MutableLiveData<BookResponse> books = new MutableLiveData<>();
    private final BookDataSource api =  new RetrofitInitializer().BookService();

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
        return books;
    }
}
