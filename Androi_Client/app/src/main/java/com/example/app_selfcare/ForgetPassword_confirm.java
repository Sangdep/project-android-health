package com.example.app_selfcare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ForgetPassword_confirm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forget_password_confirm);

        // Nút Xác nhận → Sang trang đổi mật khẩu
        findViewById(R.id.btnConfirm).setOnClickListener(v -> {
            Intent intent = new Intent(ForgetPassword_confirm.this, ResetPasswordActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        // Nút "Gửi lại mã"
        findViewById(R.id.tvResendCode).setOnClickListener(v -> {
            // Gửi lại mã (giả lập)
            // Có thể thêm countdown
        });

        // Nút Back → Quay lại trang nhập email
        findViewById(R.id.backButton).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }
}