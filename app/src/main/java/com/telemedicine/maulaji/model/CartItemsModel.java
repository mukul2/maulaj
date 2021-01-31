package com.telemedicine.maulaji.model;

public class CartItemsModel {
    String name;
    String image;
    String id;
    int quantity;
    float price;

    public CartItemsModel(String name, String image, String id, int quantity, float price) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CartItemsModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
