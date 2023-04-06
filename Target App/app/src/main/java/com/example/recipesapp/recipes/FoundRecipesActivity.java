package com.example.recipesapp.recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.recipesapp.Api.Listeners.RecipesFoundListener;
import com.example.recipesapp.Api.Listeners.WineMatchListener;
import com.example.recipesapp.Api.Models.Models.ListOfRecipes.Recipes;
import com.example.recipesapp.Api.Models.Models.Wine.WineMatches;
import com.example.recipesapp.Api.RequestManager;
import com.example.recipesapp.R;
import com.example.recipesapp.wine.WineActivity;

public class FoundRecipesActivity extends AppCompatActivity {

    ProgressDialog dialog;
    RequestManager manager;
    String query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_recipe);

        Intent intent = getIntent();
        query = intent.getStringExtra("query");

        dialog = new ProgressDialog(this);
        manager = new RequestManager(this);

        manager.getFoundRecipes(recipesFoundListener, query, "15");
        dialog.show();

    }

    private final RecipesFoundListener recipesFoundListener = new RecipesFoundListener() {
        @Override
        public void didFetch(Recipes recipes, String message) {
            toast(recipes);
            dialog.dismiss();
        }

        @Override
        public void didError(String error) {
            Toast.makeText(FoundRecipesActivity.this, error, Toast.LENGTH_SHORT).show();

        }
    };
    void toast(Recipes recipes){
        Toast.makeText(FoundRecipesActivity.this, Integer.toString(recipes.results.size()), Toast.LENGTH_SHORT).show();
    }
}