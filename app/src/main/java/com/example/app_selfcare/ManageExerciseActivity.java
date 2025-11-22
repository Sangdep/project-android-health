package com.example.app_selfcare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class ManageExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magage_exercice);

        // Nút Back → về AdminHomeActivity
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            startActivity(new Intent(ManageExerciseActivity.this, AdminHomeActivity.class));
            finish(); // Đóng activity hiện tại
        });
    }
}