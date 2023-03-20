package com.example.recipesapp.Libraries;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Product {
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


}
