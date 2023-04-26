package com.example.recipesapp.Api.Models.Models.FridgeProducts;

import java.time.LocalDate;
import java.util.Date;

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

    public FridgeProduct(String username, String productName, String expirationDate) {
        this.username = username;
        this.productName = productName;
        this.expirationDate = expirationDate;
    }


    @Override
    public int compareTo(FridgeProduct o) {
        return expirationDate.compareTo(o.expirationDate);
    }
}
