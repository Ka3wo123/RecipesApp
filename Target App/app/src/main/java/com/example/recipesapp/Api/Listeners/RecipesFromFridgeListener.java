package com.example.recipesapp.Api.Listeners;


import com.example.recipesapp.Api.Models.Models.RecipesFromFridge.RecipeFromFridge;
import com.example.recipesapp.Api.Models.Models.RecipesFromFridge.RecipesFromFridge;

import java.util.List;

public interface RecipesFromFridgeListener {
    void didFetch(List<RecipeFromFridge> recipeFromFridge, String message);
    void didError(String error);
}
