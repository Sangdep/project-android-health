package com.example.app_selfcare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private ImageView btnBack, btnSettings;
    private ImageView homeIcon, workoutIcon, recipeIcon, profileIcon;
    private BarChart chartView;
    private TextView pointsText, weightText, bmiText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        // Xử lý padding cho status bar + navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupChart();
        setupStats();
        setupClickListeners();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnSettings = findViewById(R.id.btnSettings);

        homeIcon = findViewById(R.id.homeIcon);
        workoutIcon = findViewById(R.id.workoutIcon);
        recipeIcon = findViewById(R.id.recipeIcon);
        profileIcon = findViewById(R.id.profileIcon);

        chartView = findViewById(R.id.chartView);
        pointsText = findViewById(R.id.pointsText);
        weightText = findViewById(R.id.weightText);
        bmiText = findViewById(R.id.bmiText);
    }

    private void setupChart() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 75f));
        entries.add(new BarEntry(1, 85f));
        entries.add(new BarEntry(2, 90f));
        entries.add(new BarEntry(3, 80f));
        entries.add(new BarEntry(4, 95f));
        entries.add(new BarEntry(5, 78f));
        entries.add(new BarEntry(6, 88f));

        BarDataSet dataSet = new BarDataSet(entries, "Điểm");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextSize(12f);

        BarData barData = new BarData(dataSet);
        chartView.setData(barData);
        chartView.getDescription().setEnabled(false);
        chartView.getLegend().setEnabled(false);
        chartView.getXAxis().setDrawGridLines(false);
        chartView.getAxisLeft().setDrawGridLines(false);
        chartView.getAxisRight().setEnabled(false);
        chartView.animateY(1000);
        chartView.invalidate();
    }

    private void setupStats() {
        pointsText.setText("17");
        weightText.setText("68kg");
        bmiText.setText("25.4");
    }

    private void setupClickListeners() {
        btnBack.setOnClickListener(v -> finish());

        btnSettings.setOnClickListener(v -> {
            startActivity(new Intent(this, AccountSettingsActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        findViewById(R.id.updateInfor).setOnClickListener(v -> {
            // TODO: Chuyển sang màn hình cập nhật thông tin
            // startActivity(new Intent(this, UpdateProfileActivity.class));
        });

        homeIcon.setOnClickListener(v -> {
            startActivity(new Intent(this, HomeActivity.class));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            finish();
        });

        workoutIcon.setOnClickListener(v -> {
            startActivity(new Intent(this, WorkoutActivity.class));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            finish();
        });

        recipeIcon.setOnClickListener(v -> {
            startActivity(new Intent(this, RecipeHomeActivity.class));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            finish();
        });

        profileIcon.setOnClickListener(v -> { /* Đã ở đây */ });
    }
}