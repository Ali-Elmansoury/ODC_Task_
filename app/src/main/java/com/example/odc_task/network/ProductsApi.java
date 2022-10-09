package com.example.odc_task.network;

import com.example.odc_task.models.Products;
import com.example.odc_task.models.ProductsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductsApi {
    String BASE_URL = "https://dummyjson.com/";

    @GET("products")
    Call<ProductsResponse> getProductsResponse();
}
