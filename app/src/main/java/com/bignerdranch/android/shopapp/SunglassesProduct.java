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

public class SunglassesProduct extends AppCompatActivity {

    RecyclerView productRecycler;
    ProductAdapter productAdapter;
    List<Product> productList;
    ImageView backWomen,cartShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sunglasses_product);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        productRecycler = findViewById(R.id.productRecycler);

        backWomen = findViewById(R.id.back);
        backWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(SunglassesProduct.this, MainActivity.class);
                startActivity(back);
                finish();
            }
        });

        //cartShop
        cartShop = findViewById(R.id.cartShop);
        cartShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(SunglassesProduct.this, CartShop.class);
                startActivity(i);
            }
        });


        //adding data product
        productList = new ArrayList<>();
        productList.add(new Product("Sunglasses 1","These 80s inspired aviator frames are defined by a minimalistic shape in dark ruthenium metal frame. The metal temples are enriched with a Gucci and Web detail.\n_Dark ruthenium metal frame\n_Dark ruthenium metal temples with Gucci lettering and Web detail\n_Solid grey lens\n_100% UVA/UVB protection\n_Temple length: 145mm; Lens height: 50.4mm; Nose bridge length: 16mm\n_Frame height: 5.49cm; Frame width: 14.8cm\n_Made in Italy","$09.00","1",R.drawable.sunglasses1));
        productList.add(new Product("Sunglasses 2","These 80s inspired aviator frames are defined by a minimalistic shape in dark ruthenium metal frame. The metal temples are enriched with a Gucci and Web detail.\n_Dark ruthenium metal frame\n_Dark ruthenium metal temples with Gucci lettering and Web detail\n_Solid grey lens\n_100% UVA/UVB protection\n_Temple length: 145mm; Lens height: 50.4mm; Nose bridge length: 16mm\n_Frame height: 5.49cm; Frame width: 14.8cm\n_Made in Italy","$09.00","1",R.drawable.sunglasses2));
        productList.add(new Product("Sunglasses 3","These 80s inspired aviator frames are defined by a minimalistic shape in dark ruthenium metal frame. The metal temples are enriched with a Gucci and Web detail.\n_Dark ruthenium metal frame\n_Dark ruthenium metal temples with Gucci lettering and Web detail\n_Solid grey lens\n_100% UVA/UVB protection\n_Temple length: 145mm; Lens height: 50.4mm; Nose bridge length: 16mm\n_Frame height: 5.49cm; Frame width: 14.8cm\n_Made in Italy","$09.00","1",R.drawable.sunglasses3));
        productList.add(new Product("Sunglasses 4","These 80s inspired aviator frames are defined by a minimalistic shape in dark ruthenium metal frame. The metal temples are enriched with a Gucci and Web detail.\n_Dark ruthenium metal frame\n_Dark ruthenium metal temples with Gucci lettering and Web detail\n_Solid grey lens\n_100% UVA/UVB protection\n_Temple length: 145mm; Lens height: 50.4mm; Nose bridge length: 16mm\n_Frame height: 5.49cm; Frame width: 14.8cm\n_Made in Italy","$09.00","1",R.drawable.sunglasses7));
        productList.add(new Product("Sunglasses 5","These 80s inspired aviator frames are defined by a minimalistic shape in dark ruthenium metal frame. The metal temples are enriched with a Gucci and Web detail.\n_Dark ruthenium metal frame\n_Dark ruthenium metal temples with Gucci lettering and Web detail\n_Solid grey lens\n_100% UVA/UVB protection\n_Temple length: 145mm; Lens height: 50.4mm; Nose bridge length: 16mm\n_Frame height: 5.49cm; Frame width: 14.8cm\n_Made in Italy","$09.00","1",R.drawable.sunglasses5));
        productList.add(new Product("Sunglasses 6","These 80s inspired aviator frames are defined by a minimalistic shape in dark ruthenium metal frame. The metal temples are enriched with a Gucci and Web detail.\n_Dark ruthenium metal frame\n_Dark ruthenium metal temples with Gucci lettering and Web detail\n_Solid grey lens\n_100% UVA/UVB protection\n_Temple length: 145mm; Lens height: 50.4mm; Nose bridge length: 16mm\n_Frame height: 5.49cm; Frame width: 14.8cm\n_Made in Italy","$09.00","1",R.drawable.sunglasses6));


        setProductRecycler(productList);
    }
    private void setProductRecycler(List<Product> productDataList) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        productRecycler.setLayoutManager(layoutManager);
        productRecycler.addItemDecoration(new SunglassesProduct.GridSpacingItemDecoration(2, dpToPx(16), true));
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