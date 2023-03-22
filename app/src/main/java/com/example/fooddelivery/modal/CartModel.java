package com.example.fooddelivery.modal;

public class CartModel {
    private int id;
    private String title;
    private String image;
    private double price;

    public CartModel(int id, String title, String image, double price) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public CartModel setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CartModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImage() {
        return image;
    }

    public CartModel setImage(String image) {
        this.image = image;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public CartModel setPrice(double price) {
        this.price = price;
        return this;
    }
}
