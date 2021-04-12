package com.ecomm.application.entity;

import java.util.ArrayList;
import java.util.Collections;

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
        this.minPrice = maxPrice;
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
            else if(s.contains("shipping")){
                if(s.substring(8).compareTo("Overseas") == 0){
                    unsortedList = filterShippingLoc(unsortedList, "Overseas");
                }
                else if(s.substring(8).compareTo("Domestic") == 0){
                    unsortedList = filterShippingLoc(unsortedList, "Singapore");
                }
                else if(s.substring(8).compareTo("Free") == 0){
                    unsortedList = filterFreeShipping(unsortedList);
                }
            }
            else if(s.contains("Lazada")){
                unsortedList = filterSite(unsortedList, "Lazada");
            }
            else if(s.contains("Qoo10")){
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

    //filter by price range
    public ArrayList<Product> filterPriceRange(ArrayList<Product> prodList){
        for(Product p : prodList){
            if(p.getPrice() < this.minPrice || p.getPrice() > this.maxPrice){
                prodList.remove(p);
            }
        }
        return prodList;
    }

    //filter by shopping site
    public ArrayList<Product> filterSite(ArrayList<Product> prodList, String site){
        for(Product p : prodList){
            if(p.getEcommerceSite().compareTo(site) != 0){
                prodList.remove(p);
            }
        }
        return prodList;
    }

    //filter free shipping fee
    public ArrayList<Product> filterFreeShipping(ArrayList<Product> prodList){
        for(Product p : prodList){
            if(p.getShippingFee() > 0){
                prodList.remove(p);
            }
        }
        return prodList;
    }

    //filter domestic or overseas shipping
    public ArrayList<Product> filterShippingLoc(ArrayList<Product> prodList, String location){
        for(Product p : prodList){
            if(p.getShipFrom().compareTo(location) != 0){
                prodList.remove(p);
            }
        }
        return prodList;
    }

}
