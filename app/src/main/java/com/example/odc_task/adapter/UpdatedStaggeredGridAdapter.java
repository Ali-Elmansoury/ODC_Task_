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

public class UpdatedStaggeredGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<Products> data;
    public int limit;
    ClickListener listener;
    public static final int TYPE_HEADER=0,TYPE_ITEM=1;

    public UpdatedStaggeredGridAdapter(List<Products> data , int limit , ClickListener listener) {
        this.data=data;
        this.listener=listener;
        this.limit=limit;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==TYPE_ITEM)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem = layoutInflater.inflate(R.layout.staggered_item,parent,false);
            UpdatedStaggeredGridAdapter.ItemViewHolder viewHolder = new UpdatedStaggeredGridAdapter.ItemViewHolder(listItem);
            return viewHolder;
        }
        else if (viewType==TYPE_HEADER)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem = layoutInflater.inflate(R.layout.header,parent,false);
            UpdatedStaggeredGridAdapter.HeaderViewHolder viewHolder = new UpdatedStaggeredGridAdapter.HeaderViewHolder(listItem);
            return viewHolder;
        }
        else return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder)
        {
            ((HeaderViewHolder) holder).items.setText(String.valueOf(limit));
        }
        else if (holder instanceof ItemViewHolder)
        {
            int index = holder.getAdapterPosition();
            ((ItemViewHolder) holder).name.setText(data.get(position).title);
            ((ItemViewHolder) holder).brand.setText(data.get(position).brand);
            ((ItemViewHolder) holder).category.setText(data.get(position).category);
            ((ItemViewHolder) holder).price.setText(String.valueOf(data.get(position).price)+"$");
            Glide.with(((ItemViewHolder) holder).thumbnail).load(data.get(position).thumbnail).into(((ItemViewHolder) holder).thumbnail);
            ((ItemViewHolder) holder).view.setOnClickListener(view ->
                    listener.click(index));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView name,brand,category,price;
        public ImageView thumbnail;
        public View view;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            brand = itemView.findViewById(R.id.brand);
            category = itemView.findViewById(R.id.category);
            price = itemView.findViewById(R.id.price);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            view = itemView;
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public TextView items;
        public View view;

        public HeaderViewHolder(@NonNull View headerView) {
            super(headerView);
            items = headerView.findViewById(R.id.items);
            view = headerView;
        }
    }
}
