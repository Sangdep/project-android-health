package com.example.app_selfcare.upload;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.ImageView;          // Đã đổi
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;
import com.example.app_selfcare.R;

public class InforSex extends AppCompatActivity {

    // Khai báo biến
    private RadioGroup radioGroupGender;
    private RadioButton radioMale, radioFemale;
    private MaterialCardView cardMale, cardFemale;
    private TextView textMale, textFemale;
    private ImageView iconMale, iconFemale;
    private Button buttonContinue;
    private ImageView buttonBack;        // ĐÃ SỬA: từ ImageButton → ImageView

    private String selectedGender = "FEMALE"; // Mặc định là Nữ

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

        initViews();
        handleGenderSelection(false);
        setupListeners();
    }

    private void initViews() {
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        cardMale = findViewById(R.id.cardMale);
        cardFemale = findViewById(R.id.cardFemale);
        textMale = findViewById(R.id.textMale);
        textFemale = findViewById(R.id.textFemale);
        iconMale = findViewById(R.id.iconMale);
        iconFemale = findViewById(R.id.iconFemale);
        buttonContinue = findViewById(R.id.buttonContinue);
        buttonBack = findViewById(R.id.backButton);  // Giờ đúng kiểu rồi
    }

    private void setupListeners() {
        cardMale.setOnClickListener(v -> handleGenderSelection(true));
        radioMale.setOnClickListener(v -> handleGenderSelection(true));

        cardFemale.setOnClickListener(v -> handleGenderSelection(false));
        radioFemale.setOnClickListener(v -> handleGenderSelection(false));

        buttonContinue.setOnClickListener(v -> {
            Intent intent = new Intent(InforSex.this, InforBirthdate.class);
            intent.putExtra("user_gender", selectedGender);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        // Vẫn hoạt động bình thường dù là ImageView
        buttonBack.setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }

    private void handleGenderSelection(boolean isMale) {
        if (isMale) {
            selectedGender = "MALE";
            radioMale.setChecked(true);
            radioFemale.setChecked(false);
            updateGenderUI(true);
        } else {
            selectedGender = "FEMALE";
            radioFemale.setChecked(true);
            radioMale.setChecked(false);
            updateGenderUI(false);
        }
    }

    private void updateGenderUI(boolean isMaleSelected) {
        int orangeColor = Color.parseColor("#F97316");
        int grayTextColor = Color.parseColor("#1F2937");
        int grayIconColor = Color.parseColor("#4B5563");
        int grayRadioColor = Color.parseColor("#A1A1AA");

        ColorStateList orangeColorState = ColorStateList.valueOf(orangeColor);
        ColorStateList grayRadioColorState = ColorStateList.valueOf(grayRadioColor);

        int strokeWidthPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());

        if (isMaleSelected) {
            cardMale.setStrokeColor(orangeColor);
            cardMale.setStrokeWidth(strokeWidthPx);
            textMale.setTextColor(orangeColor);
            iconMale.setColorFilter(orangeColor);
            radioMale.setButtonTintList(orangeColorState);

            cardFemale.setStrokeColor(Color.TRANSPARENT);
            cardFemale.setStrokeWidth(0);
            textFemale.setTextColor(grayTextColor);
            iconFemale.setColorFilter(grayIconColor);
            radioFemale.setButtonTintList(grayRadioColorState);
        } else {
            cardFemale.setStrokeColor(orangeColor);
            cardFemale.setStrokeWidth(strokeWidthPx);
            textFemale.setTextColor(orangeColor);
            iconFemale.setColorFilter(orangeColor);
            radioFemale.setButtonTintList(orangeColorState);

            cardMale.setStrokeColor(Color.TRANSPARENT);
            cardMale.setStrokeWidth(0);
            textMale.setTextColor(grayTextColor);
            iconMale.setColorFilter(grayIconColor);
            radioMale.setButtonTintList(grayRadioColorState);
        }
    }
}