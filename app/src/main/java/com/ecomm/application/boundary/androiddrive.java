package com.ecomm.application.boundary;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.ecomm.application.entity.Product;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.util.StringUtils;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import io.appium.java_client.android.AndroidDriver;

public class androiddrive extends AppCompatActivity {
    //    static WebDriver driver;
    @Test
//    public static void main(String arr[]) throws MalformedURLException, InterruptedException
    public void going() throws InterruptedException, IOException, URISyntaxException
    {
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(CapabilityType.VERSION, "10");
        capabilities.setCapability("chromedriverUseSystemExecutable", true);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-agent=Chrome/86.0.4240.198");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);


        String url_lazada = "https://www.lazada.sg/catalog/?q=";
        String query = "chicken";
        String replace = query.split(" ")[0];
        for(String a : query.split(" ")){
            if (a!=query.split(" ")[0]){
                replace+="+"+a;
            }
        }

        String combined_url = url_lazada + replace;
        driver.get(combined_url);

//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
//        try {
//            WebElement searchBox = driver.findElement(By.name("q"));
//            searchBox.sendKeys("noodles" + Keys.ENTER);
//            searchBox.submit();
//        } catch (Exception e) {
//            WebElement searchBox = null;
//        }

        Thread.sleep(20000); //10 sec

        // Thread.sleep(5000);  // Let the user actually see something!
//        List<WebElement> item_titles = driver.findElements(By.cssSelector("#root > div > div.ant-row.c10-Cg > div.ant-col-24 > div > div.ant-col-20.ant-col-push-4.c1z9Ut > div.c1_t2i > div:nth-child(1) > div > div > div.c3KeDq > div.c16H9d"));
        List<WebElement> item_titles = driver.findElements(By.cssSelector("div[class='c16H9d']"));
        List<WebElement> item_prices = driver.findElements(By.className("div[class='c13VH6']"));
        List<WebElement> item_urls = driver.findElements(By.className("div[class='c16H9d']"));
//        List<WebElement> item_imgs = driver.findElements(By.className("cRjKsc")); //c1ZEkM
        List<WebElement> item_imgs = driver.findElements(By.className("div[class='c1ZEkM']")); //c1ZEkM
        List<WebElement> links=driver.findElements(By.tagName("img"));
//        List<WebElement> item_titles = driver.findElements(By.cssSelector("#root > div > div.ant-row.c10-Cg > div.ant-col-24 > div > div.ant-col-20.ant-col-push-4.c1z9Ut > div.c1_t2i > div:nth-child(7) > div > div > div.c3KeDq > div.c16H9d"));
        String [] titles_list =new String[item_titles.size()];
        String [] prices_list =new String[item_prices.size()];
        String [] urls_list = new String[item_urls.size()];
        String [] img_list = new String[links.size()];
        System.out.println(item_titles.size());

        int i=0, j=0, k=0, l=0;
        for(WebElement a: item_titles) {    //convert to string []
            titles_list[i]=a.getText();
//            System.out.println(a.getText());
            i++;
        }


        for(WebElement a: item_prices) {    //convert to string []
            prices_list[j]=a.getText();
//            System.out.println(a.getText());
            j++;
        }

        for(WebElement a: item_urls) {    //convert to string []
//            urls_list[k]=a.getAttribute("href");
            urls_list[k]=a.findElement(By.cssSelector("a")).getAttribute("href");
//            System.out.println(k + urls_list[k]);
            k++;
        }

//        for(WebElement a: item_imgs) {    //convert to string []
        for(WebElement a: links) {    //convert to string []
            if (a.getAttribute("src").contains("sg-test-11")) {
                img_list[l] = a.getAttribute("src");
//                System.out.println(a.getAttribute("src"));
//            img_list[l]=a.findElement(By.cssSelector("a")).getAttribute("src");
                //System.out.println(a.findElement(By.cssSelector("a")).findElement(By.className("c1ZEkM")).getText());
//            System.out.println(img_list[l]);
                l++;
            }
        }

        driver.quit();

        System.out.println("\n\n\n\n**********************");
//        System.out.println(item_titles.size());
//        System.out.println(item_prices.size());
//        System.out.println(urls_list.length);
//        System.out.println(item_urls.size());

        ArrayList<Product> productList = new ArrayList<Product>();

        for(int m=0; m<item_titles.size(); m++){
            float price = Float.parseFloat(prices_list[m].substring(1));
            URI uri = new URI(urls_list[m]);
            URL url = uri.toURL();
            Product p = new Product(titles_list[m], price, url, "Lazada");
            productList.add(p);
            //System.out.println(p.getPrice());
            System.out.println(titles_list[m]+"\t"+prices_list[m]+"\t"+urls_list[m]);
        }


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