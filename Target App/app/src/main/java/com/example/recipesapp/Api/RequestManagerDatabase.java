package com.example.recipesapp.Api;

import android.content.Context;
import android.util.Log;

import com.example.recipesapp.Api.Listeners.FridgeProductListener;
import com.example.recipesapp.Api.Listeners.ShoppingListener;
import com.example.recipesapp.Api.Models.Models.FridgeProducts.FridgeProduct;
import com.example.recipesapp.Api.Models.Models.ShoppingProducts.ShoppingProduct;
import com.example.recipesapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class RequestManagerDatabase {
    private Context context;
    private final Retrofit databaseRetrofit;

    public RequestManagerDatabase(Context context) {
        this.context = context;
        databaseRetrofit = new Retrofit.Builder()
                .baseUrl(context.getResources().getString(R.string.server_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getShoppingListProducts(ShoppingListener listener, String username) {
        CallShoppingProduct shoppingProduct = databaseRetrofit.create(CallShoppingProduct.class);
        Call<List<ShoppingProduct>> call = shoppingProduct.callShoppingProducts(username);
        call.enqueue(new Callback<List<ShoppingProduct>>() {
            @Override
            public void onResponse(Call<List<ShoppingProduct>> call, Response<List<ShoppingProduct>> response) {
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<ShoppingProduct>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void addToShoppingList(ShoppingProduct shoppingProduct) {
        CallShoppingProduct shoppingProductcall = databaseRetrofit.create(CallShoppingProduct.class);
        Call<ShoppingProduct> call = shoppingProductcall.addProductToShoppingList(shoppingProduct);
        call.enqueue(new Callback<ShoppingProduct>() {
            @Override
            public void onResponse(Call<ShoppingProduct> call, Response<ShoppingProduct> response) {
                Log.v("onSaveProductShoppingProduct", "Shopping product saved, code: " + response.code());
            }

            @Override
            public void onFailure(Call<ShoppingProduct> call, Throwable t) {
                Log.v("onFailureSaveShoppingProduct", "Failed while saving: " + t.getMessage());
            }
        });
    }

    public void deleteFromShoppingList(Integer id) {
        CallShoppingProduct shoppingProduct = databaseRetrofit.create(CallShoppingProduct.class);
        Call<ShoppingProduct> call = shoppingProduct.deleteShoppingProduct(id);
        call.enqueue(new Callback<ShoppingProduct>() {
            @Override
            public void onResponse(Call<ShoppingProduct> call, Response<ShoppingProduct> response) {
                Log.v("onDeletedShoppingProduct", "Deleted from shopping list");
            }

            @Override
            public void onFailure(Call<ShoppingProduct> call, Throwable t) {
                Log.v("onFailureDeleteShoppingProduct", "Error while deleting: " + t.getMessage());
            }
        });
    }

    private interface CallShoppingProduct {
        @GET("shoppingList/account/{username}")
        Call<List<ShoppingProduct>> callShoppingProducts(@Path("username") String username);

        @POST("/shoppingList")
        Call<ShoppingProduct> addProductToShoppingList(@Body ShoppingProduct shoppingProduct);

        @DELETE("/shoppingProduct/{id}")
        Call<ShoppingProduct> deleteShoppingProduct(@Path("id") Integer id);
    }

    public void getFridgeProducts(FridgeProductListener listener, String username) {
        CallFridgeProduct fridgeProduct = databaseRetrofit.create(CallFridgeProduct.class);
        Call<List<FridgeProduct>> call = fridgeProduct.callFridgeProducts(username);
        call.enqueue(new Callback<List<FridgeProduct>>() {
            @Override
            public void onResponse(Call<List<FridgeProduct>> call, Response<List<FridgeProduct>> response) {
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<FridgeProduct>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void addToFridge(FridgeProduct product) {
        CallFridgeProduct fridgeProductcall = databaseRetrofit.create(CallFridgeProduct.class);
        Call<FridgeProduct> fridgeProductCall = fridgeProductcall.addProductToFridge(product);
        fridgeProductCall.enqueue(new Callback<FridgeProduct>() {
            @Override
            public void onResponse(Call<FridgeProduct> call, Response<FridgeProduct> response) {
                Log.v("onSaveProductFridge", "Product saved, code: " + response.code());
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
        @GET("/fridge/account/{username}")
        Call<List<FridgeProduct>> callFridgeProducts(@Path("username") String username);

        @POST("/fridge")
        Call<FridgeProduct> addProductToFridge(@Body FridgeProduct fridgeProduct);

        @DELETE("/fridge/{id}")
        Call<FridgeProduct> deleteProduct(@Path("id") Integer id);
    }
}
