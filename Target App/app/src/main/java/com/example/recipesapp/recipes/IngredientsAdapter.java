package com.example.recipesapp.recipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipesapp.Api.Models.Models.RecipeDetails.ExtendedIngredient;
import com.example.recipesapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder>{

    Context context;
    List<ExtendedIngredient> list;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.ingredient_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.ingredient_name.setText(list.get(position).name);
        holder.ingredient_name.setSelected(true);
        holder.ingredient_quantity.setText(list.get(position).original);
        holder.ingredient_quantity.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/" + list.get(position).image).into(holder.ingredient_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class IngredientsViewHolder extends RecyclerView.ViewHolder {
    TextView ingredient_quantity, ingredient_name;
    ImageView ingredient_image;
    public IngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        ingredient_image = itemView.findViewById(R.id.ingredient_image);
        ingredient_name = itemView.findViewById(R.id.ingredient_name);
        ingredient_quantity = itemView.findViewById(R.id.ingredient_quantity);
    }
}