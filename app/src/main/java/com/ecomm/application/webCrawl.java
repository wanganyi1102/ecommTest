package com.ecomm.application;

import com.ecomm.application.entity.Product;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class webCrawl {
    @Test
    public void testSearch() throws InterruptedException, MalformedURLException, URISyntaxException {

        ArrayList<Product> productList1 = new ArrayList<Product>();

//        //no argument
//        productList1 = testLazadaSearch();
//        System.out.println("hello");
//        System.out.println(productList1.get(0).getName());
//        System.out.println("hello1");

        //with argument
        productList1 = testLazadaSearch("milo");
        System.out.println(productList1.get(1).getName());
        System.out.println(productList1.get(1).getPrice());
        System.out.println(productList1.get(1).getUrl());
        System.out.println(productList1.get(1).getEcommerceSite());


    }
    //@Test
    //public ArrayList<Product> testLazadaSearch(String query) throws InterruptedException, URISyntaxException, MalformedURLException {
    //public ArrayList<Product> testLazadaSearch() throws InterruptedException, URISyntaxException, MalformedURLException {
    public ArrayList<Product> testLazadaSearch(String query) throws InterruptedException, URISyntaxException, MalformedURLException {
        // Optional. If not specified, WebDriver searches the PATH for chromedriver.
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("os.name"));

        //add path to chromedriver
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.indexOf("mac") >= 0) {
            System.setProperty("webdriver.chrome.driver", "src/main/java/com/ecomm/application/chromedriver");
        } else if (OS.indexOf("win") >= 0){
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Anyi\\WebCrawl\\src\\chromedriver.exe"); //change path
        } else if (OS.contains("nix") || OS.contains("nux")){
            System.setProperty("webdriver.chrome.driver", "src/main/java/com/ecomm/application/chromedriver");
        }


        ChromeOptions options = new ChromeOptions();
        final ChromeOptions headless = options.addArguments("disable-extensions", "--disable-popup-blocking", "headless");//delete headless to see/open chrome browser
        WebDriver driver = new ChromeDriver(options);
        /* WebDriver driver = new ChromeDriver(); */
        driver.get("https://www.lazada.sg");
//        Thread.sleep(5000);  // Let the user actually see something!

        // String content = driver.getPageSource(); // Read page content
        // System.out.println(content);  // Print the page content


        //find searchbar and send query
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys((CharSequence) query);
        searchBox.submit();


        // Thread.sleep(5000);  // Let the user actually see something!
        List<WebElement> item_titles = driver.findElements(By.className("c16H9d"));
        List<WebElement> item_prices = driver.findElements(By.className("c13VH6"));
        List<WebElement> item_urls = driver.findElements(By.className("c16H9d"));
//        List<WebElement> item_imgs = driver.findElements(By.className("cRjKsc")); //c1ZEkM
        List<WebElement> item_imgs = driver.findElements(By.className("c1ZEkM")); //c1ZEkM
        List<WebElement> links=driver.findElements(By.tagName("img"));

        String [] titles_list =new String[item_titles.size()];
        String [] prices_list =new String[item_prices.size()];
        String [] urls_list = new String[item_urls.size()];
        String [] img_list = new String[item_imgs.size()];

        int i=0, j=0, k=0, l=0;
        for(WebElement a: item_titles) {    //convert to string []
            titles_list[i]=a.getText();
            i++;
        }

        for(WebElement a: item_prices) {    //convert to string []
            prices_list[j]=a.getText();
            j++;
        }

        for(WebElement a: item_titles) {    //convert to string []
//            urls_list[k]=a.getAttribute("href");
            urls_list[k]=a.findElement(By.cssSelector("a")).getAttribute("href");
            k++;
        }

//        for(WebElement a: item_imgs) {    //convert to string []
        for(WebElement a: links) {    //convert to string []
            img_list[l]=a.getAttribute("src");
            System.out.println(a.getAttribute("src"));
//            img_list[l]=a.findElement(By.cssSelector("a")).getAttribute("src");
            //System.out.println(a.findElement(By.cssSelector("a")).findElement(By.className("c1ZEkM")).getText());
            System.out.println(img_list[l]);
            l++;
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

        return productList;
    }


    @Test
    public void productCrawl() {
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.indexOf("mac") >= 0) {
            System.setProperty("webdriver.chrome.driver", "src/main/java/com/example/homepagetest/chromedriver");
        } else if (OS.indexOf("win") >= 0) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Anyi\\WebCrawl\\src\\chromedriver.exe");
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-extensions", "--disable-popup-blocking", "headless");  //delete headless to see/open chrome browser
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.lazada.sg/products/milo-active-go-lower-in-sugar-gao-siew-dai-i303334245-s536676188.html?spm=a2o42.searchlistbrand.list.1.70854041BnOLQm&search=1");
        //gets product image url (works)
