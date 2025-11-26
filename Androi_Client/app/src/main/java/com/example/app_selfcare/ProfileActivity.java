package com.example.app_selfcare;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.app_selfcare.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private ImageView btnBack, btnSettings;
    private ImageView homeIcon, workoutIcon, recipeIcon, profileIcon;
    private LinearLayout updateInfor;
    private BarChart chartView;
    private TextView pointsText, weightText, bmiText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

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

    @SuppressLint("WrongViewCast")
    private void initViews() {
        chartView = findViewById(R.id.chartView);
        pointsText = findViewById(R.id.pointsText);
        weightText = findViewById(R.id.weightText);
        bmiText = findViewById(R.id.bmiText);

        homeIcon = findViewById(R.id.homeIcon);
        workoutIcon = findViewById(R.id.workoutIcon);
        recipeIcon = findViewById(R.id.recipeIcon);
        profileIcon = findViewById(R.id.profileIcon);
        btnSettings = findViewById(R.id.btnSettings);
        btnBack = findViewById(R.id.btnBack);
        updateInfor = findViewById(R.id.updateInfor);
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

        BarDataSet dataSet = new BarDataSet(entries, "Tiến độ");
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
        pointsText.setText("Tăng cân");
        weightText.setText("68 kg");
        bmiText.setText("25.4");
    }

    private void setupClickListeners() {
        btnBack.setOnClickListener(v -> finish());
        btnSettings.setOnClickListener(v -> {
            startActivity(new Intent(this, AccountSettingsActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        updateInfor.setOnClickListener(v -> showUpdateProfileDialog());

        homeIcon.setOnClickListener(v -> navigateAndFinish(HomeActivity.class));
        workoutIcon.setOnClickListener(v -> navigateAndFinish(WorkoutActivity.class));
        recipeIcon.setOnClickListener(v -> navigateAndFinish(RecipeHomeActivity.class));
        profileIcon.setOnClickListener(v -> { /* Đang ở Profile */ });
    }

    private void navigateAndFinish(Class<?> cls) {
        startActivity(new Intent(this, cls));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

    // DIALOG CẬP NHẬT THÔNG TIN SỨC KHỎE
    private void showUpdateProfileDialog() {
        Dialog dialog = new Dialog(this, R.style.DialogTheme);
        dialog.setContentView(R.layout.dialog_update_profile);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // Find all views
        TextInputEditText etWeight = dialog.findViewById(R.id.etWeight);
        TextInputEditText etHeight = dialog.findViewById(R.id.etHeight);
        RadioGroup radioGroupGoal = dialog.findViewById(R.id.radioGroupGoal);
        RadioButton rbGain = dialog.findViewById(R.id.rbGain);
        RadioButton rbMaintain = dialog.findViewById(R.id.rbMaintain);
        RadioButton rbLose = dialog.findViewById(R.id.rbLose);
        TextView tvBMIResult = dialog.findViewById(R.id.tvBMIResult);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnSave = dialog.findViewById(R.id.btnSave);

        // Đặt giá trị hiện tại
        etWeight.setText(weightText.getText().toString().replace(" kg", "").trim());
        etHeight.setText("170");

        // Tính BMI realtime
        TextWatcher bmiWatcher = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                calculateAndShowBMI(etWeight.getText().toString(), etHeight.getText().toString(), tvBMIResult);
            }
        };
        etWeight.addTextChangedListener(bmiWatcher);
        etHeight.addTextChangedListener(bmiWatcher);

        // Tính ngay khi mở
        calculateAndShowBMI(etWeight.getText().toString(), etHeight.getText().toString(), tvBMIResult);

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnSave.setOnClickListener(v -> {
            String weight = etWeight.getText().toString().trim();
            String height = etHeight.getText().toString().trim();

            if (weight.isEmpty() || height.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                return;
            }

            weightText.setText(weight + " kg");
            String bmi = calculateBMI(weight, height);
            bmiText.setText(bmi.split(" - ")[0]);

            String goal = rbGain.isChecked() ? "Tăng cân" :
                    rbMaintain.isChecked() ? "Giữ cân" : "Giảm cân";
            pointsText.setText(goal);

            Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialog.setCancelable(true);
        dialog.show();
    }

    private void calculateAndShowBMI(String weightStr, String heightStr, TextView tv) {
        try {
            if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
                float weight = Float.parseFloat(weightStr);
                float heightM = Float.parseFloat(heightStr) / 100f;
                float bmi = weight / (heightM * heightM);
                String status = bmi < 18.5 ? "Gầy" :
                        bmi < 25 ? "Bình thường" :
                                bmi < 30 ? "Thừa cân" : "Béo phì";
                tv.setText(String.format("BMI: %.1f - %s", bmi, status));
            }
        } catch (Exception ignored) {
            tv.setText("BMI: -");
        }
    }

    private String calculateBMI(String weightStr, String heightStr) {
        try {
            float weight = Float.parseFloat(weightStr);
            float heightM = Float.parseFloat(heightStr) / 100f;
            float bmi = weight / (heightM * heightM);
            String status = bmi < 18.5 ? "Gầy" :
                    bmi < 25 ? "Bình thường" :
                            bmi < 30 ? "Thừa cân" : "Béo phì";
            return String.format("%.1f - %s", bmi, status);
        } catch (Exception e) {
            return "0.0 - -";
        }
    }
}