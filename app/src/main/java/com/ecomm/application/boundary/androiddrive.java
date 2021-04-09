//package com.ecomm.application.boundary;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.io.File;
//import java.net.MalformedURLException;
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.util.concurrent.TimeUnit;
//
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.android.AndroidDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
////import io.appium.java_client.android.AndroidDriver;
//
//public class androiddrive extends AppCompatActivity {
////    static WebDriver driver;
//    @Test
////    public static void main(String arr[]) throws MalformedURLException, InterruptedException
//    public void going() throws InterruptedException, MalformedURLException, URISyntaxException
//    {
//        DesiredCapabilities capabilities= new DesiredCapabilities();
//        capabilities.setCapability("deviceName", "Pixel 2 API 29");
//        capabilities.setCapability("platformVersion", "platform-version");
//        capabilities.setCapability("platformName", "platform-name");
////        capabilities.setCapability("app", app.getAbsolutePath());
//        String baseURL = "http://0.0.0.0:";
//        String minorURL = "/wd/hub";
//        String port = "4723";
//        WebDriver driver = new AndroidDriver(new URL(baseURL+port+minorURL), capabilities);
////        WebDriver driver = new AndroidDriver();
//        driver.get("http://www.google.co.in");
//
//        WebElement element = driver.findElement(By.name("q"));
//
//
//        element.sendKeys("Welcome");
//
//        element.submit();
//        System.out.println("Page title is: " + driver.getTitle());
//        driver.quit();
////
////        File app= new File("apk-file-path");
////        DesiredCapabilities capabilities= new DesiredCapabilities();
////        capabilities.setCapability("deviceName", "your-device-name");
////        capabilities.setCapability("platformVersion", "platform-version");
////        capabilities.setCapability("platformName", "platform-name");
////        capabilities.setCapability("app", app.getAbsolutePath());
////        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);
////        driver.findElement(By.id("username-element")).sendKeys("username");
////        driver.findElement(By.id("password-element")).sendKeys("password");
////        driver.findElement(By.id("password-element ")).click();
////        driver.quit();
//    }
//}