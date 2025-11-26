package com.example.app_selfcare.upload;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app_selfcare.HomeActivity;
import com.example.app_selfcare.R;

public class InforGoal extends AppCompatActivity {

    private CheckBox checkboxLoseWeight, checkboxPustWeight, checkboxIncreaseStrength, checkboxTryApp;
    private String selectedHealthGoal = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_goal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        checkboxLoseWeight = findViewById(R.id.checkboxLoseWeight);
        checkboxPustWeight = findViewById(R.id.checkboxGainWeight);
        checkboxIncreaseStrength = findViewById(R.id.checkboxIncreaseStrength);
        checkboxTryApp = findViewById(R.id.checkboxTryApp);

        // Set up checkbox listeners
        setupCheckboxListener(checkboxLoseWeight, "Tôi muốn giảm cân");
        setupCheckboxListener(checkboxPustWeight, "Tôi muốn tăng cân");
        setupCheckboxListener(checkboxIncreaseStrength, "Tôi muốn tăng sức bền");
        setupCheckboxListener(checkboxTryApp, "Chỉ đang thử ứng dụng! 👍");

        findViewById(R.id.buttonContinue).setOnClickListener(v -> {
            if (selectedHealthGoal.isEmpty()) {
                // Default to first option if none selected
                selectedHealthGoal = "Tôi muốn giảm cân";
            }
            Intent intent = new Intent(InforGoal.this, Avatar.class);
            // Pass forward data from previous activities - always pass, even if null
            String gender = getIntent().getStringExtra("user_gender");
            if (gender != null) {
                intent.putExtra("user_gender", gender);
            }
            int age = getIntent().getIntExtra("user_age", -1);
            if (age != -1) {
                intent.putExtra("user_age", age);
            }
            int height = getIntent().getIntExtra("user_height_cm", -1);
            if (height != -1) {
                intent.putExtra("user_height_cm", height);
            }
            int weight = getIntent().getIntExtra("user_weight_kg", -1);
            if (weight != -1) {
                intent.putExtra("user_weight_kg", weight);
            }
            // Pass current data
            intent.putExtra("user_health_goal", selectedHealthGoal);
            
            // Debug log
            android.util.Log.d("InforGoal", "Passing gender: " + gender);
            
            startActivity(intent);
            finish(); // Đóng toàn bộ form
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        // Nút Back → Quay lại InforWeight
        findViewById(R.id.backButton).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }

    private void setupCheckboxListener(CheckBox checkBox, String goalText) {
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Uncheck all others
                if (checkBox != checkboxLoseWeight) checkboxLoseWeight.setChecked(false);
                if (checkBox != checkboxPustWeight) checkboxPustWeight.setChecked(false);
                if (checkBox != checkboxIncreaseStrength) checkboxIncreaseStrength.setChecked(false);
                if (checkBox != checkboxTryApp) checkboxTryApp.setChecked(false);
                selectedHealthGoal = goalText;
            } else {
                selectedHealthGoal = "";
            }
        });
    }
}