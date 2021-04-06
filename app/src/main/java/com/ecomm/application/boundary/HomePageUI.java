package com.ecomm.application.boundary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.ecomm.application.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomePageUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get intent passed from loginUI
        if (getIntent().hasExtra("com.example.ACCOUNT")){
            String username = getIntent().getExtras().getString("com.example.ACCOUNT");
        }

        // click on cart button
        ImageButton cartBtn = (ImageButton) findViewById(R.id.cartBtn);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(getApplicationContext(), ShoppingCartUI.class);
                startActivity(cartIntent);
            }
        });

        //(test filter) click on filter button
        TextView filterSearchBtn = (TextView) findViewById(R.id.filterSearchBtn);
        filterSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filterIntent = new Intent(getApplicationContext(), FilterUI.class);
                startActivity(filterIntent);
            }
        });

        SearchView SearchBar = (SearchView) findViewById(R.id.searchBar);
        SearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.isEmpty()){
                    System.out.println("Please enter search item");
                }
                Intent searchIntent = new Intent(getApplicationContext(), SearchResultsUI.class);
//                searchIntent.putExtra("com.ecomm.application.QUERY", query);
                startActivity(searchIntent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        ImageView mangoImageView = (ImageView) findViewById(R.id.mangoImageView);
        mangoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> productDeets = new ArrayList<String>();
                productDeets.add(0,"mango");
                productDeets.add("S$4.59");
                productDeets.add("Mangoes imported from Malaysia. Ripe and sweet.");
                Intent displayProductDetail = new Intent(getApplicationContext(), ProductDisplayUI.class);
                displayProductDetail.putExtra("com.ecomm.application.PRODUCT_INFO", productDeets);
                startActivity(displayProductDetail);
            }
        });

    }
}