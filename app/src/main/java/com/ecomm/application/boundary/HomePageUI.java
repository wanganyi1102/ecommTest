package com.ecomm.application.boundary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.ecomm.application.R;
import com.ecomm.application.entity.Product;
import com.ecomm.application.webCrawl;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

        System.out.println(getApplicationContext());

        s = "pudding";


        //get intent passed from loginUI
        if (getIntent().hasExtra("com.example.ACCOUNT")){
            String username = getIntent().getExtras().getString("com.example.ACCOUNT");
        }

        Button signout = (Button) findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser mAuth;
                FirebaseAuth.getInstance().signOut();
                Intent login = new Intent(getApplicationContext(), LoginUI.class);
                startActivity(login);
            }
        });

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

        //create fake products
        Product milo1 = new Product("(2 Pack Bundle) MILO® Instant 3in1 ACTIV-GO 18 Sachets x 27g (Expires May 2022)", 13.55,"https://www.lazada.sg/products/2-pack-bundle-milo-3in1-activgo-20x27g-free-2-sachets-promo-pack-i325002865-s677260392.html?spm=a2o42.searchlistbrand.list.21.5c514041QejQHI&search=1&freeshipping=1", "MILO is made with the nutritious goodness of malt, milk, cocoa, essential vitamins and minerals, giving you the energy to go further.", "Lazada",4.3, 2.0, 100, "https://sg-test-11.slatic.net/p/514d199bc6acc8eceb1ce3ae992599ce.jpg_100x100q90.jpg_.webp");
        Product milo2 = new Product("MILO Australian Recipe Powder Tin 1.25KG", 17.9,"https://www.lazada.sg/products/milo-australian-recipe-powder-tin-125kg-i266854852-s414549673.html?spm=a2o42.searchlistbrand.list.7.5c514041bToQId&search=1&freeshipping=1", "MILO is made with the nutritious goodness of malt, milk, cocoa, essential vitamins and minerals, giving you the energy to go further.", "Lazada",3.9, 2.0 , 80, "https://sg-test-11.slatic.net/p/067c568c3897e5b972963a421716e1e5.jpg_100x100q90.jpg_.webp");
        Product milo3 = new Product("MILO Instant 3in1 ACTIVGO Sachet 18x27G (Expires May 2022)", 6.5,"https://www.lazada.sg/products/milo-3in1-activgo-20x27g-free-2-sachets-promo-pack-i1517978352-s7092168353.html?spm=a2o42.searchlistbrand.list.19.5c514041mZArYY&search=1&freeshipping=1", "MILO is made with the nutritious goodness of malt, milk, cocoa, essential vitamins and minerals, giving you the energy to go further.", "Lazada",4.3, 0.0 ,56, "https://sg-test-11.slatic.net/p/c640737f7fb5b9fe309e006928e220cc.jpg_100x100q90.jpg_.webp");
        Product milo4 = new Product("(Bundle of 2) MILO Australian Recipe Powder Refill 900G (Expires Feb 2022)", 25.5,"https://www.lazada.sg/products/bundle-of-2-milo-australian-recipe-powder-refill-900g-expires-feb-2022-i1096012563-s4229812303.html?spm=a2o42.searchlistbrand.list.48.5c514041ofNP5G&search=1&freeshipping=1", "MILO is made with the nutritious goodness of malt, milk, cocoa, essential vitamins and minerals, giving you the energy to go further.", "Lazada",4.3, 1.0 ,78, "https://sg-test-11.slatic.net/p/3b4286a37cd08cf8de06215800ca8fc4.jpg_100x100q90.jpg_.webp");
        Product milo5 = new Product("MILO GAO KOSONG Powder Refill Pack 750g (Expires Feb 2022)", 7.95,"https://www.lazada.sg/products/milo-gao-kosong-powder-refill-pack-750g-expires-feb-2022-i266864077-s414554134.html?spm=a2o42.searchlistbrand.list.13.57dd40410SEKoO&search=1&freeshipping=1", "MILO® is specially formulated with protomalt, milk and cocoa, providing the essential vitamins and minerals of a nutritious breakfast beverage.", "Lazada",4.5, 2.0 ,85, "https://laz-img-sg.alicdn.com/p/8b9064d60f43db66ca00b1b18dcf42be.jpg_100x100q80.jpg_.webp");
        Product milo6 = new Product("(48 Packets/2 Cartons) MILO UHT Chocolate Malt Packet Liquid Drink 200ml x 48", 34,"https://www.lazada.sg/products/48-packets2-cartons-milo-uht-chocolate-malt-packet-liquid-drink-200ml-x-48-i111650098-s117668321.html?spm=a2o42.searchlistbrand.list.53.5c514041kET5Yg&search=1&freeshipping=1", "Low in fatContains ACTIVGO- PROTOMALT, vitamins and minerals giving you the energy to winContains Vitamin C and Iron", "Lazada",4.7, 0.0 ,93, "http://laz-img-sg.alicdn.com/p/17e3734d36da7e0e18712bfeb7875de5.jpg_100x100q80.jpg_.webp");
        Product milo7 = new Product("Nestle Milo Calcium (24 x 240ml)", 18.95, "https://www.qoo10.sg/item/NESTLE-MILO-CALCIUM-24-X-240ML/658364556?banner_no=1305330", "Icy cold milo in cans. Rich in calcium. Shipped from Singapore.", "Qoo10", 3.5, 1.99, 20, "https://sg-test-11.slatic.net/p/16/nestle-milo-original-can-x-24-cans-240ml-deal-5131-56091575-ccd73915012c40f2a2d3fca2565db7e8-catalog_233.jpg_100x100q90.jpg_.webp");
        Product milo8 = new Product("[Nestle] MILO® UHT Ready To Drink! 24 X 200ml", 15.99, "https://www.qoo10.sg/item/NESTL%c3%89-MILO-UHT-READY-TO-DRINK-24-X-200ML/679037736?banner_no=1305330", "Milo is a chocolate and malt powder typically mixed with hot water or milk (or both) to produce a beverage popular in Oceania, South America, Southeast Asia and parts of Africa. Produced by Nestlé, Milo was originally developed by Australian inventor Thomas Mayne in 1934.", "Qoo10", 4.1, 1.99, 96, "http://laz-img-sg.alicdn.com/p/17e3734d36da7e0e18712bfeb7875de5.jpg_100x100q80.jpg_.webp");
        Product milo9 = new Product("[NESTLE] BUNDLE OF 3: MILO® Biscuit Sandwich (with and without Milk)", 16.5, "https://www.qoo10.sg/item/NESTLE-NESTLE-BUNDLE-OF-3-MILO-BISCUIT-SANDWICH-WITH-AND-WITHOUT-MILK/540022888?banner_no=1305330",
                "MILO now comes in a form of a Sandwich Cookie Biscuit with Milk Filling! Try it now!", "Qoo10", 4.9, 3.99, 56, "https://laz-img-sg.alicdn.com/p/6f35989018c7b392a896394817f065ff.jpg_100x100q80.jpg_.webp");
        Product milo10 = new Product("[LOCAL SELLER] MILO Active Go Powder 1KG", 11.9, "https://www.qoo10.sg/item/MILO-LOCAL-SELLER-MILO-ACTIVE-GO-POWDER-1KG/679214056?banner_no=1305330", "Having breakfast with MILO sets a positive mood for the rest of your day, and gives you positive energy both mentally and physically.", "Qoo10", 4.3, 0.0, 73, "https://sg-test-11.slatic.net/p/2aee9561221d978c9977b45890bb2df4.jpg_100x100q90.jpg_.webp");

        ArrayList<Product> productsToDisplay = new ArrayList<Product>();
        productsToDisplay.add(milo1);
        productsToDisplay.add(milo2);
        productsToDisplay.add(milo3);
        productsToDisplay.add(milo4);
        productsToDisplay.add(milo5);
        productsToDisplay.add(milo6);
        productsToDisplay.add(milo7);
        productsToDisplay.add(milo8);
        productsToDisplay.add(milo9);
        productsToDisplay.add(milo10);

        TextView textView2 = (TextView) findViewById(R.id.textView2);

        //search bar
        SearchView SearchBar = (SearchView) findViewById(R.id.searchBar);
        SearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.isEmpty()){
                    System.out.println("Please enter search item");
                }

                q = SearchBar.getQuery().toString();
                System.out.println(q);

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

