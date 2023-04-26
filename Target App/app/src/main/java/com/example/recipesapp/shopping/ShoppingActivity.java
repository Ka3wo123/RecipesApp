package com.example.recipesapp.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.recipesapp.Api.Listeners.ShoppingListener;
import com.example.recipesapp.Api.Models.Models.FridgeProducts.FridgeProduct;
import com.example.recipesapp.Api.Models.Models.ShoppingProducts.ShoppingProduct;
import com.example.recipesapp.Api.Models.Models.ShoppingProducts.ShoppingProductsList;
import com.example.recipesapp.Api.RequestManager;
import com.example.recipesapp.Libraries.ShoppingAdapter;
import com.example.recipesapp.R;

import java.util.ArrayList;
import java.util.List;

public class ShoppingActivity extends AppCompatActivity implements AddNewShoppingPopup.AddNewListener {
    private ProgressDialog progressDialog;
    private ShoppingAdapter adapter;
    private RecyclerView recyclerView;
    private ImageButton back;
    private Button addNew, addToFridgeBtn;
    private RequestManager requestManager;
    private String username;
    private ShoppingProductsList productsList = new ShoppingProductsList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        back = findViewById(R.id.backShopping);
        addNew = findViewById(R.id.addNew);
        addToFridgeBtn = findViewById(R.id.addAllToFridge);
        recyclerView = findViewById(R.id.recyclerViewShopping);
        Intent intent = getIntent();

        username = intent.getStringExtra("username");

        requestManager = new RequestManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.show();

        requestManager.getShoppingListProducts(shoppingListener, username);

        back.setOnClickListener(v -> finish());
        addNew.setOnClickListener(v -> {
            AddNewShoppingPopup popup = new AddNewShoppingPopup();
            popup.show(getSupportFragmentManager(), "Popup shopping");
        });

        //addToFridgeBtn.setOnClickListener(v -> addToFridge());

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

//    private void addToFridge() {
//        for (ShoppingProduct product : productsList.shoppingProductsList) {
//            FridgeProduct fridgeProduct = new FridgeProduct(product.id,
//                    product.username,
//                    product.productName,
//                    product.)
//            requestManager.addToFridge(product);
//            requestManager.deleteFromShoppingList(product.id);
//        }
//        adapter.notifyItemRangeRemoved(0, productsList.shoppingProductsList.size());
//        Toast.makeText(this, "Products added to fridge", Toast.LENGTH_SHORT).show();
//    }

    private final ShoppingListener shoppingListener = new ShoppingListener() {
        @Override
        public void didFetch(List<ShoppingProduct> shoppingProducts, String message) {

            progressDialog.dismiss();
            recyclerView.setLayoutManager(new LinearLayoutManager(ShoppingActivity.this));

            productsList.shoppingProductsList = new ArrayList<>();

            for(ShoppingProduct product : shoppingProducts) {
                ShoppingProduct shoppingProduct = new ShoppingProduct();
                shoppingProduct.id = product.id;
                shoppingProduct.productName = product.productName;
                shoppingProduct.quantity = product.quantity;

                productsList.shoppingProductsList.add(shoppingProduct);
            }

            adapter = new ShoppingAdapter(ShoppingActivity.this, productsList.shoppingProductsList);
            recyclerView.setAdapter(adapter);
        }

        @Override
        public void didError(String error) {

        }
    };

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            requestManager.deleteFromShoppingList(productsList.shoppingProductsList.get(position).id);
            adapter.deleteItem(position);

        }
    };


    @Override
    public void apply(String name, Integer quantity) {
        requestManager.addToShoppingList(new ShoppingProduct(username, name, quantity));
        productsList.shoppingProductsList.add(new ShoppingProduct(name, quantity));
        adapter.notifyItemInserted(productsList.shoppingProductsList.size() - 1);
        recyclerView.scrollToPosition(productsList.shoppingProductsList.size() - 1);
    }
}