package com.example.recipesapp.Libraries;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private Connection connection;
    public Connection connectDB(){
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://195.150.230.208:5432/2023_wolek_dawid?user=2023_wolek_dawid&password=35244&ssl=true");
            return connection;
        } catch (SQLException e){
            Log.v("ERROR_CON", "SQL: " + e.getMessage()
                    + " state: " + e.getSQLState()
                    + " code: " + e.getErrorCode()
                    + " next exception: " + e.getNextException());
            e.printStackTrace();
        }
        return null;
    }
}
