package com.example.recipesapp.mainMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.recipesapp.Libraries.GlobalData;
import com.example.recipesapp.R;

public class MainMenuActivity extends AppCompatActivity {

    private TextView username, name, password;
    private final GlobalData globalData = GlobalData.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);

        username.setText(globalData.getUsername());
        name.setText(globalData.getName());
        password.setText(globalData.getPassword());


    }
}