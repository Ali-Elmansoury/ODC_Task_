package com.example.odc_task.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.odc_task.models.ProductsResponse;
import com.example.odc_task.repository.Repository;
import com.example.odc_task.repository.Repository_ID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivityViewModel extends ViewModel {
    public MutableLiveData<ProductsResponse> ListMutableLiveData = new MutableLiveData<>();

    Repository repo = new Repository();
    public void getData() {

        Call<ProductsResponse> call = repo.getProducts();
        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                ListMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                Log.e("error",t.getMessage());
            }
        });

    }
}
