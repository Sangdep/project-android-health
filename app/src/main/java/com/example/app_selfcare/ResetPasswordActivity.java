package com.example.app_selfcare;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText edtNewPassword, edtConfirmPassword;
    Button btnChangePassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnChangePassword = findViewById(R.id.btnChangePassword);


        //  Giả lập đổi mật khẩu
        btnChangePassword.setOnClickListener(v -> {
            String pass1 = edtNewPassword.getText().toString().trim();
            String pass2 = edtConfirmPassword.getText().toString().trim();

            if (pass1.isEmpty() || pass2.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pass1.equals(pass2)) {
                Toast.makeText(this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            // ✅ Giả lập "thành công"
            showSuccessDialog();
        });
    }


    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_password_success, null);
        builder.setView(view);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();

        Button btnToLogin = view.findViewById(R.id.btnToLogin);
        btnToLogin.setOnClickListener(v -> {
            dialog.dismiss();
            Toast.makeText(this, "Chuyển về trang đăng nhập", Toast.LENGTH_SHORT).show();
            finish(); // đóng activity
        });

        dialog.show();
    }
}
