package com.bignerdranch.android.shopapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.shopapp.R;
import com.bignerdranch.android.shopapp.model.Discount;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.DiscountViewHolder> {

    public DiscountAdapter(Context context, List<Discount> discountList) {
        this.context = context;
        this.discountList = discountList;
    }

    Context context;
    List<Discount> discountList;

    @NonNull
    @NotNull
    @Override
    public DiscountViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.discount_product_recycler,parent,false);
        return new DiscountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DiscountAdapter.DiscountViewHolder holder, int position) {
        holder.discountImageView.setImageResource(discountList.get(position).getImageurl());
    }

    @Override
    public int getItemCount() {
        return discountList.size();
    }

    public static class DiscountViewHolder extends RecyclerView.ViewHolder{

        ImageView discountImageView;

        public DiscountViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            discountImageView = itemView.findViewById(R.id.discountImageView);
        }
    }

}
