package com.ecomm.application.control;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityManagerCompat;

import com.ecomm.application.entity.Product;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
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

public class webCrawl extends AppCompatActivity {
    private static final String FILENAME = "SCRAPED.csv";
    //    static WebDriver driver;
//    @Test
//    public static void main(String arr[]) throws MalformedURLException, InterruptedException
    public void another(WebDriver driver){
        driver.get("https://www.lazada.sg");
        driver.close();
        System.out.println("another");
//        ActivityManager mngr = (ActivityManager) getSystemService( ACTIVITY_SERVICE );
//        List<ActivityManager.RunningTaskInfo> taskList = mngr.getRunningTasks(10);
////        taskList.get(0).numActivities == 1;
//        System.out.println(taskList.get(0).topActivity.getClassName());

            //// This is last activity


//        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.ecomm.application");
//
//        if (launchIntent != null) {
//            startActivity(launchIntent);
//        } else {
//            Toast.makeText(androiddrive.this, "There is no package available in android", Toast.LENGTH_LONG).show();
//        }

    }

    public ArrayList<Product> testLazadaSearch(WebDriver driver, String q) throws InterruptedException, URISyntaxException, IOException {

//        public void going() throws InterruptedException, IOException, URISyntaxException{
//        DesiredCapabilities capabilities= new DesiredCapabilities();
//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("deviceName", "emulator-5554");
//        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
//        capabilities.setCapability(CapabilityType.VERSION, "10");
//        capabilities.setCapability("chromedriverUseSystemExecutable", true);
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--user-agent=Chrome/86.0.4240.198");
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);


//        String url_lazada = "https://www.lazada.sg/catalog/?q=";
//        String query = "chicken";
//        String replace = query.split(" ")[0];
//        for(String a : query.split(" ")){
//            if (a!=query.split(" ")[0]){
//                replace+="+"+a;
//            }
//        }

//        String combined_url = url_lazada + replace;
//        driver.get(combined_url);
        driver.get("https://www.lazada.sg");
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        try {
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys(q + Keys.ENTER);
            searchBox.submit();
        } catch (Exception e) {
            WebElement searchBox = null;
        }

        Thread.sleep(20000); //10 sec

        // Thread.sleep(5000);  // Let the user actually see something!
//        List<WebElement> item_titles = driver.findElements(By.cssSelector("#root > div > div.ant-row.c10-Cg > div.ant-col-24 > div > div.ant-col-20.ant-col-push-4.c1z9Ut > div.c1_t2i > div:nth-child(1) > div > div > div.c3KeDq > div.c16H9d"));
        List<WebElement> item_titles = driver.findElements(By.cssSelector("div[class='c16H9d']"));
        List<WebElement> item_prices = driver.findElements(By.cssSelector("span[class='c13VH6']"));
        List<WebElement> item_urls = driver.findElements(By.cssSelector("div[class='c16H9d']"));
//        List<WebElement> item_imgs = driver.findElements(By.className("cRjKsc")); //c1ZEkM
        List<WebElement> item_imgs = driver.findElements(By.cssSelector("img[class='c1ZEkM']")); //c1ZEkM
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

//        driver.quit();


//        Intent prodIntent = new Intent(androidx.multidex.MultiDexApplication, ProductDisplayUI.class);
//        startActivity(prodIntent);
//        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.ecomm.application");
//
//        if (launchIntent != null) {
//            startActivity(launchIntent);
//        } else {
//            Toast.makeText(androiddrive.this, "There is no package available in android", Toast.LENGTH_LONG).show();
//        }

//        ((AndroidDriver) driver).s

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
            Product p = new Product(titles_list[m], price, urls_list[m], "Lazada");
            productList.add(p);
            //System.out.println(p.getPrice());
            System.out.println(titles_list[m]+"\t"+prices_list[m]+"\t"+urls_list[m]);

        }


        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_PERMISSION_STORAGE = 100;
            String[] permissions = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };

//            int permission = ActivityCompat.checkSelfPermission(androiddrive.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//            if (permission != PackageManager.PERMISSION_GRANTED) {
//                // We don't have permission so prompt the user
//                ActivityCompat.requestPermissions(androiddrive.this, permissions, REQUEST_CODE_PERMISSION_STORAGE);
//            }
        }
//
//            for (String str : permissions) {
////                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
//                    this.requestPermissions(permissions, REQUEST_CODE_PERMISSION_STORAGE);
////                    break;
////                }
//            }
//        }

//        java.io.File csvFile = new java.io.File(Environment
//                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//                + "/TESTING.csv");
//        org.apache.commons.io.FileUtils.copyInputStreamToFile(is, file);

        List<String[]> data = null;
        FileOutputStream fos = null;
        for(int m=0; m<item_titles.size(); m++){
            data = new ArrayList<String[]>();
            data.add(new String[]{titles_list[m],prices_list[m],urls_list[m]});
        }

        File storageDir = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DOWNLOADS );
        File file = File.createTempFile(
                "Adsfadsf",  // prefix
                ".csv",         // suffix
                storageDir      // directory
        );
        CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
        csvWrite.writeAll(data);
        csvWrite.close();

        System.out.println("yep it worked T_T");

        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists()){
            exportDir.mkdirs();
        }
        file = new File(exportDir, "teste.csv");
        try{
            file.createNewFile();
            csvWrite = new CSVWriter(new FileWriter(file));
            csvWrite.writeAll(data);
            csvWrite.close();
        }catch(Exception sqlEx){
            sqlEx.printStackTrace();
//            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
        }