//        URI uri = null;
//        try {
//            uri = new URI("https://www.lazada.sg/products/cambodia-keo-romeat-mango-i395210387-s952532064.html?spm=a2o42.searchlist.list.3.e8f72f9eWNyvMi&search=1");
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        URL mangoURL = null;
//        try {
//            mangoURL = uri.toURL();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

        ImageView mangoImageView = (ImageView) findViewById(R.id.mangoImageView);
        mangoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Product mango = new Product("mango", 2.50, "https://www.lazada.sg/products/cambodia-keo-romeat-mango-i395210387-s952532064.html?spm=a2o42.searchlist.list.3.e8f72f9eWNyvMi&search=1",
                        "Keo Romeat mango from Cambodia which is also known as the Goldstar mango locally, have a golden yellow color peel when ripen. It as a balanced sweet flavor with a bright orange flesh that consist of minimum fiber allowing it to have a smooth texture.",
                        "Lazada", 4.4, 3.9, "https://sg-test-11.slatic.net/p/b8375ca4a12d1937e2954a31955881ba.jpg_100x100q90.jpg_.webp",1);

                Intent displayProductDetail = new Intent(getApplicationContext(), ProductDisplayUI.class);
                displayProductDetail.putExtra("com.ecomm.application.PRODUCT_INFO", mango);
                startActivity(displayProductDetail);
            }
        });


            //fake phone product
            ImageView phone = (ImageView) findViewById(R.id.phone);
            phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Product phone = new Product("phone", 8.90, "https://www.lazada.sg/products/cambodia-keo-romeat-mango-i395210387-s952532064.html?spm=a2o42.searchlist.list.3.e8f72f9eWNyvMi&search=1",
                            "Iphone 12 from Singtel. No charger included, no ipods included.",
                            "Qoo10", 3.9, 10.5, "https://sg-test-11.slatic.net/p/e9592d365dbdaaaa27aa39edba680960.jpg_100x100q90.jpg_.webp",1);

                    Intent displayProductDetail = new Intent(getApplicationContext(), ProductDisplayUI.class);
                    displayProductDetail.putExtra("com.ecomm.application.PRODUCT_INFO", phone);
                    startActivity(displayProductDetail);
                }
            });

            //fake women's clothes
        ArrayList<Product> womensProducts = new ArrayList<Product>();
        Product women1 = new Product("ZANZEA Long Top Solid Blouse Shirt Dress", 13.9, "https://www.lazada.sg/products/zanzea-long-top-solid-blouse-shirt-dress-women-long-sleeve-pockets-buttons-dress-i230468168-s409256320.html?spm=a2o42.searchlist.list.7.23735d4eQvMRg6&search=1&freeshipping=1", "Material:Cotton\n" + "Type:Dress Color:Black,White,Red,Navy,Pink. Package include:1. Dress Feature: Buttons, Pockets, Shirt Dress, Solid Pattern.", "Lazada",
                4.5, 0.0, 76, "https://laz-img-sg.alicdn.com/original/6f7eb08fd164a87c562c5c4a2d98d000.jpg_100x100q80.jpg_.webp");
        Product women2 = new Product("Vero Moda Women Ruffled Trims Off-the-shoulder Printed Dress", 25.5, "https://www.google.com", "dress", "Lazada", 4.3,2.0, 86, "https://my-test-11.slatic.net/p/d83e8e0789ff282ea50cbc03fa5681b8.jpg_100x100q80.jpg_.webp");
        Product women3 = new Product("ZANZEA Boho Style Printed Floral Dress Summer Womens Short Sleeve Dresses Casual", 11.4, "https://www.google.com", "dress", "Lazada", 4.3,2.0, 86, "https://my-test-11.slatic.net/p/7a209209b41eb01b3452d713f490a34b.jpg_100x100q90.jpg_.webp");
        Product women4 = new Product("Pink Tops Summer Women Tshirt Fashion Tee Shirt O-neck Cotton T-shirts Korea Short-sleeved T-shirt For Women (On Sale)", 9.8, "https://www.google.com", "dress", "Lazada", 4.3,2.0, 86, "https://my-test-11.slatic.net/p/aff4962833c542332680e84e55e68426.jpg_100x100q90.jpg_.webp");
        Product women5 = new Product("VONDA Womens Long Sleeve Loose Abaya Retro Dress Oversized Basic Long Maxi Dress", 16, "https://www.google.com", "dress", "Lazada", 4.3,2.0, 86, "https://my-test-11.slatic.net/p/120c97ded9cf7916ecb8072e7e49d993.jpg_100x100q80.jpg_.webp");
        Product women6 = new Product("ZANZEA Women Muslim Dubai Abaya Kaftan Long Sleeve Crew Neck Maxi Shirt Dress", 15.2, "https://www.google.com", "dress", "Lazada", 4.3,2.0, 86, "https://my-test-11.slatic.net/p/d2fb1f45ca7ec4ad47b5b40ceac9f016.jpg_100x100q90.jpg_.webp");
        womensProducts.add(women1);
        womensProducts.add(women2);
        womensProducts.add(women3);
        womensProducts.add(women4);
        womensProducts.add(women5);
        womensProducts.add(women6);

        ImageView womensClothesBtn = (ImageView) findViewById(R.id.womensClothesBtn);
        womensClothesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent womenIntent = new Intent(getApplicationContext(), SearchTestUI.class);
                womenIntent.putExtra("com.ecomm.application.PRODUCT_LIST", womensProducts);
                startActivity(womenIntent);
            }
        });

    }


}

