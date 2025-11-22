package com.example.app_selfcare;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
=======
>>>>>>> 558f613ff39e4eeddb5d81b0e1a1c898e64ffb1f

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

<<<<<<< HEAD
import com.example.app_selfcare.Data.Model.Request.UserLoginRequest;
import com.example.app_selfcare.Data.Model.Response.ApiResponse;
import com.example.app_selfcare.Data.Model.Response.UserLoginResponse;
import com.example.app_selfcare.Data.remote.ApiClient;
import com.example.app_selfcare.Data.remote.ApiService;

import com.example.app_selfcare.upload.InforAge;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnLogin;

=======
import com.example.app_selfcare.upload.InforAge;

public class LoginActivity extends AppCompatActivity {

>>>>>>> 558f613ff39e4eeddb5d81b0e1a1c898e64ffb1f
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
<<<<<<< HEAD

=======
>>>>>>> 558f613ff39e4eeddb5d81b0e1a1c898e64ffb1f
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tranglogin), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

<<<<<<< HEAD
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Chuyển sang Register
=======
        // Link "Đăng ký" → Register
>>>>>>> 558f613ff39e4eeddb5d81b0e1a1c898e64ffb1f
        findViewById(R.id.txtRegister).setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
<<<<<<< HEAD

        // Chuyển sang Forgot Password
=======
        // Trong onCreate() của LoginActivity
>>>>>>> 558f613ff39e4eeddb5d81b0e1a1c898e64ffb1f
        findViewById(R.id.txtForgotPassword).setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgetPassword.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

<<<<<<< HEAD
        // NÚT LOGIN
        btnLogin.setOnClickListener(v -> {
            doLogin();
        });
    }

    private void doLogin() {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        UserLoginRequest request = new UserLoginRequest(email, password);

        ApiService api = ApiClient.getClient().create(ApiService.class);

        api.login(request).enqueue(new Callback<ApiResponse<UserLoginResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<UserLoginResponse>> call, Response<ApiResponse<UserLoginResponse>> response) {

                if (response.isSuccessful() && response.body() != null) {

                    ApiResponse<UserLoginResponse> apiRes = response.body();

                    if (apiRes.getCode() == 200 && apiRes.getResult().isAuthenticated()) {

                        // Lấy token
                        String token = apiRes.getResult().getToken();

                        // Lưu token vào SharedPreferences
                        saveToken(token);

                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                        // Chuyển sang màn hình InforAge
                        startActivity(new Intent(LoginActivity.this, InforAge.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();

                    } else {
                        Toast.makeText(LoginActivity.this, "Sai email hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Lỗi máy chủ!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<UserLoginResponse>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Kết nối thất bại: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveToken(String token) {
        getSharedPreferences("APP_DATA", MODE_PRIVATE)
                .edit()
                .putString("TOKEN", token)
                .apply();
    }
}
=======
        // Nút Đăng nhập → Home
        findViewById(R.id.btnLogin).setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish(); // Đóng Login để không quay lại
        });
    }
}
>>>>>>> 558f613ff39e4eeddb5d81b0e1a1c898e64ffb1f
