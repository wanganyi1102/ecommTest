package com.ecomm.application.boundary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ecomm.application.R;

import java.util.ArrayList;

public class ProductDisplayUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display_u_i);

        if(getIntent().hasExtra("com.ecomm.application.PRODUCT_INFO")){
            ArrayList<String> productDeets = getIntent().getExtras().getStringArrayList("com.ecomm.application.PRODUCT_INFO");
            for(String s : productDeets){
                System.out.println(s);
            }
        }

    }
}