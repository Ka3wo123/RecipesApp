package com.example.recipesapp.Libraries;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Product implements Comparable<Product> {
    private String name;
    private String expirationDate;


    public Product(String name, String expirationDate) {
        this.name = name;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public String getExpirationDate() {
        return expirationDate;
    }


    @Override
    public int compareTo(Product o) {
        return expirationDate.compareTo(o.expirationDate);
    }
}
