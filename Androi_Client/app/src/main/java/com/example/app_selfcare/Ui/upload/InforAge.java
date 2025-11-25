package com.example.app_selfcare.Ui.upload;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_selfcare.R;
import com.example.app_selfcare.upload.AgePickerAdapter;

import java.util.ArrayList;
import java.util.List;

public class InforAge extends AppCompatActivity {

    private RecyclerView agePicker;
    private TextView textViewSelectedAge;
    private int selectedAge = 25; // tuổi mặc định

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_infor_age);

        // Xử lý padding cho status bar / navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            var systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Khởi tạo view
        agePicker = findViewById(R.id.agePicker);
        textViewSelectedAge = findViewById(R.id.textViewSelectedAge);

        setupAgePicker();

        // Nút Tiếp tục → màn hình chiều cao
        findViewById(R.id.buttonContinue).setOnClickListener(v -> {
            Intent intent = new Intent(InforAge.this, InforHeight.class);
            intent.putExtra("user_age", selectedAge);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        // Nút Back
        findViewById(R.id.buttonBack).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }

    private void setupAgePicker() {
        // Tạo danh sách tuổi từ 10 đến 80
        List<String> ages = new ArrayList<>();
        for (int i = 10; i <= 80; i++) {
            ages.add(String.valueOf(i));
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        agePicker.setLayoutManager(layoutManager);

        AgePickerAdapter adapter = new AgePickerAdapter(ages, age -> {
            selectedAge = age;
            textViewSelectedAge.setText(String.valueOf(age));
        });

        // Hiệu ứng snap giống iOS
        new PagerSnapHelper().attachToRecyclerView(agePicker);
        agePicker.setAdapter(adapter);

        // Cuộn đến tuổi mặc định (25)
        int defaultPosition = 15; // 25 - 10 = 15
        agePicker.scrollToPosition(defaultPosition);
        textViewSelectedAge.setText("25");
    }
}