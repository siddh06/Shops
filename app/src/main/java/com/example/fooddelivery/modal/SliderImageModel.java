package com.example.fooddelivery.modal;

public class SliderImageModel {
    private String image;
    private String title;

    public SliderImageModel(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public SliderImageModel setImage(String image) {
        this.image = image;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SliderImageModel setTitle(String title) {
        this.title = title;
        return this;
    }
}
