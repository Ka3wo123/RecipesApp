package com.example.recipesapp.Libraries;

public class GlobalData {

    private static GlobalData instance;
    private static String name;
    private static String username;
    private static String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        GlobalData.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        GlobalData.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        GlobalData.password = password;
    }

    public static GlobalData getInstance() {
        if (instance == null) {
            instance = new GlobalData();
        }
        return instance;
    }
}
