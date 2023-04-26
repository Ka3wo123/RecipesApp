package com.example.recipesapp.Api.Listeners;

import com.example.recipesapp.Api.Models.Models.ShoppingProducts.ShoppingProduct;

import java.util.List;

public interface ShoppingListener {
    void didFetch(List<ShoppingProduct> shoppingProducts, String message);
    void didError(String error);
}
