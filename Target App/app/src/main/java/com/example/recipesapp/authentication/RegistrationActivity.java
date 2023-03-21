package com.example.recipesapp.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.recipesapp.R;

public class RegistrationActivity extends AppCompatActivity {

    Button returnButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        returnButton = findViewById(R.id.returnToLogin);
        bindReturnToButton(returnButton);
    }

    void bindReturnToButton(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}