package com.example.recipesapp.Api.Listeners;


import com.example.recipesapp.Api.Models.Models.RecipesFromFridge.RecipesFromFridge;

public interface RecipesFromFridgeListener {
    void didFetch(RecipesFromFridge recipesFromFridge, String message);
    void didError(String error);
}
