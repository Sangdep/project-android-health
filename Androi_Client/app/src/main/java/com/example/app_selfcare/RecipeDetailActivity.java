package com.example.app_selfcare;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class RecipeDetailActivity extends AppCompatActivity {

    private ImageView backButton, ivRecipeImage, saveButton;
    private TextView tvRecipeTitle, tvRecipeTime;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        // Khởi tạo views
        backButton = findViewById(R.id.backButton);
        ivRecipeImage = findViewById(R.id.iv_recipe_image);
        tvRecipeTitle = findViewById(R.id.tv_recipe_title);
        tvRecipeTime = findViewById(R.id.tv_recipe_time);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        saveButton = findViewById(R.id.saveButton);

        // Nhận dữ liệu từ Intent
        String recipeName = getIntent().getStringExtra("recipeName");
        String recipeTime = getIntent().getStringExtra("recipeTime");

        // Cập nhật giao diện với dữ liệu
        if (recipeName != null) tvRecipeTitle.setText(recipeName);
        if (recipeTime != null) tvRecipeTime.setText("⏰ " + recipeTime);

        // Setup ViewPager với Fragments
        ArrayList<androidx.fragment.app.Fragment> fragments = new ArrayList<>();
        fragments.add(new IngredientsFragment()); // Giả định bạn đã có fragment này
        fragments.add(new StepsFragment());       // Giả định bạn đã có fragment này

        RecipePagerAdapter adapter = new RecipePagerAdapter(this, fragments);
        viewPager.setAdapter(adapter);

        // Setup TabLayout
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Thành phần");
                    break;
                case 1:
                    tab.setText("Quy trình");
                    break;
            }
        }).attach();

        // Xử lý click back button
        backButton.setOnClickListener(v -> onBackPressed());

        // Xử lý click save button
        saveButton.setOnClickListener(v -> {
            String recipeNameToSave = tvRecipeTitle.getText().toString();
            String recipeTimeToSave = tvRecipeTime.getText().toString().replace("⏰ ", "");
            saveRecipe(recipeNameToSave, recipeTimeToSave);
            Toast.makeText(this, "Đã lưu món: " + recipeNameToSave, Toast.LENGTH_SHORT).show();
        });

        // Thêm vào sau saveButton.setOnClickListener
        saveButton.setOnClickListener(v -> {
            String recipeNameToSave = tvRecipeTitle.getText().toString();
            String recipeTimeToSave = tvRecipeTime.getText().toString().replace("⏰ ", "");
            saveRecipe(recipeNameToSave, recipeTimeToSave);
            Toast.makeText(this, "Đã lưu món: " + recipeNameToSave, Toast.LENGTH_SHORT).show();
        });
    }

    // Hàm lưu món ăn (sử dụng SharedPreferences)
    private void saveRecipe(String name, String time) {
        SharedPreferences prefs = getSharedPreferences("SavedRecipes", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String savedRecipes = prefs.getString("recipes", "");
        if (!savedRecipes.contains(name)) {
            savedRecipes += name + "|" + time + ";";
            editor.putString("recipes", savedRecipes);
            editor.apply();
        }
    }
}