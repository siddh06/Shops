package com.example.fooddelivery.modal;

import com.google.gson.annotations.SerializedName;

public class Product {
    private int id;
    private String title;
    private String image;
    private double price;
    private String description;
    private String category;
    @SerializedName("rating")
    private ProductRating ratings;

    public Product(int id, String title, String image, double price, String description, String category, ProductRating ratings) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.price = price;
        this.description = description;
        this.category = category;
        this.ratings = ratings;
    }

    public String getImage() {
        return image;
    }

    public Product setImage(String image) {
        this.image = image;
        return this;
    }

    public ProductRating getRatings() {
        return ratings;
    }

    public Product setRatings(ProductRating ratings) {
        this.ratings = ratings;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Product setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getId() {
        return id;
    }

    public Product setId(int id) {
        this.id = id;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Product setCategory(String category) {
        this.category = category;
        return this;
    }
}
