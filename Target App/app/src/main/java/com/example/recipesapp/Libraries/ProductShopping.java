package com.example.recipesapp.Libraries;

public class ProductShopping {
    private String name;
    private String quantity;

    public ProductShopping(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }
}
