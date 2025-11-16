package com.example.app_selfcare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class RecipeHomeActivity extends AppCompatActivity {

    private ImageView homeIcon, workoutIcon, recipeIcon, profileIcon;
    private CardView recipeCard1, recipeCard2, newRecipeCard1;
    private Button btnSavedRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_home);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        homeIcon = findViewById(R.id.homeIcon);
        workoutIcon = findViewById(R.id.workoutIcon);
        recipeIcon = findViewById(R.id.recipeIcon);
        profileIcon = findViewById(R.id.profileIcon);

        recipeCard1 = findViewById(R.id.recipeCard1);
        recipeCard2 = findViewById(R.id.recipeCard2);
        newRecipeCard1 = findViewById(R.id.newRecipeCard1);
        btnSavedRecipes = findViewById(R.id.btn_saved_recipes);
    }

    private void setupClickListeners() {
        // Click vào các card để mở RecipeDetailActivity
        recipeCard1.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeHomeActivity.this, RecipeDetailActivity.class);
            intent.putExtra("recipeName", "Classic Greek Salad");
            intent.putExtra("recipeTime", "15 Mins");
            startActivity(intent);
        });

        recipeCard2.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeHomeActivity.this, RecipeDetailActivity.class);
            intent.putExtra("recipeName", "Crunchy Nut Coleslaw");
            intent.putExtra("recipeTime", "10 Mins");
            startActivity(intent);
        });

        newRecipeCard1.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeHomeActivity.this, RecipeDetailActivity.class);
            intent.putExtra("recipeName", "Steak with tomato...");
            intent.putExtra("recipeTime", "20 Mins");
            startActivity(intent);
        });

        // Click vào button "Món đã lưu" để mở SavedRecipesActivity
        btnSavedRecipes.setOnClickListener(v -> {
            startActivity(new Intent(RecipeHomeActivity.this, SavedRecipesActivity.class));
        });

        // Navigation
        homeIcon.setOnClickListener(v -> finish());
        profileIcon.setOnClickListener(v -> startActivity(new Intent(RecipeHomeActivity.this, ProfileActivity.class)));
    }
}