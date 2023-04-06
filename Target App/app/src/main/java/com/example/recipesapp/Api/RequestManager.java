package com.example.recipesapp.Api;

import android.content.Context;

import com.example.recipesapp.Api.Listeners.RecipesFoundListener;
import com.example.recipesapp.Api.Listeners.WineMatchListener;
import com.example.recipesapp.Api.Models.Models.ListOfRecipes.Recipes;
import com.example.recipesapp.Api.Models.Models.Wine.WineMatches;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {

    Context context;
    ApiKey apiKey = new ApiKey();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context){
        this.context = context;
    }

    public void getPairedWine(WineMatchListener listener, String food){
        CallPaireWine callPaireWine = retrofit.create(CallPaireWine.class);
        Call<WineMatches> call = callPaireWine.paireWine(apiKey.getApiKey(), food);
        call.enqueue(new Callback<WineMatches>() {
            @Override
            public void onResponse(Call<WineMatches> call, Response<WineMatches> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<WineMatches> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private interface CallPaireWine{
        @GET("food/wine/pairing")
        Call<WineMatches> paireWine(
                @Query("apiKey") String apiKey,
                @Query("food") String food
        );
    }
    public void getFoundRecipes(RecipesFoundListener listener, String query, String number){
        CallFindRecipes callFindRecipes = retrofit.create(CallFindRecipes.class);
        Call<Recipes> call = callFindRecipes.findRecipes(apiKey.getApiKey(), query, number);
        call.enqueue(new Callback<Recipes>() {
            @Override
            public void onResponse(Call<Recipes> call, Response<Recipes> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<Recipes> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }
    private interface CallFindRecipes{
        @GET("recipes/complexSearch")
        Call<Recipes> findRecipes(
                @Query("apiKey") String apiKey,
                @Query("query") String query,
                @Query("number") String number
        );
    }
}
