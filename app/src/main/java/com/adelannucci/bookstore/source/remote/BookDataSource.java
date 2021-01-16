package com.adelannucci.bookstore.source.remote;

import com.adelannucci.bookstore.source.remote.data.BookResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookDataSource {

    @GET("volumes")
    Call<BookResponse> getBook(@Query("q") String title,
                               @Query("maxResults") Long pageSize,
                               @Query("startIndex") Long page);
}
