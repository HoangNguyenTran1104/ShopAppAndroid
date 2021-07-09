package com.bignerdranch.android.shopapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.shopapp.database.Database;

import java.util.ArrayList;
import java.util.List;


public class ProductDetail extends AppCompatActivity {

    ImageView backProductDetail;
    String name,description,price;
    int imageUrl;
    TextView detailName,detailPrice,detailDescription;
    ImageView detailImage;
    ImageView cartShop;
    Button addCartBtn;
    Database database;
    List<String> productList;
    String nameSQL ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        backProductDetail = findViewById(R.id.back);
        backProductDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent back = new Intent(ProductDetail.this, WonmenProduct.class);
//                startActivity(back);
//                finish();
                onBackPressed();
            }
        });


         Intent i = getIntent();
         name = i.getStringExtra("name");
         description = i.getStringExtra("description");
         price = i.getStringExtra("price");
         imageUrl = i.getIntExtra("imageUrl",R.drawable.nu1);


        detailName= findViewById(R.id.detailName);
        detailPrice = findViewById(R.id.detailPrice);
        detailDescription = findViewById(R.id.detailDescription);
        detailImage = findViewById(R.id.detailImage);

        detailName.setText(name);
        detailPrice.setText(price);
        detailDescription.setText(description);
        detailImage.setImageResource(imageUrl);


        //cartShop
        cartShop = findViewById(R.id.cartShop);
        cartShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(ProductDetail.this, CartShop.class);
                startActivity(i);
            }
        });

        // add cart
        addCartBtn = findViewById(R.id.addCartBtn);
        addCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertProduct(name,description,price,"1",imageUrl);
            }
        });

    }

    private void InsertProduct(String detailName,String description,String detailPrice,String count, int imageUrl){

//        Toast.makeText(this,count,Toast.LENGTH_LONG).show();
        database = new Database(this,"ProductSQL.sqlite",null,1 );
        productList = new ArrayList<>();


        Cursor productDatabase = database.GetData("SELECT NAME FROM Product");
        while (productDatabase.moveToNext()){
            nameSQL= productDatabase.getString(0);
            productList.add(nameSQL);
        }

        if(productList.contains(detailName)){
            Toast.makeText(this,detailName+" existed in cart",Toast.LENGTH_LONG).show();
        }
        else{
            database.QueryData("INSERT INTO Product VALUES(NULL,'" + detailName + "','" + description + "','" + detailPrice + "','" + count + "','"+detailPrice+"','" + imageUrl + "') ");
            Toast.makeText(this, detailName + " added in cart", Toast.LENGTH_LONG).show();
        }

    }



}