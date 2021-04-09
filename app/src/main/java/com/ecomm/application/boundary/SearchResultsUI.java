package com.ecomm.application.boundary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.ecomm.application.entity.Product;
import com.ecomm.application.webCrawl;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.ecomm.application.R;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchResultsUI extends AppCompatActivity {

    Button backHome;
    webCrawl crawler = new webCrawl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //Set back button to activity
        super.onCreate(savedInstanceState);
        backHome = findViewById(R.id.toHome);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backHomeIntent = new Intent(getApplicationContext(), HomePageUI.class);
                startActivity(backHomeIntent);
            }
        });

        //textViews
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        ArrayList<Product> productList = new ArrayList<Product>();

        try {
            productList = this.lazadaresults();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        textView2.setText(productList.get(1).getName());
    }


    //@Test
    //public void lazadaresults() throws InterruptedException, MalformedURLException, URISyntaxException {
    public ArrayList<Product> lazadaresults() throws InterruptedException, MalformedURLException, URISyntaxException {
        //HomePageUI search = new HomePageUI();
        //SearchView SearchBar = (SearchView) findViewById(R.id.searchBar);
        //String q = (String) SearchBar.getQuery();
        String q = "orange";

        ArrayList<Product> productList = new ArrayList<Product>();
        productList = crawler.testLazadaSearch(q);

        System.out.println(productList.get(1).getPrice());
        System.out.println(productList.get(1).getName());
        System.out.println(productList.get(1).getUrl());
        System.out.println(productList.get(1).getEcommerceSite());


        //System.out.println(Arrays.toString(productList.toArray()));

        //System.out.println(productList);
        //System.out.println(crawler.testLazadaSearch(q));

        return productList;
    }






}
