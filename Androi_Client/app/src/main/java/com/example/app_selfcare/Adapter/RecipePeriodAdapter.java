package com.example.app_selfcare.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_selfcare.R;
import com.example.app_selfcare.RecipeDetailActivity;
import com.example.app_selfcare.Data.Model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipePeriodAdapter extends RecyclerView.Adapter<RecipePeriodAdapter.RecipeViewHolder> {

    private Context context;
    private List<Recipe> recipeList;

    public RecipePeriodAdapter(Context context) {
        this.context = context;
        this.recipeList = new ArrayList<>();
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipeList = recipes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_recipe_card, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);

        holder.tvRecipeName.setText(recipe.getName());
        holder.tvRecipeDescription.setText(recipe.getDescription());
        holder.tvTime.setText(recipe.getTimeMinutes() + " phút");
        holder.tvCalories.setText(recipe.getCalories() + " kcal");
        holder.tvDifficulty.setText(recipe.getDifficulty());

        // Set image
        holder.ivRecipeImage.setImageResource(recipe.getImageResId());

        // Set meal type badge
        String mealType = recipe.getMealType();
        switch (mealType) {
            case "BREAKFAST":
                holder.tvMealType.setText("Sáng");
                holder.tvMealType.setBackgroundResource(R.drawable.bg_badge_breakfast);
                holder.tvMealType.setVisibility(View.VISIBLE);
                break;
            case "LUNCH":
                holder.tvMealType.setText("Trưa");
                holder.tvMealType.setBackgroundResource(R.drawable.bg_badge_lunch);
                holder.tvMealType.setVisibility(View.VISIBLE);
                break;
            case "DINNER":
                holder.tvMealType.setText("Tối");
                holder.tvMealType.setBackgroundResource(R.drawable.bg_badge_dinner);
                holder.tvMealType.setVisibility(View.VISIBLE);
                break;
            default:
                holder.tvMealType.setVisibility(View.GONE);
        }

        // Click listener
        holder.cardRecipe.setOnClickListener(v -> {
            Intent intent = new Intent(context, RecipeDetailActivity.class);
            intent.putExtra("recipeId", recipe.getId());
            intent.putExtra("recipeName", recipe.getName());
            intent.putExtra("recipeTime", recipe.getTimeMinutes() + " Mins");
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    static class RecipeViewHolder extends RecyclerView.ViewHolder {
        CardView cardRecipe;
        ImageView ivRecipeImage;
        TextView tvRecipeName, tvRecipeDescription;
        TextView tvTime, tvCalories, tvDifficulty;
        TextView tvMealType;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            cardRecipe = itemView.findViewById(R.id.cardRecipe);
            ivRecipeImage = itemView.findViewById(R.id.ivRecipeImage);
            tvRecipeName = itemView.findViewById(R.id.tvRecipeName);
            tvRecipeDescription = itemView.findViewById(R.id.tvRecipeDescription);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvCalories = itemView.findViewById(R.id.tvCalories);
            tvDifficulty = itemView.findViewById(R.id.tvDifficulty);
            tvMealType = itemView.findViewById(R.id.tvMealType);
        }
    }
}