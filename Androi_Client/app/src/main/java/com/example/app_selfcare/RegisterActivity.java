    package com.example.app_selfcare;

    import android.content.Intent;
    import android.os.Bundle;
    import android.widget.TextView;

    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;

    import com.example.app_selfcare.upload.InforSex;

    public class RegisterActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_register);

            // Nút Đăng ký → Chuyển sang InforSex
            findViewById(R.id.btnRegister).setOnClickListener(v -> {
                Intent intent = new Intent(RegisterActivity.this, InforSex.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            });

            // Nút "Đăng nhập" (nếu có) → Quay lại Login
            TextView txtLoginRedirect = findViewById(R.id.txtLoginRedirect);
            if (txtLoginRedirect != null) {
                txtLoginRedirect.setOnClickListener(v -> {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                });
            }     }
    }