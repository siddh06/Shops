package com.example.fooddelivery.modal;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetailsModal {
    private int id;
    private String title;
    private String image;
    private double price;
    private String description;
    private String category;
    @SerializedName("rating")
    private ProductRating ratings;

    public ProductDetailsModal(int id, String title, String image, double price, String description, ProductRating ratings) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.price = price;
        this.description = description;
        this.ratings = ratings;
    }

    public int getId() {
        return id;
    }

    public ProductDetailsModal setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ProductDetailsModal setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImage() {
        return image;
    }

    public ProductDetailsModal setImage(String image) {
        this.image = image;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ProductDetailsModal setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDetailsModal setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ProductDetailsModal setCategory(String category) {
        this.category = category;
        return this;
    }

    public ProductRating getRatings() {
        return ratings;
    }

    public ProductDetailsModal setRatings(ProductRating ratings) {
        this.ratings = ratings;
        return this;
    }
}
