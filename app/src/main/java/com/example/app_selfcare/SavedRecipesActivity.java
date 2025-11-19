package com.example.app_selfcare;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SavedRecipesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SavedRecipeAdapter adapter;
    private List<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_recipes);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Món đã lưu");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Setup RecyclerView
        recyclerView = findViewById(R.id.recycler_saved_recipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo danh sách và adapter
        recipeList = new ArrayList<>();
        adapter = new SavedRecipeAdapter(recipeList, this);
        recyclerView.setAdapter(adapter);

        // Lấy danh sách món đã lưu từ SharedPreferences
        loadSavedRecipes();

        // Xử lý navigation
        findViewById(R.id.homeIcon).setOnClickListener(v -> finish());
    }

    private void loadSavedRecipes() {
        SharedPreferences prefs = getSharedPreferences("SavedRecipes", MODE_PRIVATE);
        String savedRecipes = prefs.getString("recipes", "");

        if (!savedRecipes.isEmpty()) {
            String[] recipes = savedRecipes.split(";");
            for (String recipe : recipes) {
                if (!recipe.isEmpty()) {
                    String[] parts = recipe.split("\\|");
                    if (parts.length == 2) {
                        recipeList.add(new Recipe(parts[0], parts[1], R.drawable.ic_launcher_background));
                    }
                }
            }
            adapter.notifyDataSetChanged();
        } else {
            // Thêm dữ liệu mẫu nếu danh sách rỗng
            saveSampleData();
            loadSavedRecipes(); // Tải lại sau khi thêm dữ liệu mẫu
        }
    }

    private void saveSampleData() {
        SharedPreferences prefs = getSharedPreferences("SavedRecipes", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String savedRecipes = prefs.getString("recipes", "");
        if (savedRecipes.isEmpty()) {
            savedRecipes += "Sườn nướng truyện thông|⏰ 20 min;";
            savedRecipes += "Cơm hầm vị với gà nướng|⏰ 20 min;";
            editor.putString("recipes", savedRecipes);
            editor.apply();
        }
    }

    // Xử lý nút back trên toolbar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    // Model class cho món ăn
    public static class Recipe {
        String name;
        String time;
        int imageRes;

        public Recipe(String name, String time, int imageRes) {
            this.name = name;
            this.time = time;
            this.imageRes = imageRes;
        }
    }
}