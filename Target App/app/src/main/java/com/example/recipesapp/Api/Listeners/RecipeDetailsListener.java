package com.example.recipesapp.Api.Listeners;

import com.example.recipesapp.Api.Models.Models.ListOfRecipes.Recipes;
import com.example.recipesapp.Api.Models.Models.RecipeDetails.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse recipeDetailsResponse, String message);
    void didError(String error);
}
