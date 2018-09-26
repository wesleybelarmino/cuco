package com.app.cuco.data.network;

import com.app.cuco.data.network.interfaces.IApiRequests;
import com.app.cuco.data.network.interfaces.IApiServiceGet;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRequest implements IApiRequests{

    private final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/";
    private Retrofit retrofit;

    public ApiRequest() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    }

    @Override public IApiServiceGet get() {
        return retrofit.create(IApiServiceGet.class);
    }
}
