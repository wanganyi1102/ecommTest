package com.ecomm.application.boundary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ecomm.application.R;
import com.example.homepagetest.ShoppingCart;
import com.google.android.gms.actions.ItemListIntents;

public class ShoppingCartUI extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart_u_i);

//        //Set back button to activity
//        ImageButton backBtn = (ImageButton) findViewById(R.id.backToHome);
//        backBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent backHomeIntent = new Intent(getApplicationContext(), HomePageUI.class);
//                startActivity(backHomeIntent);
//            }
//        });

        //Set button to payment
        Button paymentBtn = (Button) findViewById(R.id.btn_check_out);
        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paymentIntent = new Intent(getApplicationContext(), PaymentUI.class);
                startActivity(paymentIntent);
            }
        });
    }
}