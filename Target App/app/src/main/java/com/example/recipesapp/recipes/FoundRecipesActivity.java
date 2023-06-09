package com.example.recipesapp.recipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.recipesapp.Api.Listeners.RecipesFoundListener;
import com.example.recipesapp.Api.Listeners.RecipesFromFridgeListener;
import com.example.recipesapp.Api.Models.Models.ListOfRecipes.Recipe;
import com.example.recipesapp.Api.Models.Models.ListOfRecipes.Recipes;
import com.example.recipesapp.Api.Models.Models.RecipesFromFridge.RecipeFromFridge;
import com.example.recipesapp.Api.RequestManager;
import com.example.recipesapp.Libraries.RecipesAdapter;
import com.example.recipesapp.R;

import java.util.ArrayList;
import java.util.List;

public class FoundRecipesActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RequestManager manager;
    private String query = "";
    private RecyclerView recyclerView;
    private RecipesAdapter recipesAdapter;
    private ImageButton backBtn;
    int number = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_recipe);

        recyclerView = findViewById(R.id.recyclerViewRecipes);
        backBtn = findViewById(R.id.back4);
        backBtn.setOnClickListener(v -> finish());

        Intent intent = getIntent();
        query = intent.getStringExtra("query");
        boolean fromFridge = intent.getBooleanExtra("fromFridge", false);

        manager = new RequestManager(this);
        if (fromFridge && query != null) {
            manager.getRecipesFromFridge(recipesFromFridgeListener, query, 15, true, 2);
        } else if (query == null) {
            manager.findRecipesRaw(recipesFoundListener);
        } else {
            manager.getFoundRecipes(recipesFoundListener, query, number);
        }


        progressBar = findViewById(R.id.progressBarRecipes);
        progressBar.setVisibility(View.VISIBLE);

    }


    private final RecipesFoundListener recipesFoundListener = new RecipesFoundListener() {
        @Override
        public void didFetch(Recipes recipes, String message) {
            progressBar.setVisibility(View.INVISIBLE);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(FoundRecipesActivity.this, RecyclerView.VERTICAL, false));

            recipesAdapter = new RecipesAdapter(FoundRecipesActivity.this, recipes.results);

            recyclerView.setAdapter(recipesAdapter);

            if (recipes.results.size() == 0) {
                Toast.makeText(FoundRecipesActivity.this, "No recipes found", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        @Override
        public void didError(String error) {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(FoundRecipesActivity.this, "Error" + error, Toast.LENGTH_SHORT).show();

        }
    };
    private final RecipesFromFridgeListener recipesFromFridgeListener = new RecipesFromFridgeListener() {

        @Override
        public void didFetch(List<RecipeFromFridge> recipesFromFridge, String message) {

            progressBar.setVisibility(View.INVISIBLE);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(FoundRecipesActivity.this, RecyclerView.VERTICAL, false));

            Recipes recipes = new Recipes();
            recipes.results = new ArrayList<>();
            for (RecipeFromFridge recipeFromFridge : recipesFromFridge) {
                Recipe recipe = new Recipe();
                recipe.id = recipeFromFridge.id;
                recipe.title = recipeFromFridge.title;
                recipe.image = recipeFromFridge.image;
                recipe.imageType = recipeFromFridge.imageType;
                recipes.results.add(recipe);
            }

            recipesAdapter = new RecipesAdapter(FoundRecipesActivity.this, recipes.results);

            recyclerView.setAdapter(recipesAdapter);

            if (recipesFromFridge.size() == 0) {
                Toast.makeText(FoundRecipesActivity.this, "No recipes found", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        @Override
        public void didError(String error) {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(FoundRecipesActivity.this, "Error" + error, Toast.LENGTH_SHORT).show();

        }
    };

}