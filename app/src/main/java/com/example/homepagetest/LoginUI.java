package com.example.homepagetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginBtn = (Button) findViewById(R.id.loginBtn); //declare login btn
        loginBtn.setOnClickListener(new View.OnClickListener() { //upon clicking on login btn
            @Override
            public void onClick(View v) {
                Intent loginSuccessIntent = new Intent(getApplicationContext(), HomePageUI.class);
                loginSuccessIntent.putExtra("com.example.ACCOUNT", "xiaoming123");
                startActivity(loginSuccessIntent);
            }
        });

        TextView registerBtn = (TextView) findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(getApplicationContext(), RegisterUI.class);
                startActivity(registerIntent);
            }
        });

    }
}