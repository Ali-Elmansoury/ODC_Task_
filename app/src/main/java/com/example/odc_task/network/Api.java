package com.example.odc_task.network;

import com.example.odc_task.models.Products;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://dummyjson.com/";

    @GET("products")
    Call<List<Products>> getProducts();
}
