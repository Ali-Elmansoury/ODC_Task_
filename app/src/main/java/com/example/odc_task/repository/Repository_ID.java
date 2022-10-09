package com.example.odc_task.repository;

import com.example.odc_task.models.ProductsResponse;
import com.example.odc_task.network.RetrofitClientByID;

import retrofit2.Call;

public class Repository_ID {
    public Call<ProductsResponse> getProductsById() {
        return RetrofitClientByID.getInstance().getMyApi().getProductsById();
    }

}