//        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
//        String fileName = "scraped.csv";
//        String filePath = baseDir + File.separator + fileName;
//        File f = new File(filePath);
//        String csv = "test.csv";
//        CSVWriter writer = new CSVWriter(new FileWriter(filePath));
//
//        writer.writeAll(data);
//
//        writer.close();
//        Toast.makeText(this, "yes....? scraped.csv"+exportDir, Toast.LENGTH_LONG).show();



        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput("testing.csv", Context.MODE_PRIVATE));
            for(String[] a : data) {
                for (String b : a) {
                    outputStreamWriter.write(b);
                }
            }
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }


        try {
            fos = openFileOutput(FILENAME, MODE_PRIVATE);
            for(String[] a : data) {
                for (String b : a) {
                    fos.write(b.getBytes());
                }
            }
            Toast.makeText(this, "Saved to" + getFilesDir()+"/"+FILENAME,Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (fos!=null){
                try {
                    fos.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        driver.close();

//        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
//        String fileName = "scraped.csv";
//        String filePath = baseDir + File.separator + fileName;
//        File f = new File(filePath);
//        CSVWriter writer = null;
//        List<String[]> data = null;
//
//        for(int m=0; m<item_titles.size(); m++){
//            data = new ArrayList<String[]>();
//            data.add(new String[]{titles_list[m],prices_list[m],urls_list[m]});
//        }
//
//        // File exist
//        if(f.exists()&&!f.isDirectory())
//        {
//            FileWriter mFileWriter = new FileWriter(filePath, true);
//            writer = new CSVWriter(mFileWriter);
//        }
//        else
//        {
//            writer = new CSVWriter(new FileWriter(filePath));
//        }
//
//        writer.writeAll(data);
//        writer.close();
        System.out.println("okay");





//        CSVWriter writer = null;
//        List<String[]> data = null;
//        for(int m=0; m<item_titles.size(); m++){
//            writer = new CSVWriter(new FileWriter("scrapedData.csv"));
//
//            data = new ArrayList<String[]>();
//            data.add(new String[]{titles_list[m],prices_list[m],urls_list[m]});
//
//        }
//        writer.writeAll(data); // data is adding to csv
//        writer.close();
//        System.out.println("okay");



        return productList;

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

    //@Test
    public ArrayList<Product> goingqoo(String q) throws InterruptedException, IOException, URISyntaxException {
        //public void goingqoo() throws InterruptedException, IOException, URISyntaxException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(CapabilityType.VERSION, "10");
        capabilities.setCapability("chromedriverUseSystemExecutable", true);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-agent=Chrome/86.0.4240.198");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);


//        String url_lazada = "https://www.lazada.sg/catalog/?q=";
//        String query = "chicken";
//        String replace = query.split(" ")[0];
//        for(String a : query.split(" ")){
//            if (a!=query.split(" ")[0]){
//                replace+="+"+a;
//            }
//        }


        //String query = "chicken";
        String url_qoo = "https://www.qoo10.sg/s/" + q + "?keyword=" + q + "&keyword_auto_change=";
        System.out.println(url_qoo);



        //String combined_url = url_lazada + replace;
        driver.get(url_qoo);

//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
//        driver.get("https://www.lazada.sg");
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
        List<WebElement> item_titles = driver.findElements(By.cssSelector("div[class='sbj']"));
        List<WebElement> item_prices = driver.findElements(By.cssSelector("strong[title='Discounted Price']"));
        List<WebElement> item_urls = driver.findElements(By.cssSelector("div[class='sbj']"));
//        List<WebElement> item_imgs = driver.findElements(By.className("cRjKsc")); //c1ZEkM
        List<WebElement> item_imgs = driver.findElements(By.className("div[class='inner']")); //c1ZEkM
        List<WebElement> links = driver.findElements(By.tagName("img"));
//        List<WebElement> item_titles = driver.findElements(By.cssSelector("#root > div > div.ant-row.c10-Cg > div.ant-col-24 > div > div.ant-col-20.ant-col-push-4.c1z9Ut > div.c1_t2i > div:nth-child(7) > div > div > div.c3KeDq > div.c16H9d"));
        String[] titles_list = new String[item_titles.size()];
        String[] prices_list = new String[item_prices.size()];
        String[] urls_list = new String[item_urls.size()];
        String[] img_list = new String[links.size()];
        System.out.println(item_titles.size());
        System.out.println(item_prices.size());

        int i = 0, j = 0, k = 0, l = 0;
        for (WebElement a : item_titles) {    //convert to string []
            titles_list[i] = a.getText();
            //System.out.println(a.getText());
            i++;
        }


        for (WebElement a : item_prices) {    //convert to string []
            prices_list[j] = a.getText();
            System.out.println(a.getText());
            j++;
        }

        for (WebElement a : item_urls) {    //convert to string []
//            urls_list[k]=a.getAttribute("href");
            urls_list[k] = a.findElement(By.cssSelector("a")).getAttribute("href");
            //System.out.println(k + urls_list[k]);
            k++;
        }

//        for(WebElement a: item_imgs) {    //convert to string []
        for (WebElement a : links) {    //convert to string []
            if (a.getAttribute("src").contains("sg-test-11")) {
                img_list[l] = a.getAttribute("src");
//                System.out.println(a.getAttribute("src"));
//            img_list[l]=a.findElement(By.cssSelector("a")).getAttribute("src");
                //System.out.println(a.findElement(By.cssSelector("a")).findElement(By.className("c1ZEkM")).getText());
                //System.out.println(img_list[l]);
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

        for (int m = 0; m < 8; m++) {

            float price = Float.parseFloat(prices_list[m].substring(2));
            URI uri = new URI(urls_list[m]);
            URL url = uri.toURL();
            Product p = new Product(titles_list[m], price, urls_list[m], "qoo10");
            productList.add(p);
            //System.out.println(p.getPrice());
            //System.out.println(titles_list[m] + "\t" + price + "\t" + urls_list[m]);
        }

        driver.quit();
        return productList;


    }
}