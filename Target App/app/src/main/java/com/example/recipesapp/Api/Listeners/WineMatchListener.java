package com.example.recipesapp.Api.Listeners;

import com.example.recipesapp.Api.Models.Wine.WineMatches;

public interface WineMatchListener {

    void didFetch(WineMatches wineMatches, String message);
    void didError(String error);
}
