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

public class WonmenProduct extends AppCompatActivity {

    RecyclerView productRecycler;
    ProductAdapter productAdapter;
    List<Product> productList;
    ImageView backWomen,cartShop;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wonmen_product);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        productRecycler = findViewById(R.id.productRecycler);

        backWomen = findViewById(R.id.back);
        backWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(WonmenProduct.this, MainActivity.class);
                startActivity(back);
                finish();
            }
        });

        //cartShop
        cartShop = findViewById(R.id.cartShop);
        cartShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(WonmenProduct.this, CartShop.class);
                startActivity(i);
            }
        });


        //adding data product
        productList = new ArrayList<>();
        productList.add(new Product("T-Shirt Spanish","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$09.00","1",R.drawable.nu1));
        productList.add(new Product("Blouse","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$09.00","1",R.drawable.nu1));
        productList.add(new Product("Shirt","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$09.00","1",R.drawable.nu1));
        productList.add(new Product("Light blouse","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$09.00","1",R.drawable.nu1));
        productList.add(new Product("Shirt","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$09.00","1",R.drawable.nu1));
        productList.add(new Product("Light blouse","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$09.00","1",R.drawable.nu1));


        setProductRecycler(productList);

    }

    private void setProductRecycler(List<Product> productDataList) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        productRecycler.setLayoutManager(layoutManager);
        productRecycler.addItemDecoration(new WonmenProduct.GridSpacingItemDecoration(2, dpToPx(16), true));
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