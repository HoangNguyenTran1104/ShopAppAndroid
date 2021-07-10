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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.shopapp.database.Database;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    Database database;

    TextView gotoRegister;
    EditText userNameLogin,passwordLogin;
    Button btnSignIn;
    List<String> userNameList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        gotoRegister = findViewById(R.id.gotoRegister);

        userNameLogin = findViewById(R.id.userNameLogin);
        passwordLogin = findViewById(R.id.passwordLogin);
        btnSignIn = findViewById(R.id.btnSignIn);


        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Login.this, SignUp.class);
                startActivity(i);
            }
        });


        //create database
        //create database ProductSQL.sqlite
        database = new Database(this,"ProductSQL.sqlite",null,1 );
        database.QueryData("CREATE TABLE IF NOT EXISTS Login(userID INTEGER PRIMARY KEY AUTOINCREMENT, userName VARCHAR(200))");
        //insert data
        // database.QueryData("INSERT INTO Account VALUES(NULL,'hoangnguyen','hoangnt18@uef.edu.vn','123123')");
        //  database.QueryData("DELETE FROM Account");


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameLoginSQL = userNameLogin.getText().toString().trim();
                String passwordLoginSQL = passwordLogin.getText().toString().trim();
                int result = Login(userNameLoginSQL,passwordLoginSQL);
                if(result == 1){
//                    Intent i =new Intent(Login.this, CartShop.class);
//                    startActivity(i);
                    onBackPressed();
                    Toast.makeText(Login.this,"Login successful",Toast.LENGTH_SHORT).show();
                }
                else{
                    //Toast.makeText(Login.this,"Login failed",Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder dialogRemoveCart = new AlertDialog.Builder(Login.this);
                    dialogRemoveCart.setTitle("Login failed");
                    dialogRemoveCart.setMessage("Please input username and password again.");

                    dialogRemoveCart.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialogRemoveCart.show();



                }
            }
        });

    }

    public int Login(String userNameSQL, String passwordSQL){
        String userName;
        userNameList = new ArrayList<>();

        Cursor productDatabase = database.GetData("SELECT userName FROM Account WHERE userName='"+userNameSQL+"' and password ='"+passwordSQL+"' ");
        while (productDatabase.moveToNext()){
            userName = productDatabase.getString(0);
            userNameList.add(userName);
        }

        //Toast.makeText(Login.this,passwordSQL,Toast.LENGTH_SHORT).show();
        if(userNameList.size() > 0){
            database.QueryData("INSERT INTO Login VALUES(NULL,'"+userNameSQL+"')");
            return 1;
        }
        else{
            return 0;
        }
    }
}


