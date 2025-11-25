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

public class InforHeight extends AppCompatActivity {

    // 1. Thiết lập giới hạn chiều cao (CM)
    private static final float MIN_CM = 100f;
    private static final float MAX_CM = 250f;

    private TextView textViewDisplayedHeight;
    private Button buttonCm, buttonFt;
    private HorizontalScrollView heightRulerScroll;
    private LinearLayout heightRulerContent;

    private boolean isCmSelected = true;
    private float currentHeightCm = 170f; // Mặc định 1m70
    private float displayedMin;
    private float displayedMax;
    private int tickSpacingPx;
    private int sidePaddingPx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Đảm bảo tên file layout XML của bạn là activity_infor_height.xml
        setContentView(R.layout.activity_infor_height);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 2. Ánh xạ View (Khớp chính xác với ID trong file XML)
        // Lưu ý: ID trong XML là textViewDisplayedWeight (do copy từ màn hình cân nặng), nên ở đây phải gọi đúng ID đó
        textViewDisplayedHeight = findViewById(R.id.textViewDisplayedWeight);

        buttonCm = findViewById(R.id.buttonCm);

        // LƯU Ý: Trong XML bạn gửi thiếu nút buttonFt. Bạn cần thêm Button có id buttonFt vào XML.
        // Nếu chưa có, dòng này sẽ trả về null.


        heightRulerScroll = findViewById(R.id.heightRulerScroll);
        heightRulerContent = findViewById(R.id.heightRulerContent);

        tickSpacingPx = dpToPx(12);
        sidePaddingPx = getResources().getDisplayMetrics().widthPixels / 2;

        setupUnitToggle();
        rebuildHeightRuler();

        // 3. Listener cuộn
        if (heightRulerScroll != null) {
            heightRulerScroll.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                handleHeightScroll(scrollX);
            });
        }

        // Nút Tiếp tục -> Chuyển sang màn hình Cân nặng
        View btnContinue = findViewById(R.id.buttonContinue);
        if (btnContinue != null) {
            btnContinue.setOnClickListener(v -> {
                Intent intent = new Intent(InforHeight.this, InforWeight.class);
                // Pass forward data from previous activities - always pass, even if null
                String gender = getIntent().getStringExtra("user_gender");
                if (gender != null) {
                    intent.putExtra("user_gender", gender);
                }
                int age = getIntent().getIntExtra("user_age", -1);
                if (age != -1) {
                    intent.putExtra("user_age", age);
                }
                // Pass current data
                intent.putExtra("user_height_cm", Math.round(currentHeightCm));
                
                // Debug log
                android.util.Log.d("InforHeight", "Passing gender: " + gender);
                
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            });
        }

        // Nút Back
        View btnBack = findViewById(R.id.buttonBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> {
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            });
        }
    }

    private void rebuildHeightRuler() {
        if (heightRulerContent == null) return;

        displayedMin = isCmSelected ? MIN_CM : cmToFt(MIN_CM);
        displayedMax = isCmSelected ? MAX_CM : cmToFt(MAX_CM);

        heightRulerContent.removeAllViews();
        addEdgeSpacer(heightRulerContent);

        // Vòng lặp tạo vạch thước
        for (int value = Math.round(displayedMin); value <= Math.round(displayedMax); value++) {
            heightRulerContent.addView(createTickView(value));
        }
        addEdgeSpacer(heightRulerContent);

        float displayValue = isCmSelected ? currentHeightCm : cmToFt(currentHeightCm);
        updateHeightDisplay(displayValue);
        scrollToValue(displayValue);
    }

    private void handleHeightScroll(int scrollX) {
        float effective = Math.max(0, scrollX);

        float maxScroll = (displayedMax - displayedMin) * tickSpacingPx;
        if (effective > maxScroll) effective = maxScroll;

        float valueInUnit = displayedMin + (effective / tickSpacingPx);

        if (isCmSelected) {
            currentHeightCm = clamp(valueInUnit, MIN_CM, MAX_CM);
            updateHeightDisplay(currentHeightCm);
        } else {
            float clampedFt = clamp(valueInUnit, displayedMin, displayedMax);
            currentHeightCm = ftToCm(clampedFt);
            updateHeightDisplay(clampedFt);
        }
    }

    private void setupUnitToggle() {
        // Kiểm tra null để tránh crash nếu XML thiếu nút
        if (buttonCm == null || buttonFt == null) return;

        buttonCm.setOnClickListener(v -> {
            if (isCmSelected) return;
            isCmSelected = true;
            styleUnitButtons();
            rebuildHeightRuler();
        });

        buttonFt.setOnClickListener(v -> {
            if (!isCmSelected) return;
            isCmSelected = false;
            styleUnitButtons();
            rebuildHeightRuler();
        });

        styleUnitButtons();
    }

    private void styleUnitButtons() {
        if (buttonCm == null || buttonFt == null) return;

        if (isCmSelected) {
            buttonCm.setBackgroundResource(R.drawable.button_unit_selected_background);
            buttonCm.setTextColor(ContextCompat.getColor(this, R.color.orange_text_icon));
            buttonFt.setBackgroundResource(R.drawable.button_unit_default_background);
            buttonFt.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        } else {
            buttonFt.setBackgroundResource(R.drawable.button_unit_selected_background);
            buttonFt.setTextColor(ContextCompat.getColor(this, R.color.orange_text_icon));
            buttonCm.setBackgroundResource(R.drawable.button_unit_default_background);
            buttonCm.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        }
    }

    private void scrollToValue(float valueInCurrentUnit) {
        if (heightRulerScroll == null) return;
        int scrollX = (int) ((valueInCurrentUnit - displayedMin) * tickSpacingPx);
        heightRulerScroll.post(() -> heightRulerScroll.scrollTo(scrollX, 0));
    }

    private void updateHeightDisplay(float value) {
        if (textViewDisplayedHeight == null) return;
        if (isCmSelected) {
            textViewDisplayedHeight.setText(String.format("%d cm", Math.round(value)));
        } else {
            // Hiển thị Feet với 1 số lẻ (ví dụ: 5.7 Ft)
            textViewDisplayedHeight.setText(String.format("%.1f Ft", value));
        }
    }

    private View createTickView(int value) {
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(tickSpacingPx, LinearLayout.LayoutParams.MATCH_PARENT);
        container.setLayoutParams(params);

        View line = new View(this);
        // Logic hiển thị vạch dài ngắn:
        // Nếu CM: chia hết cho 10 là vạch dài.
        // Nếu FT: giữ nguyên (mỗi Foot là 1 vạch dài)
        boolean isMajorTick = isCmSelected ? (value % 10 == 0) : true;

        int lineHeight = dpToPx(isMajorTick ? 60 : 30);
        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(dpToPx(2), lineHeight);
        lineParams.gravity = Gravity.CENTER_HORIZONTAL;
        line.setLayoutParams(lineParams);
        line.setBackgroundColor(Color.WHITE);
        container.addView(line);

        // Hiển thị số dưới vạch
        if (isMajorTick) {
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

    private float cmToFt(float cm) {
        return cm / 30.48f;
    }

    private float ftToCm(float ft) {
        return ft * 30.48f;
    }

    private float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    private int dpToPx(int dp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics()));
    }
}