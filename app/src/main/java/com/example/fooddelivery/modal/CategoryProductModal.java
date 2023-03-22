package com.example.fooddelivery.modal;

import java.util.List;

public class CategoryProductModal {
    private int id;
    private String title;
    private String image;
    private double price;
    private String description;
    private String category;
    private List<ProductRating> ratings;

    public CategoryProductModal(String title, double price, String image) {
        this.title = title;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public CategoryProductModal setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CategoryProductModal setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImage() {
        return image;
    }

    public CategoryProductModal setImage(String image) {
        this.image = image;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public CategoryProductModal setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryProductModal setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public CategoryProductModal setCategory(String category) {
        this.category = category;
        return this;
    }

    public List<ProductRating> getRatings() {
        return ratings;
    }

    public CategoryProductModal setRatings(List<ProductRating> ratings) {
        this.ratings = ratings;
        return this;
    }
}
