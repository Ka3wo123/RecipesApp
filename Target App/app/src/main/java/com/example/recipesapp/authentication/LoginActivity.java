package com.example.recipesapp.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ShareActionProvider;

import com.example.recipesapp.Libraries.DatabaseManager;
import com.example.recipesapp.Libraries.GlobalData;
import com.example.recipesapp.R;
import com.example.recipesapp.mainMenu.MainMenuActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {
    private Connection connection;
    private GlobalData globalData;
    private Button connectBtn;
    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DatabaseManager databaseManager = new DatabaseManager();
        connection = databaseManager.connectDB();

        connectBtn = findViewById(R.id.button);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        connectBtn.setOnClickListener(v -> {
            try {
                LoginActivity.this.selectUserData(username.getText().toString(), password.getText().toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        if(readCheckedFromSP())
            readLoginFromSP();


    }

    public void selectUserData(String usernameLogin, String passwordLogin) throws SQLException {
        try (Statement stm = connection.createStatement()) {
            String query = "select * from User where username = ? and password = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, usernameLogin);
            ps.setString(2, passwordLogin);

            ResultSet resultSet = stm.executeQuery(query);
            String name = resultSet.getString("name");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");

            globalData.setName(name);
            globalData.setUsername(username);
            globalData.setPassword(password);

            //TODO wywoluje funckje do sprawdzenia czy login zapisany
                saveCheckedToSP();

            Intent mainMenu = new Intent(this, MainMenuActivity.class);
            startActivity(mainMenu);

        }
    }

    public void goToRegisterActivity(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void readLoginFromSP(){
        SharedPreferences sharedPreferences = getSharedPreferences("RecipeApp", Context.MODE_PRIVATE);
        String login = sharedPreferences.getString("login","");
        EditText editText = findViewById(R.id.username);
        editText.setText(login);
    }

    boolean readCheckedFromSP(){
        SharedPreferences sharedPreferences = getSharedPreferences("RecipeApp", Context.MODE_PRIVATE);
        String checked = sharedPreferences.getString("checked","false");
        return checked.equals("true");
    }
    public void saveCheckedToSP(){
        CheckBox checked = findViewById(R.id.rememberPassword);
        EditText login = findViewById(R.id.username);
        if(checked.isChecked()){
            SharedPreferences sharedPreferences = getSharedPreferences("RecipeApp", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("checked","true");
            editor.apply();
            editor.putString("login", String.valueOf(login.getText()));
            editor.apply();
        }

    }
}