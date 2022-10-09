package com.example.odc_task.repository;

import com.example.odc_task.models.Products;
import com.example.odc_task.models.ProductsResponse;
import com.example.odc_task.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class Repository {

    public Call<ProductsResponse> getProducts() {
        return RetrofitClient.getInstance().getMyApi().getProductsResponse();
    }

}
