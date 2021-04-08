package com.ecomm.application.boundary;

import android.content.Intent;
import android.os.Bundle;

import com.ecomm.application.entity.Product;
import com.ecomm.application.webCrawl;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.ecomm.application.R;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class SearchResultsUI extends AppCompatActivity {

    Button backHome;
    webCrawl crawler = new webCrawl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //back to home button
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results_u_i);

        backHome = findViewById(R.id.toHome);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backHomeIntent = new Intent(getApplicationContext(), HomePageUI.class);
                startActivity(backHomeIntent);
            }
        });





        //initialise all textViews
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        TextView textView5 = (TextView) findViewById(R.id.textView5);

        SearchView SearchBar = (SearchView) findViewById(R.id.searchBar);
        SearchBar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Functionality for the button...
                Intent searchPage = new Intent(getApplicationContext(), SearchResultsUI.class);
                startActivity(searchPage);
            }
        });

        /*
        final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getApplicationContext(), SearchResultsUI.class);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;

            }
        };


        SearchBar.setOnQueryTextListener(queryTextListener);

         */



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
