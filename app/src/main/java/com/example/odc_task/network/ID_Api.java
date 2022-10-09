package com.example.odc_task.network;

import com.example.odc_task.models.ProductsResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ID_Api {
    String BASE_URL = "https://dummyjson.com/";

    @GET("products/")
    Call<ProductsResponse> getProductsById();
}
