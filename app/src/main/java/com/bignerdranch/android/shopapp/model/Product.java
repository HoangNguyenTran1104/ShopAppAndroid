package com.bignerdranch.android.shopapp.model;

public class Product {
    int id;
    String name;
    String description;
    String price;
    String count;
    String priceCount;
    Integer imageUrl;



    public Product(int id,String name, String description, String price, String count, Integer imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.count = count;
        this.imageUrl = imageUrl;
        this.id = id;
    }

    public Product(String name, String description, String price, String count, Integer imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.count = count;
        this.imageUrl = imageUrl;
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(int id, String name, String description, String price, String count, String priceCount, Integer imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.count = count;
        this.priceCount = priceCount;
        this.imageUrl = imageUrl;
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getCount() {
        return count;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public String getPriceCount() {
        return priceCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPriceCount(String priceCount) {
        this.priceCount = priceCount;
    }
}
