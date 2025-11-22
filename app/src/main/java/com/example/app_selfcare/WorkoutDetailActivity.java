package com.example.app_selfcare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WorkoutDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(WorkoutDetailActivity.this, WorkoutActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        ImageView homeIcon = findViewById(R.id.homeIcon);
        ImageView workoutIcon = findViewById(R.id.workoutIcon);
        ImageView recipeIcon = findViewById(R.id.recipeIcon);
        ImageView profileIcon = findViewById(R.id.profileIcon);

        homeIcon.setOnClickListener(v -> {
            Intent intent = new Intent(WorkoutDetailActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        workoutIcon.setOnClickListener(v -> {
            Intent intent = new Intent(WorkoutDetailActivity.this, WorkoutActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            finish();
        });

        recipeIcon.setOnClickListener(v -> {
            startActivity(new Intent(WorkoutDetailActivity.this, RecipeHomeActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        profileIcon.setOnClickListener(v -> {
            startActivity(new Intent(WorkoutDetailActivity.this, ProfileActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        Button startWorkoutButton = findViewById(R.id.btnStartWorkout);
        startWorkoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(WorkoutDetailActivity.this, WorkoutTrainingActivity.class);
            startActivity(intent);
        });
    }
}