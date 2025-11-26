package com.example.app_selfcare.upload;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app_selfcare.R;

public class InforWeight extends AppCompatActivity {

    private static final float MIN_KG = 30f;
    private static final float MAX_KG = 150f;

    private TextView textViewDisplayedWeight;
    private Button buttonKg, buttonLbs;
    private HorizontalScrollView weightRulerScroll;
    private LinearLayout weightRulerContent;

    private boolean isKgSelected = true;
    private float currentWeightKg = 62f;
    private float displayedMin;
    private float displayedMax;
    private int tickSpacingPx;
    private int sidePaddingPx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_weight);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewDisplayedWeight = findViewById(R.id.textViewDisplayedWeight);
        buttonKg = findViewById(R.id.buttonKg);
        buttonLbs = findViewById(R.id.buttonLbs);
        weightRulerScroll = findViewById(R.id.weightRulerScroll);
        weightRulerContent = findViewById(R.id.weightRulerContent);

        tickSpacingPx = dpToPx(12);
        sidePaddingPx = getResources().getDisplayMetrics().widthPixels / 2;

        setupUnitToggle();
        rebuildWeightRuler();

        // --- SỬA LỖI TẠI ĐÂY: Dùng đúng Listener cho ScrollView ---
        weightRulerScroll.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            handleWeightScroll(scrollX);
        });

        // Nút Tiếp tục → InforGoal
        findViewById(R.id.buttonContinue).setOnClickListener(v -> {
            Intent intent = new Intent(InforWeight.this, InforGoal.class);
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
            // Pass current data
            intent.putExtra("user_weight_kg", Math.round(currentWeightKg));
            
            // Debug log
            android.util.Log.d("InforWeight", "Passing gender: " + gender);
            
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        // Nút Back → Quay lại InforHeight
        findViewById(R.id.backButton).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }

    private void rebuildWeightRuler() {
        if (weightRulerContent == null) return;
        displayedMin = isKgSelected ? MIN_KG : kgToLbs(MIN_KG);
        displayedMax = isKgSelected ? MAX_KG : kgToLbs(MAX_KG);

        weightRulerContent.removeAllViews();
        addEdgeSpacer(weightRulerContent);
        for (int value = Math.round(displayedMin); value <= Math.round(displayedMax); value++) {
            weightRulerContent.addView(createTickView(value));
        }
        addEdgeSpacer(weightRulerContent);

        float displayValue = isKgSelected ? currentWeightKg : kgToLbs(currentWeightKg);
        updateWeightDisplay(displayValue);
        scrollToValue(displayValue);
    }

    // --- SỬA LỖI TẠI ĐÂY: Đổi Object thành int scrollX ---
    private void handleWeightScroll(int scrollX) {
        // Logic chuẩn: scrollX tính từ 0 (vị trí Min)
        float effective = Math.max(0, scrollX);

        float maxScroll = (displayedMax - displayedMin) * tickSpacingPx;
        if (effective > maxScroll) effective = maxScroll;

        float valueInUnit = displayedMin + (effective / tickSpacingPx);

        if (isKgSelected) {
            currentWeightKg = clamp(valueInUnit, MIN_KG, MAX_KG);
            updateWeightDisplay(currentWeightKg);
        } else {
            float clampedLbs = clamp(valueInUnit, displayedMin, displayedMax);
            currentWeightKg = lbsToKg(clampedLbs);
            updateWeightDisplay(clampedLbs);
        }
    }

    private void setupUnitToggle() {
        if (buttonKg == null || buttonLbs == null) return;

        buttonKg.setOnClickListener(v -> {
            if (isKgSelected) return;
            isKgSelected = true;
            styleUnitButtons();
            rebuildWeightRuler();
        });

        buttonLbs.setOnClickListener(v -> {
            if (!isKgSelected) return;
            isKgSelected = false;
            styleUnitButtons();
            rebuildWeightRuler();
        });

        styleUnitButtons();
    }

    private void styleUnitButtons() {
        if (isKgSelected) {
            buttonKg.setBackgroundResource(R.drawable.button_unit_selected_background);
            buttonKg.setTextColor(ContextCompat.getColor(this, R.color.orange_text_icon));
            buttonLbs.setBackgroundResource(R.drawable.button_unit_default_background);
            buttonLbs.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        } else {
            buttonLbs.setBackgroundResource(R.drawable.button_unit_selected_background);
            buttonLbs.setTextColor(ContextCompat.getColor(this, R.color.orange_text_icon));
            buttonKg.setBackgroundResource(R.drawable.button_unit_default_background);
            buttonKg.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        }
    }

    private void scrollToValue(float valueInCurrentUnit) {
        if (weightRulerScroll == null) return;
        // Logic chuẩn: Tính toán vị trí pixel cần scroll tới
        int scrollX = (int) ((valueInCurrentUnit - displayedMin) * tickSpacingPx);

        weightRulerScroll.post(() -> weightRulerScroll.scrollTo(scrollX, 0));
    }

    private void updateWeightDisplay(float value) {
        if (textViewDisplayedWeight == null) return;
        if (isKgSelected) {
            textViewDisplayedWeight.setText(String.format("%d Kg", Math.round(value)));
        } else {
            textViewDisplayedWeight.setText(String.format("%d Lbs", Math.round(value)));
        }
    }

    private View createTickView(int value) {
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(tickSpacingPx, LinearLayout.LayoutParams.MATCH_PARENT);
        container.setLayoutParams(params);

        View line = new View(this);
        int lineHeight = dpToPx(value % 5 == 0 ? 60 : 30);
        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(dpToPx(2), lineHeight);
        lineParams.gravity = Gravity.CENTER_HORIZONTAL;
        line.setLayoutParams(lineParams);
        line.setBackgroundColor(Color.WHITE);
        container.addView(line);

        if (value % 5 == 0) {
            TextView label = new TextView(this);
            label.setText(String.valueOf(value));
            label.setTextColor(Color.WHITE);
            label.setAlpha(0.85f);
            label.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            label.setGravity(Gravity.CENTER_HORIZONTAL);
            container.addView(label);
        }

        return container;
    }

    private void addEdgeSpacer(LinearLayout container) {
        View spacer = new View(this);
        spacer.setLayoutParams(new LinearLayout.LayoutParams(sidePaddingPx, LinearLayout.LayoutParams.MATCH_PARENT));
        container.addView(spacer);
    }

    private float kgToLbs(float kg) {
        return kg * 2.20462f;
    }

    private float lbsToKg(float lbs) {
        return lbs / 2.20462f;
    }

    private float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    private int dpToPx(int dp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics()));
    }
}