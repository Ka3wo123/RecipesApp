package com.example.recipesapp.mainMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.recipesapp.Libraries.GlobalData;
import com.example.recipesapp.R;
import com.example.recipesapp.authentication.LoginActivity;
import com.example.recipesapp.authentication.RegistrationActivity;
import com.example.recipesapp.fridge.FridgeActivity;
import com.example.recipesapp.recipes.FoundRecipesActivity;
import com.example.recipesapp.recipes.SearchRecipeActivity;
import com.example.recipesapp.shopping.ShoppingActivity;
import com.example.recipesapp.wine.WineActivity;

public class MainMenuActivity extends AppCompatActivity {

    Button toFridge, toAvailable, toRecipes, toWine, toList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu);
        toFridge = findViewById(R.id.toFridge);
        toAvailable = findViewById(R.id.toAvaiable);
        toRecipes = findViewById(R.id.toRecipes);
        toWine = findViewById(R.id.toWine);
        toList = findViewById(R.id.toList);

        bindActivity(toFridge, FridgeActivity.class);
        bindActivity(toAvailable, FoundRecipesActivity.class);
        bindActivity(toRecipes, SearchRecipeActivity.class);
        bindActivity(toWine, WineActivity.class);
        bindActivity(toList, ShoppingActivity.class);


    }

    void bindActivity (Button button, Class<?> activity){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, activity);
                startActivity(intent);
            }
        });
    }
}