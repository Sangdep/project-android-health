// DashboardActivity.java
package com.example.app_selfcare;

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
    private ImageView homeIcon, settingsIcon, profileIcon, menuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();
        setupUserInfo();
        setupChart();
        setupStats();
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
        settingsIcon = findViewById(R.id.settingsIcon);
        profileIcon = findViewById(R.id.profileIcon);
        menuIcon = findViewById(R.id.menuIcon);
    }

    private void setupUserInfo() {
        userName.setText("PhucPy");
        userEmail.setText("phucpy12345@gmail.com");
    }

    private void setupChart() {
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 75f)); // Mon
        entries.add(new BarEntry(1, 85f)); // Tue
        entries.add(new BarEntry(2, 90f)); // Wed
        entries.add(new BarEntry(3, 80f)); // Thu
        entries.add(new BarEntry(4, 85f)); // Fri
        entries.add(new BarEntry(5, 78f)); // Sat
        entries.add(new BarEntry(6, 88f)); // Sun

        BarDataSet dataSet = new BarDataSet(entries, "Biểu đồ điểm");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(12f);

        BarData barData = new BarData(dataSet);
        chartView.setData(barData);
        chartView.getDescription().setEnabled(false);
        chartView.invalidate();

        // Configure chart appearance
        chartView.getXAxis().setGranularity(1f);
        chartView.getAxisLeft().setAxisMinimum(0f);
        chartView.getAxisRight().setEnabled(false);
        chartView.getLegend().setEnabled(false);
    }

    private void setupStats() {
        pointsText.setText("17");
        weightText.setText("68kg");
        bmiText.setText("25.4");
    }
}