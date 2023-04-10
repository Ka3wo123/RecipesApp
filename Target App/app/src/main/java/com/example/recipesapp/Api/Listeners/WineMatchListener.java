package com.example.recipesapp.Api.Listeners;

import com.example.recipesapp.Api.models.Wine.WineMatches;

public interface WineMatchListener {

    void didFetch(WineMatches wineMatches, String message);
    void didError(String error);
}
