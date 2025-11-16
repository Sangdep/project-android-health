package com.example.app_selfcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Quản lý món ăn → ManageFoodActivity
        findViewById(R.id.cardManageFood).setOnClickListener(v -> {
            startActivity(new Intent(AdminHomeActivity.this, ManageFoodActivity.class));
        });

        // Quản lý bài tập → ManageExerciseActivity
        findViewById(R.id.cardManageExercise).setOnClickListener(v -> {
            startActivity(new Intent(AdminHomeActivity.this, ManageExerciseActivity.class));
        });

        // Quản lý thông báo → ManageNotificationActivity
        findViewById(R.id.cardManageNotification).setOnClickListener(v -> {
            startActivity(new Intent(AdminHomeActivity.this, ManageNotificationActivity.class));
        });

        // Nút Đăng xuất → về LoginActivity
        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            Toast.makeText(this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AdminHomeActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}