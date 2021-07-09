package com.bignerdranch.android.shopapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.shopapp.database.Database;
import com.bignerdranch.android.shopapp.model.Product;
import com.bignerdranch.android.shopapp.database.ProductSQLAdapter;

import java.util.ArrayList;
import java.util.List;

public class CartShop extends AppCompatActivity {

    Database database;
    ListView productListView;
    ProductSQLAdapter productSQLAdapter;
    ArrayList<Product> productList;


    String nameSQL,descriptionSQL,countSQL,priceSQL,priceCountSQL;
    int imageSQL;
    int idSQL;

    ImageView  back;
    TextView cartPrice,totalPrice,delivery,total;
    Button btnCheckOut;
    List<String> userNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_shop);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent back = new Intent(ProductDetail.this, WonmenProduct.class);
//                startActivity(back);
//                finish();
                int result = CheckLogin();
                if(result == 1){
                    Intent back = new Intent(CartShop.this, MainActivity.class);
                    startActivity(back);
                }
                else{
                    onBackPressed();
                }



            }
        });

        //cart shop
        productListView = findViewById(R.id.productListView);
        productList = new ArrayList<>();

        productSQLAdapter = new ProductSQLAdapter(this,R.layout.shop_cart_items,productList);
        productListView.setAdapter(productSQLAdapter);


        //create database
        //create database ProductSQL.sqlite
        database = new Database(this,"ProductSQL.sqlite",null,1 );


        //create table
        int phoTo = R.drawable.nu1;

        database.QueryData("CREATE TABLE IF NOT EXISTS Product(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(200), description VARCHAR(700), price VARCHAR(200), count VARCHAR(10),priceCount VARCHAR(50),imageUrl INTEGER)");
        //insert data
