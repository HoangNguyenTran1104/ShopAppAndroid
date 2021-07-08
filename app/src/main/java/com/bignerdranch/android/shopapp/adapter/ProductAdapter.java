package com.bignerdranch.android.shopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.shopapp.ProductDetail;
import com.bignerdranch.android.shopapp.R;
import com.bignerdranch.android.shopapp.model.Product;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context context;
    List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @NotNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_items,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductAdapter.ProductViewHolder holder, int position) {
        holder.name.setText(productList.get(position).getName());
        holder.price.setText(productList.get(position).getPrice());
        holder.imageUrl.setImageResource(productList.get(position).getImageUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ProductDetail.class);
                i.putExtra("name",productList.get(position).getName());
                i.putExtra("description",productList.get(position).getDescription());
                i.putExtra("price",productList.get(position).getPrice());
                i.putExtra("count",productList.get(position).getCount());
                i.putExtra("imageUrl",productList.get(position).getImageUrl());
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView name, price;
        ImageView imageUrl;

        public ProductViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.productPrice);
            imageUrl = itemView.findViewById(R.id.productImage);
        }
    }




}
