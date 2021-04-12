package com.ecomm.application.boundary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.ecomm.application.R;
import com.ecomm.application.entity.Product;
import com.ecomm.application.webCrawl;


import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class HomePageUI extends AppCompatActivity {

    public static String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s = "pudding";

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

        ImageButton profileBtn = (ImageButton) findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(getApplicationContext(), UserProfileUI.class);
                startActivity(profileIntent);
            }
        });

        //(test filter) click on filter button
        TextView filterSearchBtn = (TextView) findViewById(R.id.filterSearchBtn);
        filterSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent testListView = new Intent(getApplicationContext(), SearchTestUI.class);
                startActivity(testListView);
//                Intent filterIntent = new Intent(getApplicationContext(), FilterUI.class);
//                startActivity(filterIntent);
            }
        });



        SearchView SearchBar = (SearchView) findViewById(R.id.searchBar);
        SearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.isEmpty()){
                    System.out.println("Please enter search item");
                }


                Intent searchIntent = new Intent(getApplicationContext(), SearchUI.class);
//                searchIntent.putExtra("com.ecomm.application.QUERY", query);
                startActivity(searchIntent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }


        });

        //set textVIew in search as query
        TextView textView2 = (TextView) findViewById(R.id.textView2);


            // fake product to test other UIs
        ImageView mangoImageView = (ImageView) findViewById(R.id.mangoImageView);
        mangoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                URI uri = null;
                try {
                    uri = new URI("https://www.lazada.sg/products/cambodia-keo-romeat-mango-i395210387-s952532064.html?spm=a2o42.searchlist.list.3.e8f72f9eWNyvMi&search=1");
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                URL mangoURL = null;
                try {
                    mangoURL = uri.toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                Product mango = new Product("mango", 2.50, mangoURL,
                        "Keo Romeat mango from Cambodia which is also known as the Goldstar mango locally, have a golden yellow color peel when ripen. It as a balanced sweet flavor with a bright orange flesh that consist of minimum fiber allowing it to have a smooth texture.",
                        "Lazada", 4.4, 3.9, "https://sg-test-11.slatic.net/p/b8375ca4a12d1937e2954a31955881ba.jpg_100x100q90.jpg_.webp",1);

                Intent displayProductDetail = new Intent(getApplicationContext(), ProductDisplayUI.class);
                displayProductDetail.putExtra("com.ecomm.application.PRODUCT_INFO", mango);
                startActivity(displayProductDetail);
            }
        });


            //another fake product
            ImageView phone = (ImageView) findViewById(R.id.phone);
            phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    URI uri = null;
                    try {
                        uri = new URI("https://www.lazada.sg/products/cambodia-keo-romeat-mango-i395210387-s952532064.html?spm=a2o42.searchlist.list.3.e8f72f9eWNyvMi&search=1");
                        //https://www.lazada.sg/products/cambodia-keo-romeat-mango-i395210387-s952532064.html?spm=a2o42.searchlist.list.3.e8f72f9eWNyvMi&search=1
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    URL mangoURL = null;
                    try {
                        mangoURL = uri.toURL();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                    Product phone = new Product("phone", 8.90, mangoURL,
                            "Iphone 12 from Singtel. No charger included, no ipods included.",
                            "Qoo10", 3.9, 10.5, "https://sg-test-11.slatic.net/p/e9592d365dbdaaaa27aa39edba680960.jpg_100x100q90.jpg_.webp",1);

                    Intent displayProductDetail = new Intent(getApplicationContext(), ProductDisplayUI.class);
                    displayProductDetail.putExtra("com.ecomm.application.PRODUCT_INFO", phone);
                    startActivity(displayProductDetail);
                }
            });

        // test to check if button can open search xml
        Button test = (Button) findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSearchIntent = new Intent(getApplicationContext(), SearchUI.class);
                startActivity(openSearchIntent);
            }
        });

    }
}