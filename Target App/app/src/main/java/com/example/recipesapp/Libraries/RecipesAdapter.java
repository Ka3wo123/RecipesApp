package com.example.recipesapp.Libraries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipesapp.Api.Models.Models.ListOfRecipes.Recipe;
import com.example.recipesapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeHolder>{

    private Context context;
    private ArrayList<Recipe> recipes;

    public RecipesAdapter(Context context, ArrayList<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }
    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_item, parent, false);
        return new RecipeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.recipeName.setText(recipe.title);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/" + recipe.image).into(holder.recipeImage);

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static class RecipeHolder extends RecyclerView.ViewHolder {
        private final ImageView recipeImage;
        private final TextView recipeName;

        RecipeHolder(View itemView) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.recipeImage);
            recipeName = itemView.findViewById(R.id.recipeName);

        }
    }
}
