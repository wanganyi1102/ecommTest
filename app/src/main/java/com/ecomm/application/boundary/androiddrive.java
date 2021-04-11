package com.ecomm.application.boundary;

import android.content.Intent;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.*;
//import io.appium.java_client.android.AndroidDriver;

public class androiddrive extends AppCompatActivity {
//    static WebDriver driver;
    @Test
//    public static void main(String arr[]) throws MalformedURLException, InterruptedException
    public void going() throws InterruptedException, IOException, URISyntaxException
    {
//        Map<String, String> mobileEmulation = new HashMap<>();
//        mobileEmulation.put("deviceName", "Nexus 5");
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
//        chromeOptions.setCapability("androidPackage","com.android.chrome");
        DesiredCapabilities capabilities= new DesiredCapabilities();
//        capabilities.setCapability("chromedriverUseSystemExecutable", "/Users/graceong/AndroidStudioProjects/ecommTest/app/src/main/java/com/ecomm/application/chromedriver");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554");
//        capabilities.setCapability("androidPackage","com.android.chrome");
//        capabilities.setCapability("version", "10");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(CapabilityType.VERSION, "10");
//        capabilities.setCapability("appPackage", "com.google.android.apps.chrome");
//        capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        capabilities.setCapability("chromedriverUseSystemExecutable", true);


//        capabilities.setCapability("appPackage", "com.ecomm.application");
//        capabilities.setCapability("appActivity", "com.ecomm.application.boundary.HomePageUI");
        //        capabilities.setCapability("noReset", true);
//        capabilities.setCapability("browserName", "chrome");
        WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
//        driver.get("https://www.amazon.com");
//        driver.findElement(By.id("btn_setup")).click();
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        Uri.parse("http://www.google.com");
//        startActivity(intent);
//        driver.get("http://www.google.com");
//        System.out.println("Page title is: " + driver.getTitle());
        driver.get("https://www.lazada.sg");
//        Thread.sleep(5000);  // Let the user actually see something!

        // String content = driver.getPageSource(); // Read page content
        // System.out.println(content);  // Print the page content


        //find searchbar and send query
//        WebElement searchBox = driver.findElement(By.cssSelector("#q"));

        WebElement searchBox = driver.findElement(By.cssSelector("#root > div > div.ant-row.c10-Cg > div.ant-col-24 > div > div.ant-col-20.ant-col-push-4.c1z9Ut > div.c1_t2i > div:nth-child(1) > div > div > div.c3KeDq > div.c16H9d"));
        searchBox.sendKeys("noodles");
        searchBox.submit();


        // Thread.sleep(5000);  // Let the user actually see something!
        List<WebElement> item_titles = driver.findElements(By.cssSelector("#root > div > div.c1Pean > div > div.c2bxk7.c1pRUd.c2CJAA > div:nth-child(1) > div:nth-child(1) > a > div.c2988Q > div.c1ZOjf"));
        String [] titles_list =new String[item_titles.size()];
        int i=0, j=0, k=0, l=0;
        for(WebElement a: item_titles) {    //convert to string []
            titles_list[i]=a.getText();
            System.out.println(a.getText());
            i++;
        }

        //        DesiredCapabilities capabilities= new DesiredCapabilities();
////        capabilities.setCapability("deviceName", "Pixel 2 API 29");
////        capabilities.setCapability("platformVersion", "platform-version");
////        capabilities.setCapability("platformName", "platform-name");
////        capabilities.setCapability("testdroid_username", "ville-veikko.helppi@bitbar.com");
////        capabilities.setCapability("testdroid_device", "Pixel 2 API 29");
////        capabilities.setCapability("testdroid_target", "chrome");
////        capabilities.setCapability("testdroid_project", "Appium Chrome");
////        capabilities.setCapability("testdroid_testrun", "TestRun 1");
//        capabilities.setCapability("platformName", "Android");
////        capabilities.setCapability("browserName", "chrome");
//
//
//        capabilities.setCapability("appPackage", "com.ecomm.application");
//        capabilities.setCapability("chromedriverUseSystemExecutable", "true");
////        capabilities.setCapability("app", app.getAbsolutePath());
//        String baseURL = "http://0.0.0.0:";
//        String minorURL = "/wd/hub";
//        String port = "4723";
//        String appium_URL = "http://appium.testdroid.com/wd/hub";
//        String screenshot_path = "/Users/graceong/Downloads";

//        Process process = Runtime.getRuntime().exec("export PATH=\"${JAVA_HOME}/bin:${ANDROID_HOME}/tools:${ANDROID_HOME}/platform-tools:${PATH}");
//        ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash","-c","pwd;export PATH=\"${JAVA_HOME}/bin:${ANDROID_SDK_ROOT}/tools:${ANDROID_SDK_ROOT}/platform-tools:${PATH}");
//        ProcessBuilder processBuilder = new ProcessBuilder("export ANDROID_SDK_ROOT=/Users/graceong/Library/Android/sdk;" +
//                "export JAVA_HOME=$(/usr/libexec/java_home);" +
//                "export PATH=${JAVA_HOME}/bin:$PATH;" +
//                "export PATH=${PATH}:${ANDROID_SDK_ROOT}/tools;" +
//                "export PATH=${PATH}:${ANDROID_SDK_ROOT}/platforms-tools;" +
//                "export PATH=${PATH}:${ANDROID_SDK_ROOT}/build-tools/27.0.1;" +
//                "export PATH=${PATH}:${JAVA_HOME}");
////        processBuilder.environment().put("ANDROID_HOME", "Users/graceong/Library/Android/sdk");
//        processBuilder.environment().put("ANDROID_HOME", "/Applications/ADT/sdk");
//        processBuilder.environment().put("PATH", "$PATH:$ANDROID_HOME/bin");
//        processBuilder.environment().put("ANDROID_SDK_ROOT", "Users/graceong/Library/Android/sdk");
//        processBuilder.start();
//        WebDriver driver = new RemoteWebDriver(new URL(appium_URL), capabilities);
//        WebDriver driver = new RemoteWebDriver(new URL(baseURL+port+minorURL), capabilities);
////        WebDriver driver = new AndroidDriver();
//        driver.get("https://bitbar.com/testing");
//        WebElement elem = driver.findElement(By.xpath("//*[@id=\"menu\"]/ul/li[1]/a"));
//        elem.click();
//        driver.get("http://www.google.co.in");

//        WebElement element = driver.findElement(By.name("q"));


//        element.sendKeys("Welcome");
//
//        element.submit();
//        System.out.println("Page title is: " + driver.getTitle());
        driver.quit();
//
//        File app= new File("apk-file-path");
//        DesiredCapabilities capabilities= new DesiredCapabilities();
//        capabilities.setCapability("deviceName", "your-device-name");
//        capabilities.setCapability("platformVersion", "platform-version");
//        capabilities.setCapability("platformName", "platform-name");
//        capabilities.setCapability("app", app.getAbsolutePath());
//        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);
//        driver.findElement(By.id("username-element")).sendKeys("username");
//        driver.findElement(By.id("password-element")).sendKeys("password");
//        driver.findElement(By.id("password-element ")).click();
//        driver.quit();
    }
}