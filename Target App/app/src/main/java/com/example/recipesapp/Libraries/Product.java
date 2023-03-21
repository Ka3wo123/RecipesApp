package com.example.recipesapp.Libraries;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Product implements Comparable<Product> {
    private String name;
    private String expirationDate;
    private SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();


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
        int nameComparison = this.name.compareTo(o.getName());
        if (nameComparison != 0) {
            return nameComparison;
        }
        return this.expirationDate.compareTo(o.getExpirationDate());
    }
}
