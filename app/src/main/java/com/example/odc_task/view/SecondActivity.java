package com.example.odc_task.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.odc_task.R;
import com.example.odc_task.models.ProductsResponse;
import com.example.odc_task.viewModel.SecondActivityViewModel;

import org.json.JSONException;

import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity {

    SecondActivityViewModel model;
    TextView name,price,quantity,rating,reviews,description,dis;
    ImageView plus,minus,bookmark;
    Button add;
    String url = "https://dummyjson.com/products/",imgURL;
    ImageSlider imageSlider;
    ArrayList<SlideModel> imgSlide;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);

        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        quantity = findViewById(R.id.quantity);
        rating = findViewById(R.id.rating);
        reviews = findViewById(R.id.reviews);
        description = findViewById(R.id.description);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        bookmark = findViewById(R.id.bookmark);
        add = findViewById(R.id.add_to_cart);
        dis = findViewById(R.id.discount_percent);

        model = new ViewModelProvider(this).get(SecondActivityViewModel.class);
        model.getData();
        model.ListMutableLiveData.observe(this, new Observer<ProductsResponse>() {
            @Override
            public void onChanged(ProductsResponse productsResponse) {
                final int[] counter = {Integer.parseInt(quantity.getText().toString())};
                  name.setText(productsResponse.products.get(id-1).title);
                  price.setText(String.valueOf(productsResponse.products.get(id-1).price)+"$");
                  rating.setText(String.valueOf(productsResponse.products.get(id-1).rating));
                  reviews.setText(String.valueOf((int)(((productsResponse.products.get(id-1).rating)/5)*100))+" reviews");
                  description.setText(productsResponse.products.get(id-1).description);
                  dis.setText(String.valueOf("-"+productsResponse.products.get(id-1).discountPercentage)+"%");
                  bookmark.setOnClickListener(view -> {
                      Toast.makeText(SecondActivity.this, "Bookmarked", Toast.LENGTH_SHORT).show();
                  });
                  add.setOnClickListener(view -> {
                      Toast.makeText(SecondActivity.this, "Item added to cart", Toast.LENGTH_SHORT).show();
                  });
                  plus.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          if (counter[0] < productsResponse.products.get(id-1).stock) {
                              ++counter[0];
                              quantity.setText(counter[0] + "");
                          }
                      }
                  });
                  minus.setOnClickListener(view -> {
                      --counter[0];
                      if (counter[0] < 0){
                          counter[0] = 0;
                      }else {
                          quantity.setText(counter[0] + "");
                      }
                  });
            }
        });

        imageSlider = findViewById(R.id.imageSlider);

        imgSlide = new ArrayList<SlideModel>();


        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,url+id,null, response -> {

            try {
                for (int i=0; i <= response.getJSONArray("images").length();i++) {
                    imgURL = response.getJSONArray("images").getString(i);
                    imgSlide.add(new SlideModel(imgURL,ScaleTypes.CENTER_INSIDE));
                    imageSlider.setImageList(imgSlide,ScaleTypes.FIT);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        },error -> {
            System.out.println(error);
        });
        RequestQueue rQueue = Volley.newRequestQueue(this);
        rQueue.add(request);
    }
}
