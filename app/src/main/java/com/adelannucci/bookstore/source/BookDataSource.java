package com.adelannucci.bookstore.source;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PageKeyedDataSource;

import com.adelannucci.bookstore.source.remote.BookApiInterface;
import com.adelannucci.bookstore.source.remote.data.BookResponse;
import com.adelannucci.bookstore.source.remote.data.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDataSource extends PageKeyedDataSource<Long, Item> {
    private final static String TAG = BookDataSource.class.getName();
    public static Integer PAGE_SIZE = 20;
    public static Long FIRST_PAGE = 0L;
    private final BookApiInterface api = new RetrofitInitializer().BookService();
    private final String query;

    public BookDataSource(String query) {
        this.query = query;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params,
                            @NonNull LoadInitialCallback<Long, Item> callback) {
        Call<BookResponse> call = api.getBook(query, PAGE_SIZE, FIRST_PAGE);
        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(@Nullable Call<BookResponse> call,
                                   @Nullable Response<BookResponse> response) {
                if (response != null && response.isSuccessful()) {
                    Log.i(TAG, response.body().toString());
                    List<Item> responseItems = response.body().getItems();
                    callback.onResult(responseItems, null, FIRST_PAGE + 1);
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
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params,
                           @NonNull LoadCallback<Long, Item> callback) {
        Call<BookResponse> call = api.getBook(query, PAGE_SIZE, params.key);
        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(@Nullable Call<BookResponse> call,
                                   @Nullable Response<BookResponse> response) {
                if (response != null && response.isSuccessful()) {
                    Log.i(TAG, response.body().toString());
                    List<Item> responseItems = response.body().getItems();
                    long key;
                    if (params.key > 1) {
                        key = params.key - 1;
                    } else {
                        key = 0L;
                    }
                    callback.onResult(responseItems, key);
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
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params,
                          @NonNull LoadCallback<Long, Item> callback) {
        Call<BookResponse> call = api.getBook(query, PAGE_SIZE, params.key);
        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(@Nullable Call<BookResponse> call,
                                   @Nullable Response<BookResponse> response) {
                if (response != null && response.isSuccessful()) {
                    Log.i(TAG, response.body().toString());
                    List<Item> responseItems = response.body().getItems();
                    callback.onResult(responseItems, params.key + 1);
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

    }
}
