package com.bignerdranch.android.shopapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bignerdranch.android.shopapp.adapter.ProductAdapter;
import com.bignerdranch.android.shopapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class KidProduct extends AppCompatActivity {

    RecyclerView productRecycler;
    ProductAdapter productAdapter;
    List<Product> productList;
    ImageView backWomen,cartShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_product);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        productRecycler = findViewById(R.id.productRecycler);

        backWomen = findViewById(R.id.back);
        backWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(KidProduct.this, MainActivity.class);
                startActivity(back);
                finish();
            }
        });

        //cartShop
        cartShop = findViewById(R.id.cartShop);
        cartShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(KidProduct.this, CartShop.class);
                startActivity(i);
            }
        });


        //adding data product
        productList = new ArrayList<>();
        productList.add(new Product("Children 1","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$09.00","1",R.drawable.children1));
        productList.add(new Product("Children 2","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$10.00","1",R.drawable.children2));
        productList.add(new Product("Children 3","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$11.00","1",R.drawable.children3));
        productList.add(new Product("Children 5","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$12.00","1",R.drawable.children5));
        productList.add(new Product("Children 6","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$13.00","1",R.drawable.children6));
        productList.add(new Product("Children 7","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$14.00","1",R.drawable.children7));
        productList.add(new Product("Children 8","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$15.00","1",R.drawable.children8));
        productList.add(new Product("Children 9","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$16.00","1",R.drawable.children9));
        productList.add(new Product("Children 10","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$17.00","1",R.drawable.children10));
        productList.add(new Product("Children 12","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$18.00","1",R.drawable.children12));
        productList.add(new Product("Children 13","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$19.00","1",R.drawable.children13));
        productList.add(new Product("Children 14","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$20.00","1",R.drawable.children14));


        setProductRecycler(productList);
    }
    private void setProductRecycler(List<Product> productDataList) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        productRecycler.setLayoutManager(layoutManager);
        productRecycler.addItemDecoration(new KidProduct.GridSpacingItemDecoration(2, dpToPx(16), true));
        productRecycler.setItemAnimator(new DefaultItemAnimator());
        productAdapter= new ProductAdapter(this,productDataList);
        productRecycler.setAdapter(productAdapter);
    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}