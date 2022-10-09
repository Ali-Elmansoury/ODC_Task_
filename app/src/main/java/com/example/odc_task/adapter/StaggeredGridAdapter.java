package com.example.odc_task.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.odc_task.ClickListener;
import com.example.odc_task.R;
import com.example.odc_task.models.Products;

import java.util.List;

public class StaggeredGridAdapter extends RecyclerView.Adapter<StaggeredGridAdapter.ViewHolder> {
    public List<Products> data;
    ClickListener listener;

    public StaggeredGridAdapter(List<Products> data , ClickListener listener) {
        this.data=data;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.staggered_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int index = holder.getAdapterPosition();
        holder.name.setText(data.get(position).title);
        holder.brand.setText(data.get(position).brand);
        holder.category.setText(data.get(position).category);
        holder.price.setText(String.valueOf(data.get(position).price)+"$");
        Glide.with(holder.thumbnail).load(data.get(position).thumbnail).into(holder.thumbnail);
        holder.view.setOnClickListener(view ->
                listener.click(index));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name,brand,category,price;
        public ImageView thumbnail;
        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            brand = itemView.findViewById(R.id.brand);
            category = itemView.findViewById(R.id.category);
            price = itemView.findViewById(R.id.price);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            view = itemView;
        }
    }
}
