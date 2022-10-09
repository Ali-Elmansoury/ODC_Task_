package com.example.odc_task.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientByID {
    private static RetrofitClientByID instance = null;
    private ID_Api myApi;

    private RetrofitClientByID() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ID_Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        myApi = retrofit.create(ID_Api.class);
    }

    public static synchronized RetrofitClientByID getInstance() {
        if (instance == null) {
            instance = new RetrofitClientByID();
        }
        return instance;
    }

    public ID_Api getMyApi() {
        return myApi;
    }
}
