package com.ecomm.application.boundary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ecomm.application.R;
import com.ecomm.application.entity.Product;
import com.ecomm.application.webCrawl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.junit.Before;
import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.android.AndroidDriver;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class UserProfileUI extends AppCompatActivity {
    public static ArrayList<Product> products;

//    private Toolbar toolbar;
//    public TextView username = (TextView) findViewById(R.id.strUserName2);
//@Test
//public void testGoogleShouldWork() {
//    // Create a WebDriver instance with the activity in which we want the test to run
//    WebDriver driver = new AndroidDriver();
//    // Let’s open a web page
//    driver.get("http://www.google.com");
//
//    // Lookup for the search box by its name
//    WebElement searchBox = driver.findElement(By.name("q"));
//
//    // Enter a search query and submit
//    searchBox.sendKeys("weather in san francisco");
//    searchBox.submit();
//
//    // Making sure that Google shows 11 results
//    WebElement resultSection = driver.findElement(By.id("ires"));
//    List<WebElement> searchResults = resultSection.findElements(By.tagName("li"));
//
//    // Let’s ensure that the first result shown is the weather widget
//    WebElement weatherWidget = searchResults.get(0);
//}
//    @Test
    public static void test(){
        webCrawl crawl = new webCrawl();
        try {
            crawl.testLazadaSearch(HomePageUI.s);
            System.out.println("did this work");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("\n\nadsfjadfljalkdj");
        webCrawl crawl = new webCrawl();
        try {
            products = crawl.testLazadaSearch(HomePageUI.s);
            System.out.println("did this work");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println("adsfjadfljalkdj\n\n");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
//        try {
//            Process process = Runtime.getRuntime().exec("javac -cp \"/Users/graceong/AndroidStudioProjects/ecommTest/app/libs/*\" /Users/graceong/AndroidStudioProjects/ecommTest/app/src/main/java/com/ecomm/application/webCrawl.java /Users/graceong/AndroidStudioProjects/ecommTest/app/src/main/java/com/ecomm/application/entity/Product.java\n");
//            System.out.println("\n\n*******");
////            Process process = Runtime.getRuntime().exec("chmod 777 /Users/graceong/Downloads/chromedriver;./Users/graceong/Downloads/chromedriver");
////            Process process = Runtime.getRuntime().exec("sudo ls");
//            System.out.println(process.getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        test();
//        webCrawl crawl = new webCrawl();
//        try {
//            crawl.testLazadaSearch("chicken");
//            System.out.println("did this work");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_u_i);
        System.out.println("*******Adfadf*****");




        //Set back button to activity
        ImageButton backBtn = (ImageButton) findViewById(R.id.userBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backHomeIntent = new Intent(getApplicationContext(), HomePageUI.class);
                startActivity(backHomeIntent);
            }
        });

        String userEmail;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userEmail = user.getEmail();
        } else {
            // No user is signed in
            userEmail = "";
        }
        TextView username;
        username = findViewById(R.id.strUserName2);
        username.setText(userEmail);
//        username.setText(products.size());

        ImageView i = (ImageView)findViewById(R.id.imageView4);
        Picasso.with(UserProfileUI.this).load("https://sg-test-11.slatic.net/p/04be23fdeb4f6f9455d40d9d71c32c13.jpg_200x200q90.jpg_.webp").into(i);


        //click button to change password, show the textboxes
        Button changePassBtn = (Button) findViewById(R.id.changePassBtn);
        Button confirmChange = (Button) findViewById(R.id.confirmChangePass);
        TextView newPass, confirmNewPass, oldPass;
        newPass = findViewById(R.id.newPass);
        confirmNewPass = findViewById(R.id.confirmNewPass);
        oldPass = findViewById(R.id.oldPass);
        changePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPass.setVisibility(View.VISIBLE);
                newPass.setVisibility(View.VISIBLE);
                confirmNewPass.setVisibility(View.VISIBLE);
                changePassBtn.setVisibility(View.GONE);
                confirmChange.setVisibility(View.VISIBLE);
            }
        });

        //click confirm change to change password
        confirmChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user;
                user = FirebaseAuth.getInstance().getCurrentUser();
                final String email = user.getEmail();
                AuthCredential credential = EmailAuthProvider.getCredential(email,oldPass.getText().toString().trim());

                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            user.updatePassword(newPass.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(UserProfileUI.this, "Something went wrong. Please try again later", Toast.LENGTH_LONG).show();
                                    }else {
                                        Toast.makeText(UserProfileUI.this, "Password Successfully Modified", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(UserProfileUI.this, "Authentication Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        System.out.println("********Asdfadfadf**********");
//        test();
//    }
}