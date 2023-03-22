package com.example.fooddelivery.modal;

public class ProductRating {
    private String rate;
    private String count;

    public String getRate() {
        return rate;
    }

    public ProductRating setRate(String rate) {
        this.rate = rate;
        return this;
    }

    public String getCount() {
        return count;
    }

    public ProductRating setCount(String count) {
        this.count = count;
        return this;
    }
}
