package com.example.odc_task.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.odc_task.ClickListener;
import com.example.odc_task.R;
import com.example.odc_task.adapter.UpdatedStaggeredGridAdapter;
import com.example.odc_task.models.Products;
import com.example.odc_task.models.ProductsResponse;
import com.example.odc_task.repository.Repository_ID;
import com.example.odc_task.viewModel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    MainActivityViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = new ViewModelProvider(this).get(MainActivityViewModel.class);
        model.getData();
       model.ListMutableLiveData.observe(this, new Observer<ProductsResponse>() {
           @Override
           public void onChanged(ProductsResponse products) {
               RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
               StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
               recyclerView.setLayoutManager(staggeredGridLayoutManager);
               ClickListener listener = new ClickListener() {
                   @Override
                   public void click(int index) {
                       super.click(index);
                       Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                       intent.putExtra("id",products.products.get(index).id);
                       startActivity(intent);
                   }
               };
               UpdatedStaggeredGridAdapter adapter = new UpdatedStaggeredGridAdapter(products.products,products.limit,listener);
               recyclerView.setAdapter(adapter);
               adapter.notifyDataSetChanged();
           }
       });

    }
}