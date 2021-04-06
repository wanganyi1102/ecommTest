package com.ecomm.application.boundary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.ecomm.application.R;

public class ShoppingCartUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart_u_i);

        //Set back button to activity
        ImageButton backBtn = (ImageButton) findViewById(R.id.backToHome);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backHomeIntent = new Intent(getApplicationContext(), HomePageUI.class);
                startActivity(backHomeIntent);
            }
        });
    }
}