package com.example.recipesapp.Api.Models.Models.FridgeProducts;

public class FridgeProduct implements Comparable<FridgeProduct> {
    public Integer id;
    public String username;
    public String productName;
    public String expirationDate;

    public FridgeProduct() {
    }

    public FridgeProduct(String productName, String expirationDate) {
        this.productName = productName;
        this.expirationDate = expirationDate;
    }

    @Override
    public int compareTo(FridgeProduct o) {
        return expirationDate.compareTo(o.expirationDate);
    }
}
