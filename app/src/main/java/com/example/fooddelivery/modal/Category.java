package com.example.fooddelivery.modal;

public class Category {
    private String name;
    private int iconImg;

    public Category(String name, int iconImg) {
        this.name = name;
        this.iconImg = iconImg;
    }

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

    public int getIconImg() {
        return iconImg;
    }

    public Category setIconImg(int iconImg) {
        this.iconImg = iconImg;
        return this;
    }
}
