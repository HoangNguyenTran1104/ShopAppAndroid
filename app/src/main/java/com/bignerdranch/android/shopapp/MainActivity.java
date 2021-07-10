package com.bignerdranch.android.shopapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bignerdranch.android.shopapp.adapter.ProductAdapter;
import com.bignerdranch.android.shopapp.database.Database;
import com.bignerdranch.android.shopapp.model.Product;


import org.imaginativeworld.whynotimagecarousel.CarouselItem;
import org.imaginativeworld.whynotimagecarousel.CarouselOnScrollListener;
import org.imaginativeworld.whynotimagecarousel.CarouselType;
import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.OnItemClickListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView productRecycler;
    ProductAdapter productAdapter;
    List<Product> productList;
    ImageView womentProduct,menProduct,kidProduct,sunglassesProduct,cartShopMain,account,map;

    Database database;
    List<String> userNameList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        womentProduct = findViewById(R.id.womentProduct);
        menProduct = findViewById(R.id.menProduct);
        kidProduct = findViewById(R.id.kidProduct);
        sunglassesProduct = findViewById(R.id.sunglassesProduct);
        cartShopMain= findViewById(R.id.cartShopMain);
        account = findViewById(R.id.account);
        map = findViewById(R.id.map);
        productRecycler = findViewById(R.id.discountRecycler);

        // click womentProduct
        womentProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this, WonmenProduct.class);
                startActivity(i);
            }
        });

        // click menProduct
        menProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this, MenProduct.class);
                startActivity(i);
            }
        });

        // click kidProduct
        kidProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this, KidProduct.class);
                startActivity(i);
            }
        });

        // click kidProduct
        sunglassesProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this, SunglassesProduct.class);
                startActivity(i);
            }
        });

        //cartShop
        cartShopMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this, CartShop.class);
                startActivity(i);
            }
        });


        //map
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this, GoogleMap.class);
                startActivity(i);
            }
        });



        //account
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                int result = CheckLoginAccount();
                if(result == 1){
                     i =new Intent(MainActivity.this, LoginSuccessful.class);
                    startActivity(i);
                }
                else {
                    i =new Intent(MainActivity.this, Login.class);
                    startActivity(i);
                }



            }
        });


        //adding data product
        productList = new ArrayList<>();
        productList.add(new Product("Sunglasses 5","These '80s inspired aviator frames are defined by a minimalistic shape in dark ruthenium metal frame. The metal temples are enriched with a Gucci and Web detail.\n_Dark ruthenium metal frame\n_Dark ruthenium metal temples with Gucci lettering and Web detail\n_Solid grey lens\n_100% UVA/UVB protection\n_Temple length: 145mm; Lens height: 50.4mm; Nose bridge length: 16mm\n_Frame height: 5.49cm; Frame width: 14.8cm\n_Made in Italy","$09.00","1",R.drawable.sunglasses5));
        productList.add(new Product("Blouse 4","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$16.00","1",R.drawable.nu8));
        productList.add(new Product("Sunglasses 6","These '80s inspired aviator frames are defined by a minimalistic shape in dark ruthenium metal frame. The metal temples are enriched with a Gucci and Web detail.\n_Dark ruthenium metal frame\n_Dark ruthenium metal temples with Gucci lettering and Web detail\n_Solid grey lens\n_100% UVA/UVB protection\n_Temple length: 145mm; Lens height: 50.4mm; Nose bridge length: 16mm\n_Frame height: 5.49cm; Frame width: 14.8cm\n_Made in Italy","$09.00","1",R.drawable.sunglasses6));
        productList.add(new Product("Men 1","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$09.00","1",R.drawable.nam1));
        productList.add(new Product("Shirt","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$09.00","1",R.drawable.nu1));
        productList.add(new Product("Light blouse 2","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$14.00","1",R.drawable.nu6));
        productList.add(new Product("Men 10","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$18.00","1",R.drawable.nam10));
        productList.add(new Product("Men 2","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$10.00","1",R.drawable.nam2));
        productList.add(new Product("Shirt","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$09.00","1",R.drawable.nu1));
        productList.add(new Product("Light blouse","Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity","$12.00","1",R.drawable.nu4));

        setProductRecycler(productList);








