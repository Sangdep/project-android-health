package com.example.app_selfcare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ForgetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forget_password);

        // Nút Gửi mã → Sang trang OTP
        findViewById(R.id.btnSendCode).setOnClickListener(v -> {
            Intent intent = new Intent(ForgetPassword.this, ForgetPassword_confirm.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        // Nút "Log in" → Quay lại Login
        findViewById(R.id.tvLogin).setOnClickListener(v -> {
            startActivity(new Intent(ForgetPassword.this, LoginActivity.class));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            finish();
        });

        // Nút Back → Quay lại Login
        findViewById(R.id.btnBack).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }
}