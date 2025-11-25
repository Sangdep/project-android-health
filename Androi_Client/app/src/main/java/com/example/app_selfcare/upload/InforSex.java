package com.example.app_selfcare.upload;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app_selfcare.R;

public class InforSex extends AppCompatActivity {

    private RadioGroup radioGroupGender;
    private RadioButton radioMale, radioFemale;
    private String selectedGender = "FEMALE"; // Default to FEMALE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_infor_sex);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);

        // Initialize selectedGender based on current checked state
        int checkedId = radioGroupGender.getCheckedRadioButtonId();
        if (checkedId == R.id.radioMale) {
            selectedGender = "MALE";
        } else if (checkedId == R.id.radioFemale) {
            selectedGender = "FEMALE";
        } else {
            // Default to FEMALE if nothing is checked (shouldn't happen, but safety check)
            selectedGender = "FEMALE";
            radioFemale.setChecked(true);
        }

        // Listen for gender selection changes
        radioGroupGender.setOnCheckedChangeListener((group, checkedId1) -> {
            if (checkedId1 == R.id.radioMale) {
                selectedGender = "MALE";
            } else if (checkedId1 == R.id.radioFemale) {
                selectedGender = "FEMALE";
            }
        });

        // Also add click listeners to cards to ensure selection works
        findViewById(R.id.cardMale).setOnClickListener(v -> {
            radioMale.setChecked(true);
            selectedGender = "MALE";
        });

        findViewById(R.id.cardFemale).setOnClickListener(v -> {
            radioFemale.setChecked(true);
            selectedGender = "FEMALE";
        });

        findViewById(R.id.buttonContinue).setOnClickListener(v -> {
            // Double check gender before sending
            int currentCheckedId = radioGroupGender.getCheckedRadioButtonId();
            if (currentCheckedId == R.id.radioMale) {
                selectedGender = "MALE";
            } else if (currentCheckedId == R.id.radioFemale) {
                selectedGender = "FEMALE";
            }
            
            // Debug log
            android.util.Log.d("InforSex", "Sending gender: " + selectedGender);
            
            Intent intent = new Intent(InforSex.this, InforAge.class);
            intent.putExtra("user_gender", selectedGender);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }
}