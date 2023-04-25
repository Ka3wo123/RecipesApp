package com.example.recipesapp.Api.Listeners;

import com.example.recipesapp.Api.Models.Models.FridgeProducts.FridgeProduct;

import java.util.List;

public interface FridgeProductListener {
    void didFetch(List<FridgeProduct> fridgeProductListener, String message);
    void didError(String error);
}
