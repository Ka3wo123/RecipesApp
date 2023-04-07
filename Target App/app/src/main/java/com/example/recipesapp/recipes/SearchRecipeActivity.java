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
    ImageButton cuisineBritish, cuisineGreek, cuisineIndian, cuisineItalian, cuisineJapanese, cuisineMexican, cuisineNordic, cuisineThai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipe);


        findImageViews();
        setOnClickListeners();

    }


    void goToFoundRecipes(String query){
        Intent intent = new Intent(getApplicationContext(), FoundRecipesActivity.class);
        intent.putExtra("query", query);
        startActivity(intent);
    }
    void findImageViews(){
        nameOfFood = findViewById(R.id.nameOfFoodForRecipes);
        searchRecipes = findViewById(R.id.searchRecipes);
        back = findViewById(R.id.back3);
        cuisineBritish = findViewById(R.id.cuisineBritish);
        cuisineGreek = findViewById(R.id.cuisineGreek);
        cuisineIndian = findViewById(R.id.cuisineIndian);
        cuisineItalian = findViewById(R.id.cuisineItalian);
        cuisineJapanese = findViewById(R.id.cuisineJapanese);
        cuisineMexican = findViewById(R.id.cuisineMexican);
        cuisineNordic = findViewById(R.id.cuisineNordic);
        cuisineThai = findViewById(R.id.cuisineThai);
    }
    void setOnClickListeners(){
        back.setOnClickListener(v -> {
            finish();
        });

        searchRecipes.setOnClickListener(v ->{
            goToFoundRecipes(nameOfFood.getText().toString());
        });

        cuisineBritish.setOnClickListener(v -> {
            goToFoundRecipes("british");
        });
        cuisineGreek.setOnClickListener(v -> {
            goToFoundRecipes("greek");
        });
        cuisineIndian.setOnClickListener(v -> {
            goToFoundRecipes("indian");
        });
        cuisineItalian.setOnClickListener(v -> {
            goToFoundRecipes("italian");
        });
        cuisineJapanese.setOnClickListener(v -> {
            goToFoundRecipes("japanese");
        });
        cuisineMexican.setOnClickListener(v -> {
            goToFoundRecipes("mexican");
        });
        cuisineNordic.setOnClickListener(v -> {
            goToFoundRecipes("nordic");
        });
        cuisineThai.setOnClickListener(v -> {
            goToFoundRecipes("thai");
        });
    }
}