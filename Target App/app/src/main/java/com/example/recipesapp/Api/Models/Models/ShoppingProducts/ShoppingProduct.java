package com.example.recipesapp.Api.Models.Models.ShoppingProducts;


public class ShoppingProduct {
    public Integer id;
    public String username;
    public String productName;
    public Integer quantity;

    public ShoppingProduct() {
    }

    public ShoppingProduct(String productName, Integer quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public ShoppingProduct(String username, String productName, Integer quantity) {
        this.username = username;
        this.productName = productName;
        this.quantity = quantity;
    }


}
