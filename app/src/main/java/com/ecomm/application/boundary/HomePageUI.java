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
import com.squareup.picasso.Picasso;


import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class HomePageUI extends AppCompatActivity {
    public static ArrayList<Product> products;
    public static String s;
    public static String q = new String();

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

        URI uri = null;
        try {
            uri = new URI("https://www.lazada.sg/products/cambodia-keo-romeat-mango-i395210387-s952532064.html?spm=a2o42.searchlist.list.3.e8f72f9eWNyvMi&search=1");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        URL miloURL = null;
        try {
            miloURL = uri.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //create fake products
        Product milo1 = new Product("(2 Pack Bundle) MILO® Instant 3in1 ACTIV-GO 18 Sachets x 27g (Expires May 2022)", 13.0,miloURL, "MILO is made with the nutritious goodness of malt, milk, cocoa, essential vitamins and minerals, giving you the energy to go further.", "Qoo10",4.3, 2.0  , "https://sg-test-11.slatic.net/p/514d199bc6acc8eceb1ce3ae992599ce.jpg_100x100q90.jpg_.webp");
        Product milo2 = new Product(" MILO Australian Recipe Powder Tin 1.25KG", 17.9,miloURL, "MILO is made with the nutritious goodness of malt, milk, cocoa, essential vitamins and minerals, giving you the energy to go further.", "Qoo10",4.3, 2.0 , "https://sg-test-11.slatic.net/p/067c568c3897e5b972963a421716e1e5.jpg_100x100q90.jpg_.webp");
        Product milo3 = new Product("MILO Instant 3in1 ACTIVGO Sachet 18x27G (Expires May 2022)", 6.5,miloURL, "MILO is made with the nutritious goodness of malt, milk, cocoa, essential vitamins and minerals, giving you the energy to go further.", "Qoo10",4.3, 2.0 ,"https://sg-test-11.slatic.net/p/c640737f7fb5b9fe309e006928e220cc.jpg_100x100q90.jpg_.webp");
        Product milo4 = new Product("(Bundle of 2) MILO Australian Recipe Powder Refill 900G (Expires Feb 2022)", 25.0,miloURL, "MILO is made with the nutritious goodness of malt, milk, cocoa, essential vitamins and minerals, giving you the energy to go further.", "Qoo10",4.3, 2.0 ,"https://sg-test-11.slatic.net/p/3b4286a37cd08cf8de06215800ca8fc4.jpg_100x100q90.jpg_.webp");
        ArrayList<Product> productsToDisplay = new ArrayList<Product>();
        productsToDisplay.add(milo1);
        productsToDisplay.add(milo2);
        productsToDisplay.add(milo3);
        productsToDisplay.add(milo4);

        //(test filter) click on filter button
        TextView filterSearchBtn = (TextView) findViewById(R.id.filterSearchBtn);
        filterSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent testListView = new Intent(getApplicationContext(), SearchTestUI.class);
//                startActivity(testListView);
                Intent filterIntent = new Intent(getApplicationContext(), FilterUI.class);
                startActivity(filterIntent);
            }
        });


        TextView textView2 = (TextView) findViewById(R.id.textView2);
        SearchView SearchBar = (SearchView) findViewById(R.id.searchBar);
        SearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.isEmpty()){
                    System.out.println("Please enter search item");
                }

                q = SearchBar.getQuery().toString();
                System.out.println(q);

                try {
                    DesiredCapabilities capabilities= new DesiredCapabilities();
                    capabilities.setCapability("platformName", "Android");
                    capabilities.setCapability("deviceName", "emulator-5554");
                    capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
                    capabilities.setCapability(CapabilityType.VERSION, "10");
                    capabilities.setCapability("chromedriverUseSystemExecutable", true);
                    capabilities.setCapability("automationName","UIAutomator2");
                    capabilities.setCapability("version","10");
                    capabilities.setCapability("adbExecTimeout", "30000");
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--user-agent=Chrome/86.0.4240.198");
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//            System.out.println("creating webdriver"); ///////////
//            WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
                    WebDriver driver = new RemoteWebDriver(new URL("http://10.27.41.69:4723/wd/hub"),capabilities);
//            WebDriver driver = new RemoteWebDriver(capabilities);
//            System.out.println("getting lazada");
//            driver.get("https://www.lazada.sg");
//            System.out.println("crawl");
                    androiddrive crawl = new androiddrive();
                    products = crawl.testLazadaSearch(driver, q);
                    System.out.println(products.get(1).getName());
                    System.out.println(products.get(1).getPrice());
                    System.out.println(products.get(1).getUrl());
                    System.out.println(products.get(1).getEcommerceSite());
//            System.out.println("worked");
                } catch (MalformedURLException | InterruptedException | URISyntaxException e){
                    e.printStackTrace();
                }

                Intent searchIntent = new Intent(getApplicationContext(), SearchTestUI.class);
//                searchIntent.putExtra("com.ecomm.application.QUERY", query);
                searchIntent.putExtra("com.ecomm.application.PRODUCT_LIST", productsToDisplay);
                startActivity(searchIntent);
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }


        });


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
                Intent openSearchIntent = new Intent(getApplicationContext(), SearchTestUI.class);
                startActivity(openSearchIntent);
            }
        });

    }
}