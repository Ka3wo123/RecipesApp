package com.example.recipesapp.recipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipesapp.Api.Listeners.RecipeDetailsListener;
import com.example.recipesapp.Api.Models.Models.RecipeDetails.RecipeDetailsResponse;
import com.example.recipesapp.Api.RequestManager;
import com.example.recipesapp.R;
import com.squareup.picasso.Picasso;

public class ChosenRecipeActivity extends AppCompatActivity {

    //TODO Odczytać id z listenera przy pomocy intent getstring
    int id;

    TextView meal_name, meal_source, meal_summary;
    RecyclerView recycler_ingredients;
    ImageView meal_image;
    RequestManager requestManager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_recipe);
        findViews();

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 716429);

        requestManager = new RequestManager(this);
        requestManager.getRecipeDetails(recipeDetailsListener, id);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading details...");
        dialog.show();

    }

    private void findViews() {
        meal_name = findViewById(R.id.meal_name);
        meal_source = findViewById(R.id.meal_source);
        meal_summary = findViewById(R.id.meal_summary);
        recycler_ingredients = findViewById(R.id.recycler_ingredients);
        meal_image = findViewById(R.id.meal_image);

    }

    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse recipeDetailsResponse, String message) {
            dialog.dismiss();
            meal_name.setText(recipeDetailsResponse.title);
            meal_source.setText(recipeDetailsResponse.sourceName);
            meal_summary.setText(recipeDetailsResponse.summary);
            Picasso.get().load(recipeDetailsResponse.image).into(meal_image);

            recycler_ingredients.setHasFixedSize(true);
            recycler_ingredients.setLayoutManager(new LinearLayoutManager(ChosenRecipeActivity.this, LinearLayoutManager.HORIZONTAL, false));
            ingredientsAdapter = new IngredientsAdapter(ChosenRecipeActivity.this, recipeDetailsResponse.extendedIngredients);
            recycler_ingredients.setAdapter(ingredientsAdapter);

        }

        @Override
        public void didError(String error) {
            Toast.makeText(ChosenRecipeActivity.this, error, Toast.LENGTH_SHORT).show();
        }
    };

}