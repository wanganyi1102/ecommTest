package com.ecomm.application.control;

import android.media.Rating;

import com.ecomm.application.entity.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import kotlin.reflect.TypeOfKt;

public class Filter {
    private ArrayList<String> filterBy;
    private ArrayList<Product> unsortedList;
    private int minPrice;
    private int maxPrice;

    public void setFilterBy(ArrayList<String> filterBy) {
        this.filterBy = filterBy;
    }
    public void setMinPrice(int minPrice){
        this.minPrice = minPrice;
    }
    public void setMaxPrice(int maxPrice){
        this.maxPrice = maxPrice;
    }

    //filter by price range
    public ArrayList<Product> filterPriceRange(ArrayList<Product> prodList){
        Iterator<Product> itr = prodList.iterator();
        while (itr.hasNext()) {
            Product p = itr.next();
            if (p.getPrice() > maxPrice || p.getPrice() < minPrice){
                itr.remove();
            }
        }
        return prodList;
    }

    public ArrayList<Product> performFilter(ArrayList<String> filterBy, ArrayList<Product> unsortedList){
        for(String s : filterBy){
            if(s.contains("price")){
                if (s.substring(5).compareTo("Ascending") == 0){
                    unsortedList = sortByPrice(unsortedList, true);
                }else{
                    unsortedList = sortByPrice(unsortedList, false);
                }
            }
            if(s.contains("ratings")){
                if (s.substring(6).compareTo("Ascending") == 0){
                    unsortedList = sortByRating(unsortedList, true);
                }else{
                    unsortedList = sortByRating(unsortedList, false);
                }
            }
            if(s.contains("shipping")){
                System.out.println(s.substring(8));
                if(s.substring(8).compareTo("Overseas") == 0){
                    unsortedList = filterShippingLoc(unsortedList, "Overseas");
                }
                else if(s.substring(8).compareTo("Domestic") == 0){
                    unsortedList = filterShippingLoc(unsortedList, "Singapore");
                }
                else if(s.substring(8).contains("Free")){
                    unsortedList = filterFreeShipping(unsortedList);
                }
            }
            if(s.contains("Lazada")){
                unsortedList = filterSite(unsortedList, "Lazada");
            }
            if(s.contains("Qoo10")){
                unsortedList = filterSite(unsortedList, "Qoo10");
            }
        }
        return unsortedList;
    }

    // sort by price asc/desc
    public ArrayList<Product> sortByPrice(ArrayList<Product> sortList, boolean ascending){
        //ArrayList<Product> sortedList = new ArrayList<Product>();
        Collections.sort(sortList, Product.Price);
        if(ascending == false){
            Collections.reverse(sortList);
        }
        return sortList;
    }

    //sort by rating asc/desc
    public ArrayList<Product> sortByRating(ArrayList<Product> sortList, boolean ascending){
        //insertion sort
        int n = sortList.size();
        for (int i = 1; i < n; ++i) {
            double rating = sortList.get(i).getRating();
            int j = i - 1;

            while (j >= 0 && sortList.get(j).getRating() > rating) {
                sortList.set(j+1, sortList.get(j));
                j = j - 1;
            }
            sortList.set(j+1, sortList.get(i));
        }

        if(ascending == true){
            Collections.reverse(sortList);
        }
        return sortList;
    }

    //filter by shopping site
    public ArrayList<Product> filterSite(ArrayList<Product> prodList, String site){
        Iterator<Product> itr = prodList.iterator();
        while (itr.hasNext()) {
            Product p = itr.next();
            if (p.getEcommerceSite().compareTo(site) != 0){
                itr.remove();
            }
        }
        return prodList;
    }

    //filter free shipping fee
    public ArrayList<Product> filterFreeShipping(ArrayList<Product> prodList){
        System.out.println("filtering by free shipping...");
        Iterator<Product> itr = prodList.iterator();
        while (itr.hasNext()) {
            Product p = itr.next();
            System.out.println(p.getShippingFee());
            if (p.getShippingFee() > 0.0){
                itr.remove();
            }
        }
        return prodList;
    }

    //filter domestic or overseas shipping
    public ArrayList<Product> filterShippingLoc(ArrayList<Product> prodList, String location){
        Iterator<Product> itr = prodList.iterator();
        while (itr.hasNext()) {
            Product p = itr.next();
            if (p.getShipFrom().compareTo(location) != 0){
                itr.remove();
            }
        }
        return prodList;
    }

}
