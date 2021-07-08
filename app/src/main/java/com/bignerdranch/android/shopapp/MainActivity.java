package com.bignerdranch.android.shopapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bignerdranch.android.shopapp.adapter.DiscountAdapter;
import com.bignerdranch.android.shopapp.model.Discount;


import org.imaginativeworld.whynotimagecarousel.CarouselItem;
import org.imaginativeworld.whynotimagecarousel.CarouselOnScrollListener;
import org.imaginativeworld.whynotimagecarousel.CarouselType;
import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.OnItemClickListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator2;

public class MainActivity extends AppCompatActivity {

    RecyclerView discountRecyclerView;
    DiscountAdapter discountAdapter;
    List<Discount> discountList;
    ImageView womentProduct,menProduct,kidProduct,sunglassesProduct,cartShopMain,account;


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

        //account
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this, Login.class);
                startActivity(i);
            }
        });






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

}