//        database.QueryData("INSERT INTO Product VALUES(NULL,'Shirt','Bright colors. Bold phrases. Make a statement and own it. Start with this classic tee and put your own touch on our iconic logo.\n _An essential T-shirt.\n_Crafted from soft jersey.\n_A blank canvas for creativity','$09.00','1','$09.00','"+phoTo+"') ");

        GetListProduct();
        TotalPrice ();
        Checkout();




    }

    private void GetListProduct(){
        //select data
        Cursor productDatabase = database.GetData("SELECT * FROM Product");
        productList.clear();
        while (productDatabase.moveToNext()){
            nameSQL = productDatabase.getString(1);
            descriptionSQL = productDatabase.getString(2);
            priceSQL = productDatabase.getString(3);
            countSQL = productDatabase.getString(4);
            priceCountSQL = productDatabase.getString(5);
            imageSQL = productDatabase.getInt(6);
            idSQL = productDatabase.getInt(0);

//            Toast.makeText(this,nameSQL,Toast.LENGTH_SHORT).show();
            productList.add(new Product(idSQL,nameSQL,descriptionSQL,priceSQL,countSQL,priceCountSQL,imageSQL));
        }


        productSQLAdapter.notifyDataSetChanged();
    }

    //remove cart
    public void DialogRemoveCart(String nameCart,int Id){
        AlertDialog.Builder dialogRemoveCart = new AlertDialog.Builder(this);
        dialogRemoveCart.setMessage("Do you want to remove "+nameCart+" from cart?");
        dialogRemoveCart.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                database.QueryData("DELETE FROM Product WHERE Id='"+Id+"'");
                Toast.makeText(CartShop.this,"removed " + nameCart,Toast.LENGTH_SHORT ).show();
                GetListProduct();
                TotalPrice ();
            }
        });
        dialogRemoveCart.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogRemoveCart.show();

    }

    // cart up
    public void CartUp(int count,String price,int id){
                count ++;
                price = price.replace("$","");
                float priceCount = Float.parseFloat(price);
                priceCount = priceCount * count;
                price = String.valueOf(priceCount);
                price = "$".concat(price);
                database.QueryData("UPDATE Product SET count = '"+String.valueOf(count)+"',priceCount='"+price+"' WHERE Id = '"+id+"'");
                Toast.makeText(this,price,Toast.LENGTH_SHORT).show();
                GetListProduct();
                TotalPrice ();
    }

    // cart down
    public void CartDown(int count,String price,int id){
        if(count >1){
            count --;
            price = price.replace("$","");
            float priceCount = Float.parseFloat(price);
            priceCount = priceCount * count;
            price = String.valueOf(priceCount);
            price = "$".concat(price);
            database.QueryData("UPDATE Product SET count = '"+String.valueOf(count)+"',priceCount='"+price+"' WHERE Id = '"+id+"'");
            Toast.makeText(this,price,Toast.LENGTH_SHORT).show();
            GetListProduct();
        }
        else{
            Toast.makeText(this, "count must more than 1",Toast.LENGTH_SHORT).show();
        }
        TotalPrice ();

    }

    public void TotalPrice (){
        totalPrice = findViewById(R.id.totalPrice);
        delivery = findViewById(R.id.delivery);
        total = findViewById(R.id.total);
        String priceCount;
        float totalPriceFloat = 0;
        float deliveryFloat,totalFloat;

        database = new Database(this,"ProductSQL.sqlite",null,1 );
        List<String> priceCountList = new ArrayList<>();
        Cursor productDatabase = database.GetData("SELECT priceCount FROM Product");
        while (productDatabase.moveToNext()){
            priceCount= productDatabase.getString(0);
            priceCount = priceCount.replace("$","");
            priceCountList.add(priceCount);
            totalPriceFloat = totalPriceFloat + Float.parseFloat(priceCount);
            totalPrice.setText("$".concat(String.valueOf(totalPriceFloat)));
        }
        if(priceCountList.size() == 0){
            total.setText("$00.00");
            delivery.setText("$00.00");
            totalPrice.setText("$00.00");
        }
        else{
            deliveryFloat = Float.parseFloat(delivery.getText().toString().replace("$",""));
            totalPriceFloat = Float.parseFloat(totalPrice.getText().toString().replace("$",""));
            totalFloat = deliveryFloat + totalPriceFloat;
            total.setText("$".concat(String.valueOf(totalFloat)));
        }

    }


    public void Checkout(){
        btnCheckOut = findViewById(R.id.btnCheckOut);
        int result = CheckLogin();

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result == 0){
                    AlertDialog.Builder dialogRemoveCart = new AlertDialog.Builder(CartShop.this);
                    dialogRemoveCart.setMessage("Please login to checkout");
                    dialogRemoveCart.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            Intent i =new Intent(CartShop.this, Login.class);
                            startActivity(i);
                        }
                    });
                    dialogRemoveCart.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialogRemoveCart.show();
                }
                else{
                    AlertDialog.Builder dialogRemoveCart = new AlertDialog.Builder(CartShop.this);
                    dialogRemoveCart.setMessage("Do you want to checkout?");
                    dialogRemoveCart.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            database.QueryData("DELETE FROM Product");
                            Toast.makeText(CartShop.this,"You paid successfully ",Toast.LENGTH_SHORT ).show();
                            GetListProduct();
                            TotalPrice ();
                            Intent i =new Intent(CartShop.this, CheckoutSuccessful.class);
                            startActivity(i);
                        }
                    });
                    dialogRemoveCart.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialogRemoveCart.show();
                }


            }
        });
    }


    public int CheckLogin(){
        String userName;
        userNameList = new ArrayList<>();

        Cursor productDatabase = database.GetData("SELECT userName FROM Login");
        while (productDatabase.moveToNext()){
            userName = productDatabase.getString(0);
            userNameList.add(userName);
        }
        Toast.makeText(CartShop.this,String.valueOf(userNameList.size()),Toast.LENGTH_SHORT ).show();
        if(userNameList.size() > 0){
            return 1;
        }
        else{
            return 0;
        }
    }


}












