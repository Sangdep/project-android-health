package com.example.app_selfcare;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profileImage;
    private TextView userName, userEmail;
    private BarChart chartView;
    private TextView pointsText, weightText, bmiText;

    // BOTTOM NAV
    private ImageView homeIcon, workoutIcon, recipeIcon, profileIcon;
    private ImageView btnSettings, btnBack; // NÚT BACK + SETTINGS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();
        setupChart();
        setupStats();
        setupClickListeners();
    }

    private void initViews() {
        profileImage = findViewById(R.id.profileImage);
        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        chartView = findViewById(R.id.chartView);
        pointsText = findViewById(R.id.pointsText);
        weightText = findViewById(R.id.weightText);
        bmiText = findViewById(R.id.bmiText);

        homeIcon = findViewById(R.id.homeIcon);
        workoutIcon = findViewById(R.id.workoutIcon);
        recipeIcon = findViewById(R.id.recipeIcon);
        profileIcon = findViewById(R.id.profileIcon);

        btnSettings = findViewById(R.id.btnSettings);
        btnBack = findViewById(R.id.btnBack); // NÚT BACK
    }

    private void setupChart() {
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 75f));
        entries.add(new BarEntry(1, 85f));
        entries.add(new BarEntry(2, 90f));
        entries.add(new BarEntry(3, 80f));
        entries.add(new BarEntry(4, 85f));
        entries.add(new BarEntry(5, 78f));
        entries.add(new BarEntry(6, 88f));

        BarDataSet dataSet = new BarDataSet(entries, "Biểu đồ điểm");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(12f);

        BarData barData = new BarData(dataSet);
        chartView.setData(barData);
        chartView.getDescription().setEnabled(false);
        chartView.getXAxis().setGranularity(1f);
        chartView.getAxisLeft().setAxisMinimum(0f);
        chartView.getAxisRight().setEnabled(false);
        chartView.getLegend().setEnabled(false);
        chartView.invalidate();
    }

    private void setupStats() {
        pointsText.setText("17");
        weightText.setText("68kg");
        bmiText.setText("25.4");
    }

    private void setupClickListeners() {
        // NÚT BACK
        btnBack.setOnClickListener(v -> onBackPressed());

        // NÚT CÀI ĐẶT
        btnSettings.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, AccountSettingsActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        // BOTTOM NAV
        homeIcon.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            finish();
        });

        workoutIcon.setOnClickListener(v -> {
            // startActivity(new Intent(ProfileActivity.this, WorkoutHomeActivity.class));
        });

        recipeIcon.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, RecipeHomeActivity.class));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            finish();
        });

        profileIcon.setOnClickListener(v -> { /* Đã ở đây */ });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}