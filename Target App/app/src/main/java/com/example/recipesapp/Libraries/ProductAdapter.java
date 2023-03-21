package com.example.recipesapp.Libraries;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipesapp.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private Context context;
    private ArrayList<Product> products;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        LocalDate currentDate = LocalDate.now();
        Product product = products.get(position);
        holder.name.setText(product.getName());
        holder.expDate.setText(product.getExpirationDate());

        String[] split = holder.expDate.getText().toString().split("\\.");
        String[] currentDateSplit = currentDate.toString().split("-");

        if (Integer.parseInt(split[2]) - Integer.parseInt(currentDateSplit[0]) < 0) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#FF6161"));
        }
        if(Integer.parseInt(split[2]) - Integer.parseInt(currentDateSplit[0]) == 0 && Integer.parseInt(split[1]) - Integer.parseInt(currentDateSplit[1]) < 0) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#FF6161"));
        }
        if(Integer.parseInt(split[2]) - Integer.parseInt(currentDateSplit[0]) == 0 && Integer.parseInt(split[1]) - Integer.parseInt(currentDateSplit[1]) == 0 && Integer.parseInt(split[0]) - Integer.parseInt(currentDateSplit[2]) < 0) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#FF6161"));
        }

        if(Integer.parseInt(split[0]) - Integer.parseInt(currentDateSplit[2]) > 0 && Integer.parseInt(split[0]) - Integer.parseInt(currentDateSplit[2]) < 7) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#E5EBB2"));
        }


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView expDate;
        private final CardView cardView;

        ProductHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.productName);
            expDate = itemView.findViewById(R.id.expiarationDate);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
