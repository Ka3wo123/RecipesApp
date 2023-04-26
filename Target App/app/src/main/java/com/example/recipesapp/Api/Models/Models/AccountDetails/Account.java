package com.example.recipesapp.Api.Models.Models.AccountDetails;

public class Account {
    public String name;
    public String username;
    public String password;

    public Account() {
    }

    public Account(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
