package com.ecomm.application.boundary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecomm.application.R;
import com.ecomm.application.entity.Product;
import com.ecomm.application.entity.Transaction;
import com.squareup.picasso.Picasso;

public class ProductDisplayUI extends AppCompatActivity {

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display_u_i);

        if(getIntent().hasExtra("com.ecomm.application.PRODUCT_INFO")){
            product = (Product) getIntent().getSerializableExtra("com.ecomm.application.PRODUCT_INFO");  //Extras("com.ecomm.application.PRODUCT_INFO", mango);
        }

        //set title to product title
        TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        titleTextView.setText(product.getName());

        //set description to product description
        TextView descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(product.getDescription());

        //set price to product price
        TextView priceTextView = (TextView) findViewById(R.id.priceTextView);
        priceTextView.setText("S$ " + String.format("%.2f", product.getPrice()));

        //set image to product image
        String imageURL = product.getImageURL();
        ImageView productImage = (ImageView) findViewById(R.id.productImage);
        //productImage.setImageDrawable(LoadImageFromWebOperations(imageURL));
        Picasso.with(ProductDisplayUI.this).load(imageURL).into(productImage);

        //set rating to product rating
        TextView rating = (TextView) findViewById(R.id.rating);
        rating.setText(product.getRating()+ "");

        //set site icon
        ImageView siteIcon = (ImageView) findViewById(R.id.siteIcon);
        String site = product.getEcommerceSite();
        if(site.compareTo("Lazada") == 0){
            siteIcon.setImageDrawable(getResources().getDrawable(R.drawable.lazada));
        }
        else{
            siteIcon.setImageDrawable(getResources().getDrawable(R.drawable.qoo10));
        }

        //click gotoecommersewebsite button
        Button externalSiteBtn = (Button) findViewById(R.id.externalSiteBtn);
        externalSiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlToVisit = product.getUrl().toString();
                Intent gotoSite = new Intent(Intent.ACTION_VIEW, Uri.parse(urlToVisit));
                startActivity(gotoSite);
            }
        });

        //click add to cart
        Button addToCartBtn = (Button) findViewById(R.id.addToCartBtn);
        // add code for adding to cart
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // not sure if this is the right code to pass the product object to shopping cart ui
                //Intent addItemToCart = new Intent(getApplicationContext(), ProductDisplayUI.class);
                Intent addItemToCart = new Intent(getApplicationContext(), ShoppingCartUI.class);
                Transaction.addProduct(product);
                addItemToCart.putExtra("price", product);
                startActivity(addItemToCart);
            }
        });

        //click on back button
        Button backBtn = (Button) findViewById(R.id.backToHome);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //goes back to previous page
            }
        });

        //contact seller btn
        TextView contactSeller = (TextView) findViewById(R.id.contactSellerTextView);
        contactSeller.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent messageSeller = new Intent(getApplicationContext(), ContactSellerUI.class);
                startActivity(messageSeller);
            }
        }));

    }
}