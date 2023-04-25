package com.example.recipesapp.Api;

import android.content.Context;
import android.util.Log;

import com.example.recipesapp.Api.Listeners.FridgeProductListener;
import com.example.recipesapp.Api.Listeners.RecipeDetailsListener;
import com.example.recipesapp.Api.Listeners.RecipesFoundListener;
import com.example.recipesapp.Api.Listeners.RecipesFromFridgeListener;
import com.example.recipesapp.Api.Listeners.WineMatchListener;
import com.example.recipesapp.Api.Models.Models.AccountDetails.Account;
import com.example.recipesapp.Api.Models.Models.FridgeProducts.FridgeProduct;
import com.example.recipesapp.Api.Models.Models.ListOfRecipes.Recipes;
import com.example.recipesapp.Api.Models.Models.RecipeDetails.RecipeDetailsResponse;
import com.example.recipesapp.Api.Models.Models.RecipesFromFridge.RecipeFromFridge;
import com.example.recipesapp.Api.Models.Models.Wine.WineMatches;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {

    Context context;
    ApiKey apiKey = new ApiKey();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Retrofit databaseRetrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.2.58:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

//    public void validateAccount(String username, char[] password) {
//        CallAccount accountCall = databaseRetrofit.create(CallAccount.class);
//        Call<Boolean> call = accountCall.validateCredentials(username, password);
//        call.enqueue(new Callback<Boolean>() {
//            @Override
//            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
//                Boolean isValid = Boolean.TRUE.equals(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<Boolean> call, Throwable t) {
//
//            }
//        });
//
//    }
//
//    private interface CallAccount {
//        @GET("account/validate")
//        Call<Boolean> validateCredentials(@Query("username") String username, @Query("password") char[] password);
//    }

    public void getFridgeProducts(FridgeProductListener listener, String username) {
        CallFridgeProduct fridgeProduct = databaseRetrofit.create(CallFridgeProduct.class);
        Call<List<FridgeProduct>> call = fridgeProduct.callFridgeProducts(username);
        call.enqueue(new Callback<List<FridgeProduct>>() {
            @Override
            public void onResponse(retrofit2.Call<List<FridgeProduct>> call, Response<List<FridgeProduct>> response) {
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(retrofit2.Call<List<FridgeProduct>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void addToFridge(String username, String productName, String expirationDate) {
        CallFridgeProduct fridgeProduct = databaseRetrofit.create(CallFridgeProduct.class);
        Call<FridgeProduct> fridgeProductCall = fridgeProduct.addProductToFridge(username, productName, expirationDate);
        fridgeProductCall.enqueue(new Callback<FridgeProduct>() {
            @Override
            public void onResponse(Call<FridgeProduct> call, Response<FridgeProduct> response) {
                Log.v("onSaveProductFridge", "Product saved");
            }

            @Override
            public void onFailure(Call<FridgeProduct> call, Throwable t) {
                Log.v("onFailureSaveProductFridge", "Failed while saving: " + t.getMessage());
            }
        });
    }

    public void deleteFromFridge(Integer id) {
        CallFridgeProduct fridgeProduct = databaseRetrofit.create(CallFridgeProduct.class);
        Call<FridgeProduct> fridgeProductCall = fridgeProduct.deleteProduct(id);
        fridgeProductCall.enqueue(new Callback<FridgeProduct>() {
            @Override
            public void onResponse(Call<FridgeProduct> call, Response<FridgeProduct> response) {
                Log.v("onDeletedProductFridge", "Deleted from fridge");
            }

            @Override
            public void onFailure(Call<FridgeProduct> call, Throwable t) {
                Log.v("onFailureDeleteProductFridge", "Error while deleting: " + t.getMessage());
            }
        });
    }

    private interface CallFridgeProduct {
        @GET("fridge/account/{username}")
        Call<List<FridgeProduct>> callFridgeProducts(@Path("username") String username);

        @FormUrlEncoded
        @POST("fridge")
        Call<FridgeProduct> addProductToFridge(@Field("username") String username, @Field("productName") String productName, @Field("expirationDate") String expirationDate);

        @DELETE("fridge/{id}")
        Call<FridgeProduct> deleteProduct(@Path("id") Integer id);
    }

    public void getPairedWine(WineMatchListener listener, String food) {
        CallPaireWine callPaireWine = retrofit.create(CallPaireWine.class);
        Call<WineMatches> call = callPaireWine.paireWine(apiKey.getApiKey(), food);
        call.enqueue(new Callback<WineMatches>() {
            @Override
            public void onResponse(Call<WineMatches> call, Response<WineMatches> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<WineMatches> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private interface CallPaireWine {
        @GET("food/wine/pairing")
        Call<WineMatches> paireWine(
                @Query("apiKey") String apiKey,
                @Query("food") String food
        );
    }

    public void getFoundRecipes(RecipesFoundListener listener, String query, int number) {
        CallFindRecipes callFindRecipes = retrofit.create(CallFindRecipes.class);
        Call<Recipes> call = callFindRecipes.findRecipes(query, number, apiKey.getApiKey());
        call.enqueue(new Callback<Recipes>() {
            @Override
            public void onResponse(Call<Recipes> call, Response<Recipes> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<Recipes> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private interface CallFindRecipes {
        @GET("recipes/complexSearch")
        Call<Recipes> findRecipes(
                @Query("query") String query,
                @Query("number") int number,
                @Query("apiKey") String apiKey
        );
    }

    public void getRecipeDetails(RecipeDetailsListener listener, int id) {
        CallRecipeDetails callRecipeDetails = retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailsResponse> call = callRecipeDetails.callRecipeDetails(id, apiKey.getApiKey());
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private interface CallRecipeDetails {
        @GET("recipes/{id}/information")
        Call<RecipeDetailsResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }

    public void getRecipesFromFridge(RecipesFromFridgeListener listener, String ingredients, int number, boolean ignorePantry, int ranking) {
        CallRecipesFromFridge callRecipesFromFridge = retrofit.create(CallRecipesFromFridge.class);
        Call<List<RecipeFromFridge>> call = callRecipesFromFridge.callRecipesFromFridge(apiKey.getApiKey(), ingredients, number, ignorePantry, ranking);
        call.enqueue(new Callback<List<RecipeFromFridge>>() {
            @Override
            public void onResponse(Call<List<RecipeFromFridge>> call, Response<List<RecipeFromFridge>> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<RecipeFromFridge>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private interface CallRecipesFromFridge {
        @GET("recipes/findByIngredients")
        Call<List<RecipeFromFridge>> callRecipesFromFridge(
                @Query("apiKey") String apiKey,
                @Query("ingredients") String ingredients,
                @Query("number") int number,
                @Query("ignorePantry") boolean ignorePantry,
                @Query("ranking") int ranking
        );
    }
}
