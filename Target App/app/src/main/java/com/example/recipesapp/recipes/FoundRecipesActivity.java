package com.example.recipesapp.recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipesapp.Api.Listeners.RecipesFoundListener;
import com.example.recipesapp.Api.Models.Models.ListOfRecipes.Recipes;
import com.example.recipesapp.Api.RequestManager;
import com.example.recipesapp.R;

public class FoundRecipesActivity extends AppCompatActivity {

    ProgressDialog dialog;
    RequestManager manager;
    String query;

    //TODO do usuniecia test
    TextView textView16;
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

        textView16 = findViewById(R.id.test);
        textView16.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent test = new Intent(FoundRecipesActivity.this, ChosenRecipeActivity.class);
                test.putExtra("id", 716429);
                startActivity(test);
            }
        });

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