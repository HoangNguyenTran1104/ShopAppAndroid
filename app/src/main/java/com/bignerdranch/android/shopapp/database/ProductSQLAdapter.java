package com.bignerdranch.android.shopapp.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.shopapp.CartShop;
import com.bignerdranch.android.shopapp.R;
import com.bignerdranch.android.shopapp.model.Product;

import java.util.List;

public class ProductSQLAdapter extends BaseAdapter {

    private CartShop context;
    private int layout;
    private List<Product> productList;

    public ProductSQLAdapter(CartShop context, int layout, List<Product> productList) {
        this.context = context;
        this.layout = layout;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView cartName,cartPrice,cartCount,priceCount;
        ImageView cartImage,cartDelete,cartUp, cartDown;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(layout,null);
            holder.cartName = convertView.findViewById(R.id.cartName);
            holder.cartPrice = convertView.findViewById(R.id.cartPrice);
            holder.cartCount = convertView.findViewById(R.id.cartCount);
            holder.priceCount = convertView.findViewById(R.id.priceCount);
            holder.cartImage = convertView.findViewById(R.id.cartImage);
            holder.cartUp = convertView.findViewById(R.id.cartUp);
            holder.cartDown = convertView.findViewById(R.id.cartDown);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        Product product = productList.get(position);
        holder.cartName.setText(product.getName());
        holder.cartPrice.setText(product.getPrice());
        holder.cartCount.setText(product.getCount());
        holder.priceCount.setText(product.getPriceCount());
        holder.cartImage.setImageResource(product.getImageUrl());
//        holder.cartName.setText(product.getName());


        // remove cart
        holder.cartDelete = convertView.findViewById(R.id.cartDelete);
        holder.cartDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogRemoveCart(product.getName(),product.getId());
            }
        });

        // cart up
        holder.cartUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.CartUp(Integer.parseInt(product.getCount()), product.getPrice(),product.getId());
            }
        });

        // cart down
        holder.cartDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.CartDown(Integer.parseInt(product.getCount()),product.getPrice(),product.getId());
            }
        });

        return convertView;
    }




}
