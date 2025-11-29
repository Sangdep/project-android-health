package com.example.app_selfcare.Ui.Admin; // Đổi thành package của bạn

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_selfcare.R;

import java.util.ArrayList;
import java.util.List;

public class AddExerciseActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private Spinner spinnerCategory, spinnerDifficulty;
    private EditText etName, etCalories, etEquipment, etMuscle, etDesc, etInstruct, etUrl;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        initViews();
        setupSpinners();
        setupEvents();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerDifficulty = findViewById(R.id.spinnerDifficulty);
        etName = findViewById(R.id.etExerciseName);
        etCalories = findViewById(R.id.etCalories);
        etEquipment = findViewById(R.id.etEquipment);
        etMuscle = findViewById(R.id.etMuscleGroups);
        etDesc = findViewById(R.id.etDescription);
        etInstruct = findViewById(R.id.etInstructions);
        etUrl = findViewById(R.id.etVideoUrl);
        btnSave = findViewById(R.id.btnSaveExercise);
    }

    private void setupSpinners() {
        // 1. Setup Difficulty Spinner (Cứng, vì enum backend cố định)
        String[] difficulties = {"BEGINNER", "INTERMEDIATE", "ADVANCED"};
        ArrayAdapter<String> diffAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, difficulties);
        diffAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(diffAdapter);

        // 2. Setup Category Spinner
        // Ở đây tạm thời dùng dữ liệu giả. Sau này bạn sẽ gọi API lấy list ExerciseCategory và map vào đây
        List<String> categories = new ArrayList<>();
        categories.add("Cardio (Tim mạch)");
        categories.add("Strength (Sức mạnh)");
        categories.add("Yoga & Flexibility");
        categories.add("HIIT");

        ArrayAdapter<String> catAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(catAdapter);
    }

    private void setupEvents() {
        btnBack.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> {
            // Lấy dữ liệu từ giao diện
            String name = etName.getText().toString();
            String category = spinnerCategory.getSelectedItem().toString();
            String difficulty = spinnerDifficulty.getSelectedItem().toString();
            String caloriesStr = etCalories.getText().toString();

            // Validate đơn giản
            if (name.isEmpty() || caloriesStr.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên và lượng calo", Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: Tại đây bạn sẽ tạo Request object và gọi API Retrofit sang Spring Boot
            // Ví dụ: ExerciseRequest req = new ExerciseRequest(name, Double.parseDouble(caloriesStr), ...);

            Toast.makeText(this, "Đã lưu bài tập: " + name, Toast.LENGTH_SHORT).show();
            finish(); // Đóng màn hình sau khi lưu
        });
    }
}