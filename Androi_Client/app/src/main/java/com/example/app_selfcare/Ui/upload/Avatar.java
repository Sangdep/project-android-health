package com.example.app_selfcare.Ui.upload;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app_selfcare.HomeActivity;
import com.example.app_selfcare.R;

public class Avatar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_avatar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        // Nút Tiếp tục → Main Screen (thay tên Activity đích)
        findViewById(R.id.continueButton).setOnClickListener(v -> {
            Intent intent = new Intent(Avatar.this, HomeActivity.class); // THAY ĐỔI TÊN NÀY
            startActivity(intent);
            finish(); // Đóng toàn bộ form
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        // Nút Back → Quay lại InforSex
        findViewById(R.id.backButton).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }
}