package com.bignerdranch.android.shopapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bignerdranch.android.shopapp.database.Database;

import java.util.ArrayList;
import java.util.List;

public class SignUp extends AppCompatActivity {

    Database database;
    List<String> userNameList;
    EditText inputUserName,inputEmail,inputPassword;
    Button btnSignUp;
    String inputUserNameSQL,inputEmailSQL,inputPasswordSQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        inputUserName = findViewById(R.id.userNameLogin);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.passwordLogin);
        btnSignUp = findViewById(R.id.btnSignIn);


        //create database
        //create database ProductSQL.sqlite
        database = new Database(this,"ProductSQL.sqlite",null,1 );

        database.QueryData("CREATE TABLE IF NOT EXISTS Account(userID INTEGER PRIMARY KEY AUTOINCREMENT, userName VARCHAR(200), email VARCHAR(200), password VARCHAR(200))");
        //insert data
       // database.QueryData("INSERT INTO Account VALUES(NULL,'hoangnguyen','hoangnt18@uef.edu.vn','123123')");
        //database.QueryData("DELETE FROM Account");



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputUserNameSQL = inputUserName.getText().toString().trim();
                inputEmailSQL = inputEmail.getText().toString().trim();
                inputPasswordSQL = inputPassword.getText().toString().trim();
                int result =  SignUpAccount(inputUserNameSQL,inputEmailSQL,inputPasswordSQL);
                if(result == 1){
                    Intent i =new Intent(SignUp.this, Login.class);
                    startActivity(i);

                }
                else{
                    return;
                }
            }
        });

    }

    //SignUp
    public int SignUpAccount(String userName,String email,String password){

        userNameList = new ArrayList<>();

        Cursor productDatabase = database.GetData("SELECT userName FROM Account");
        while (productDatabase.moveToNext()){
            userName = productDatabase.getString(0);
            userNameList.add(userName);
        }

        //Toast.makeText(this,inputUserName.getText().toString()+" existed",Toast.LENGTH_LONG).show();

        if(userNameList.contains(inputUserName.getText().toString())){
            Toast.makeText(this,userName+" existed",Toast.LENGTH_LONG).show();
            return 0;
        }
        else{
            database.QueryData("INSERT INTO Account VALUES(NULL,'"+inputUserName.getText().toString()+"','"+inputEmail.getText().toString().trim()+"','"+inputPassword.getText().toString().trim()+"')");
            Toast.makeText(SignUp.this, userName+" Sign up successful", Toast.LENGTH_LONG).show();
            return 1;
        }


//        return  0;

    }


}