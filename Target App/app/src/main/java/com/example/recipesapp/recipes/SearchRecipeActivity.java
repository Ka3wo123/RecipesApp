package com.example.recipesapp.recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.recipesapp.R;

public class SearchRecipeActivity extends AppCompatActivity {

    ImageButton back;
    ImageView searchRecipes;
    EditText nameOfFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipe);

        nameOfFood = findViewById(R.id.nameOfFoodForRecipes);
        back = findViewById(R.id.back3);
        back.setOnClickListener(v -> {
            finish();
        });
        searchRecipes = findViewById(R.id.searchRecipes);
        searchRecipes.setOnClickListener(v ->{
            goToFoundRecipes(nameOfFood.getText().toString());
        });

    }


    void goToFoundRecipes(String query){
        Intent intent = new Intent(getApplicationContext(), FoundRecipesActivity.class);
        intent.putExtra("query", query);
        startActivity(intent);
    }
}