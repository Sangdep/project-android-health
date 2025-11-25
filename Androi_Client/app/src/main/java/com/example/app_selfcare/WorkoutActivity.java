package com.example.app_selfcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout);

        // Fix padding cho status bar + navigation bar (dính đáy đẹp mọi máy)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            // Bottom nav tự động dính đáy nhờ padding bottom ở XML + insets
            findViewById(R.id.bottomNav).setPadding(0, 0, 0, systemBars.bottom);
            return insets;
        });

        // ==================== BOTTOM NAVIGATION (dùng ID mới) ====================
        View navHome     = findViewById(R.id.navHome);
        View navWorkout  = findViewById(R.id.navWorkout);
        View navPlanner  = findViewById(R.id.navPlanner);
        View navProfile  = findViewById(R.id.navProfile);

        navHome.setOnClickListener(v -> {
            Intent intent = new Intent(WorkoutActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        navWorkout.setOnClickListener(v -> {
            // Đã ở màn Workout rồi → không cần chuyển, chỉ refresh nếu muốn
            // (hoặc bỏ qua để tránh reload không cần thiết)
        });

        navPlanner.setOnClickListener(v -> {
            startActivity(new Intent(WorkoutActivity.this, WorkoutDetailActivity.class)); // hoặc tên class của bạn
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        navProfile.setOnClickListener(v -> {
            startActivity(new Intent(WorkoutActivity.this, ProfileActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        // ==================== NÚT BACK + CHI TIẾT BÀI TẬP ====================
        findViewById(R.id.backButton).setOnClickListener(v -> finish());

        // Card Squat (có viền xanh) → vào chi tiết bài tập
        View cardSquat = findViewById(R.id.cardSquat);
        cardSquat.setOnClickListener(v -> {
            Intent intent = new Intent(WorkoutActivity.this, WorkoutDetailActivity.class);
            // Nếu muốn truyền tên bài tập:
            // intent.putExtra("workout_name", "Squat");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        // (Tùy chọn) Các card khác nếu muốn click được
        findViewById(R.id.cardHeader).setOnClickListener(v -> {
            // Có thể mở danh sách toàn bộ Strength workouts
        });
    }
}