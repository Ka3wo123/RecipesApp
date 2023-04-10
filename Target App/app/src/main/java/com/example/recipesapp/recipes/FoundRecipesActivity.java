package com.example.recipesapp.recipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipesapp.Api.Listeners.RecipesFoundListener;
import com.example.recipesapp.Api.Models.Models.ListOfRecipes.Recipe;
import com.example.recipesapp.Api.Models.Models.ListOfRecipes.Recipes;
import com.example.recipesapp.Api.RequestManager;
import com.example.recipesapp.Libraries.RecipesAdapter;
import com.example.recipesapp.R;

import java.util.ArrayList;
import java.util.List;

public class FoundRecipesActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private RequestManager manager;
    private String query;
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

        manager = new RequestManager(this);
        manager.getFoundRecipes(recipesFoundListener, query, number);

        dialog = new ProgressDialog(this);

        dialog.show();

    }

    private final RecipesFoundListener recipesFoundListener = new RecipesFoundListener() {
        @Override
        public void didFetch(Recipes recipes, String message) {
            dialog.dismiss();
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(FoundRecipesActivity.this, RecyclerView.VERTICAL, false));

            recipesAdapter = new RecipesAdapter(FoundRecipesActivity.this, recipes.results);

            recyclerView.setAdapter(recipesAdapter);

            if(recipes.results.size() == 0) {
                Toast.makeText(FoundRecipesActivity.this, "No recipes found", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        @Override
        public void didError(String error) {
            Toast.makeText(FoundRecipesActivity.this, "Error" + error, Toast.LENGTH_SHORT).show();

        }
    };

}