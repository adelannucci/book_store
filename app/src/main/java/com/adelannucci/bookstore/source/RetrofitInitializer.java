package com.adelannucci.bookstore.source;

import com.adelannucci.bookstore.source.remote.BookApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInitializer {

    private final static String BASE_URL = "https://www.googleapis.com/books/v1/";

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public BookApiInterface BookService() {
        return retrofit.create(BookApiInterface.class);
    }
}
