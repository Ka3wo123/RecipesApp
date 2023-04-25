package com.example.recipesapp.Libraries;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipesapp.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ShoppingHolder> {

    private Context context;
    private List<ProductShopping> products;
    private int lastPosition = -1;

    public ShoppingAdapter(Context context, List<ProductShopping> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ShoppingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_shopping_item, parent, false);
        return new ShoppingHolder(view, view.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingHolder holder, int position) {
        ProductShopping product = products.get(position);
        holder.name.setText(product.getName());
        holder.quantity.setText(product.getQuantity());

        if(holder.getAdapterPosition() > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            holder.cardView.startAnimation(animation);
            lastPosition = holder.getAdapterPosition();
        }


    }

    public void deleteItem(int position) {
        products.remove(position);
        notifyItemRemoved(position);
        Toast.makeText(context, "Product removed from shopping list", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ShoppingHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView quantity;
        private final CardView cardView;
        private final ImageButton deleteBtn;
        private ProductShopping productShopping;


        ShoppingHolder(@NonNull View itemView, Context context) {
            super(itemView);
            name = itemView.findViewById(R.id.productName);
            quantity = itemView.findViewById(R.id.quantity);
            cardView = itemView.findViewById(R.id.cardViewShopping);
            deleteBtn = itemView.findViewById(R.id.deleteProductShopping);

            deleteBtn.setOnClickListener(v -> {
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
            });

        }
    }

}

