package com.example.recipesapp.Libraries;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipesapp.Api.Models.Models.ListOfRecipes.Recipe;
import com.example.recipesapp.R;
import com.example.recipesapp.recipes.ChosenRecipeActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeHolder>{

    private final Context context;
    private final List<Recipe> recipes;

    public RecipesAdapter(Context context, List<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }
    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_item, parent, false);
        return new RecipeHolder(view, view.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.recipeName.setText(recipe.title);
        holder.recipeName.setSelected(true);
        Picasso.get().load("https://spoonacular.com/recipeImages/" + recipe.id + "-636x393.jpg").into(holder.recipeImage);
        holder.recipeImage.setSelected(true);

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class RecipeHolder extends RecyclerView.ViewHolder {
        private final ImageView recipeImage;
        private final TextView recipeName;
        private Recipe recipe;

        RecipeHolder(@NonNull View itemView, Context itemContext) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.recipeImage);
            recipeName = itemView.findViewById(R.id.recipeName);
            itemView.setOnClickListener(v -> {
                recipe = recipes.get(getAdapterPosition());
                Intent intent = new Intent(itemContext, ChosenRecipeActivity.class);
                intent.putExtra("id", recipe.id);
                itemContext.startActivity(intent);

            });

        }
    }
}
