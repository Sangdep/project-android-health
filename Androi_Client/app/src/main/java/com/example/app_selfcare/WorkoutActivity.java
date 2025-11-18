package com.example.app_selfcare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView homeIcon = findViewById(R.id.homeIcon);
        ImageView workoutIcon = findViewById(R.id.workoutIcon);
        ImageView recipeIcon = findViewById(R.id.recipeIcon);
        ImageView profileIcon = findViewById(R.id.profileIcon);

        homeIcon.setOnClickListener(v -> {
            Intent intent = new Intent(WorkoutActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        workoutIcon.setOnClickListener(v -> {
            Intent intent = new Intent(WorkoutActivity.this, WorkoutActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        recipeIcon.setOnClickListener(v -> {
            startActivity(new Intent(WorkoutActivity.this, RecipeHomeActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        profileIcon.setOnClickListener(v -> {
            startActivity(new Intent(WorkoutActivity.this, ProfileActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        View cardSquat = findViewById(R.id.cardSquat);
        cardSquat.setOnClickListener(v -> {
            Intent intent = new Intent(WorkoutActivity.this, WorkoutDetailActivity.class);
            startActivity(intent);
        });
    }
}