//CAROUSEL
        ImageCarousel carousel = findViewById(R.id.carousel);

        List<CarouselItem> list = new ArrayList<>();

        list.add(
                new CarouselItem(
                         "https://bizweb.dktcdn.net/thumb/grande/100/331/067/themes/823156/assets/slider_1.jpg?1624817220708"

                )
        );
        list.add(
                new CarouselItem(
                        "https://bizweb.dktcdn.net/thumb/grande/100/331/067/themes/823156/assets/slider_2.jpg?1624817220708"
                )
        );
        list.add(
                new CarouselItem(
                        "https://theme.hstatic.net/200000032664/1000687730/14/banner-slide4.jpg?v=402"
                )
        );


        // Attributes
        carousel.setShowTopShadow(true);
        carousel.setTopShadowAlpha(0.6f); // 0 to 1, 1 means 100%

        carousel.setShowBottomShadow(true);
        carousel.setBottomShadowAlpha(0.6f); // 0 to 1, 1 means 100%

        carousel.setShowCaption(true);
        carousel.setShowIndicator(true);
        carousel.setShowNavigationButtons(true);
        carousel.setImageScaleType(ImageView.ScaleType.CENTER);
        carousel.setCarouselBackground(new ColorDrawable(Color.parseColor("#FFFFFF")));
        carousel.setImagePlaceholder(ContextCompat.getDrawable(
                this,
                R.drawable.ic_picture
        ));

// See kotlin code for details.
        carousel.setItemLayout(R.layout.item_carousel);
        carousel.setImageViewId(R.id.img);

// See kotlin code for details.
        carousel.setPreviousButtonLayout(R.layout.previous_button_layout);
        carousel.setPreviousButtonId(R.id.btn_previous);
        carousel.setPreviousButtonMargin(0); // dp value

        carousel.setNextButtonLayout(R.layout.next_button_layout);
        carousel.setNextButtonId(R.id.btn_next);
        carousel.setNextButtonMargin(0); // dp value

        carousel.setCarouselType(CarouselType.BLOCK);
        carousel.setScaleOnScroll(false);
        carousel.setScalingFactor(.15f);

// See kotlin code for details.
        carousel.setAutoWidthFixing(true);

// See kotlin code for details.
        carousel.setAutoPlay(false);
        carousel.setAutoPlayDelay(6000); // Milliseconds

// Scroll listener
        carousel.setOnScrollListener(new CarouselOnScrollListener() {
            @Override
            public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
                // ...
            }

            @Override
            public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState, int position, @Nullable CarouselItem carouselItem) {
                // ...
            }
        });

// Item click listener
        carousel.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position, @NotNull CarouselItem carouselItem) {
                // ...
            }

            @Override
            public void onLongClick(int position, @NotNull CarouselItem dataObject) {
                // ...
            }
        });

// Goto next slide/item
        carousel.next();

// Goto previous slide/item
        carousel.previous();

// Start auto play
        carousel.start();

// Stop auto play
//        carousel.stop();

//// See kotlin code for details.
//        CircleIndicator2 indicator = findViewById(R.id.custom_indicator);
//        carousel.setIndicator(indicator);

// ...

        carousel.addData(list);


    }

    public int CheckLoginAccount(){
        userNameList = new ArrayList<>();
        String userName;
        database = new Database(this,"ProductSQL.sqlite",null,1 );
        database.QueryData("CREATE TABLE IF NOT EXISTS Login(userID INTEGER PRIMARY KEY AUTOINCREMENT, userName VARCHAR(200))");
        Cursor productDatabase = database.GetData("SELECT userName FROM Login");
        while (productDatabase.moveToNext()){
            userName = productDatabase.getString(0);
            userNameList.add(userName);
        }

        if(userNameList.size() > 0){
            return 1;
        }
        else{
            return 0;
        }
    }



    private void setProductRecycler(List<Product> productDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        productRecycler.setLayoutManager(layoutManager);
        productAdapter= new ProductAdapter(this,productDataList);
        productRecycler.setAdapter(productAdapter);
    }



}