package com.ecomm.application.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ecomm.application.R;
import com.ecomm.application.webCrawl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.android.AndroidDriver;


public class UserProfileUI extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
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
}