package com.bignerdranch.android.shopapp.model;

public class Discount {
    Integer id;
    Integer imageurl;

    public Discount(Integer id, Integer imageurl) {
        this.id = id;
        this.imageurl = imageurl;
    }

    public Integer getId() {
        return id;
    }

    public Integer getImageurl() {
        return imageurl;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setImageurl(Integer imageurl) {
        this.imageurl = imageurl;
    }
}
