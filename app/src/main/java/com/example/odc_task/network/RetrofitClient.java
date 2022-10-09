package com.example.odc_task.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private ProductsApi myApi;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ProductsApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        myApi = retrofit.create(ProductsApi.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public ProductsApi getMyApi() {
        return myApi;
    }
}