//        WebElement item_image = driver.findElement(By.xpath("//*[@id=\"module_item_gallery_1\"]/div/div[1]/div/img"));
//        System.out.println(item_image.getAttribute("src"));

        ////*[@id="module_product_detail"]/div/div/div/div/div[1]/div[2]/div   <div class="pdp-mod-spec-item-text"
        WebElement HeaderTxtElem = driver.findElement(By.xpath("//div[contains(text(),'Great chocolatey malt')]"));
        System.out.println(HeaderTxtElem.getAttribute("innerHTML"));
        //WebElement item_rating = driver.findElement(By.cssSelector());
        //WebElement item_rating = driver.findElement(By.xpath("//*[contains(concat( \" \", @class, \" \" ), concat( \" \", \"score-average\", \" \" ))]"));
        //WebElement item_rating = driver.findElement(By.xpath("/html/body/div[4]/div/div[9]/div[1]/div[2]/div/div/div/div[1]/div[2]/div/div/div[1]/div[1]/span[1]"));
        //WebElement item_rating = driver.findElement(By.className("span"));
        //System.out.println(item_rating.getText());

        driver.quit();

        System.out.println("\n\n\n\n**********************");
    }

    public ArrayList<Product> testQoo10Search() throws InterruptedException, URISyntaxException, MalformedURLException {
        // Optional. If not specified, WebDriver searches the PATH for chromedriver.
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("os.name"));

        //add path to chromedriver
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.indexOf("mac") >= 0) {
            System.setProperty("webdriver.chrome.driver", "src/main/java/com/example/homepagetest/chromedriver");
        } else if (OS.indexOf("win") >= 0){
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Anyi\\WebCrawl\\src\\chromedriver.exe"); //change path
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-extensions","--disable-popup-blocking","headless");  //delete headless to see/open chrome browser
        WebDriver driver = new ChromeDriver(options);
//         WebDriver driver = new ChromeDriver();
        driver.get("https://www.qoo10.sg/s/");
//        Thread.sleep(5000);  // Let the user actually see something!

        // String content = driver.getPageSource(); // Read page content
        // System.out.println(content);  // Print the page content


        //find searchbar and send query
        WebElement searchBox = driver.findElement(By.name("keyword"));
        searchBox.sendKeys("milo");
        searchBox.submit();

        // Thread.sleep(5000);  // Let the user actually see something!
        List<WebElement> item_titles = driver.findElements(By.className("sbj"));
        List<WebElement> item_prices = driver.findElements(By.className("prc"));
        List<WebElement> item_urls = driver.findElements(By.className("sbj"));

        String [] titles_list =new String[item_titles.size()];
        String [] prices_list =new String[item_prices.size()];
        String [] urls_list = new String[item_urls.size()];

        int i=0, j=0,k=0;
        for(WebElement a: item_titles) {    //convert to string []
            titles_list[i]=a.getText();
            i++;
        }

        for(WebElement a: item_prices) {    //convert to string []
            prices_list[j]=a.getText();
            j++;
        }

        for(WebElement a: item_titles) {    //convert to string []
//            urls_list[k]=a.getAttribute("href");
            urls_list[k]=a.findElement(By.cssSelector("a")).getAttribute("href");
            k++;
        }

        driver.quit();

        System.out.println("\n\n\n\n**********************");
//        System.out.println(item_titles.size());
//        System.out.println(item_prices.size());
//        System.out.println(urls_list.length);
//        System.out.println(item_urls.size());

        ArrayList<Product> productList = new ArrayList<Product>();

        for(int m=0; m<item_titles.size(); m++){
            // removing discount information and convert price to float
            int endIndex = prices_list[m].indexOf("\n");
            float price;
            if(endIndex!= -1){
                price = Float.parseFloat(prices_list[m].substring(2, endIndex));
            }else{
                price = Float.parseFloat(prices_list[m].substring(2));
            }

            // convert string to url
            URI uri = new URI(urls_list[m]);
            URL url = uri.toURL();
            Product p = new Product(titles_list[m], price, url, "Qoo10");
            productList.add(p);
            //System.out.println(titles_list[m]+"\t"+prices_list[m]+"\t"+urls_list[m]);
        }
//        for(Product p : productList){
//            System.out.println(p.getPrice());
//        }
        return productList;

    }

}
