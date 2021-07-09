package com.bignerdranch.android.shopapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.shopapp.database.Database;

import java.util.ArrayList;
import java.util.List;

public class LoginSuccessful extends AppCompatActivity {

    Database database;
    ImageView back;
    TextView userName,checkoutAccount;
    List<String> userNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_successful);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        checkoutAccount = findViewById(R.id.checkoutAccount);
        checkoutAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checkout();
            }
        });

        userName = findViewById(R.id.userName);
        String userNameSQL = GetUserName();
        if(userNameSQL == null){
            userName.setText("");
        }
        else{
            userName.setText(userNameSQL);
        }
    }


    public void Checkout(){
        AlertDialog.Builder dialogRemoveCart = new AlertDialog.Builder(LoginSuccessful.this);
        dialogRemoveCart.setMessage("Do you want to checkout?");
        dialogRemoveCart.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                database = new Database(LoginSuccessful.this,"ProductSQL.sqlite",null,1 );
                database.QueryData("DELETE FROM Login");
                Toast.makeText(LoginSuccessful.this,"You paid successfully ",Toast.LENGTH_SHORT ).show();
                Intent i =new Intent(LoginSuccessful.this, MainActivity.class);
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

    public String GetUserName(){
        database = new Database(LoginSuccessful.this,"ProductSQL.sqlite",null,1 );
        String userName;
        userNameList = new ArrayList<>();

        Cursor productDatabase = database.GetData("SELECT userName FROM Login");
        while (productDatabase.moveToNext()){
            userName = productDatabase.getString(0);
            userNameList.add(userName);
        }
        return userNameList.get(0);
    }
}