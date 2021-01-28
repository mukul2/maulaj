package com.telemedicine.maulaji.model;

public class CartItemsModel {
    String name;
    String id;
    int quantity;

    public CartItemsModel(String name, String id, int quantity) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
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
