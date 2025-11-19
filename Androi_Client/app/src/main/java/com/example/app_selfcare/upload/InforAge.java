package com.example.app_selfcare.upload;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app_selfcare.R;

import java.lang.reflect.Field;

public class InforAge extends AppCompatActivity {

    private NumberPicker numberPickerAge;
    private TextView textViewSelectedAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_infor_age);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        numberPickerAge = findViewById(R.id.numberPickerAge);
        textViewSelectedAge = findViewById(R.id.textViewSelectedAge);
        setupNumberPicker();

        // Nút Tiếp tục → InforHeight
        findViewById(R.id.buttonContinue).setOnClickListener(v -> {
            Intent intent = new Intent(InforAge.this, InforHeight.class);
            intent.putExtra("user_age", numberPickerAge.getValue());
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        // Nút Back → Quay lại InforSex
        findViewById(R.id.buttonBack).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }

    private void setupNumberPicker() {
        numberPickerAge.setMinValue(10);
        numberPickerAge.setMaxValue(80);
        numberPickerAge.setValue(19);
        numberPickerAge.setWrapSelectorWheel(false);
        numberPickerAge.setFormatter(value -> String.valueOf(value));
        styleNumberPicker(numberPickerAge);
        tintDivider(numberPickerAge);
        textViewSelectedAge.setText(String.valueOf(numberPickerAge.getValue()));

        numberPickerAge.setOnValueChangedListener((picker, oldVal, newVal) -> {
            textViewSelectedAge.setText(String.valueOf(newVal));
        });
    }

    private void styleNumberPicker(NumberPicker picker) {
        for (int i = 0; i < picker.getChildCount(); i++) {
            if (picker.getChildAt(i) instanceof EditText) {
                EditText editText = (EditText) picker.getChildAt(i);
                editText.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32);
                editText.setTypeface(editText.getTypeface(), Typeface.BOLD);
                editText.setBackground(null);
            }
        }
    }

    private void tintDivider(NumberPicker picker) {
        try {
            Field dividerField = NumberPicker.class.getDeclaredField("mSelectionDivider");
            dividerField.setAccessible(true);
            dividerField.set(picker, new ColorDrawable(Color.TRANSPARENT));
            dividerField.setAccessible(false);
        } catch (Exception ignored) {
            // no-op
        }
    }
}