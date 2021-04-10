package com.ecomm.application.boundary;

import android.content.Intent;
import android.os.Bundle;

import com.ecomm.application.R;
import com.ecomm.application.boundary.HomePageUI;
import com.ecomm.application.entity.Product;
import com.ecomm.application.webCrawl;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class SearchUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_u_i);
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

         */

        //click on back button
        Button home = (Button) findViewById(R.id.toHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHome = new Intent(getApplicationContext(), HomePageUI.class);
                startActivity(toHome);
            }
        });

        //initialise all textViews
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        TextView textView5 = (TextView) findViewById(R.id.textView5);


        SearchView searchView = (SearchView) findViewById(R.id.searchBar);

        ImageView i = (ImageView)findViewById(R.id.imageView6);
        Picasso.with(SearchUI.this).load("//laz-img-sg.alicdn.com/p/b3f0c6c404c45095ee74e8863b89b0db.jpg_720x720q80.jpg_.webp").into(i);

        //textView2.setText(searchView.getQuery());
        /*
        ArrayList<Product> productList = new ArrayList<Product>();


        try {
            productList = crawler.testLazadaSearch((String) searchView.getQuery());
            textView2.setText(productList.get(1).getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        ArrayList<Product> productList = new ArrayList<Product>();

        try {
            productList = this.lazadaresults((String) searchView.getQuery());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        textView2.setText(productList.get(1).getName());


        //@Test
        //public void lazadaresults() throws InterruptedException, MalformedURLException, URISyntaxException {
        webCrawl crawler = new webCrawl();
        public ArrayList<Product> lazadaresults(String q) throws InterruptedException, MalformedURLException, URISyntaxException {
            //HomePageUI search = new HomePageUI();
            //SearchView SearchBar = (SearchView) findViewById(R.id.searchBar);
            //String q = (String) SearchBar.getQuery();
            //String q = "orange";

            ArrayList<Product> productList = new ArrayList<Product>();
            productList = crawler.testLazadaSearch(q);

            System.out.println(productList.get(1).getPrice());
            System.out.println(productList.get(1).getName());
            System.out.println(productList.get(1).getUrl());
            System.out.println(productList.get(1).getEcommerceSite());

            return productList;
        }

         */





    }

}