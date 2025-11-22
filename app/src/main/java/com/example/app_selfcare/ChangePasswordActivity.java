// ChangePasswordActivity.java
package com.example.app_selfcare;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePasswordActivity extends AppCompatActivity {

    private ImageView backButton;
    private TextView titleText;
    private EditText oldPasswordEditText, newPasswordEditText, confirmPasswordEditText;
    private ImageView oldPasswordToggle, newPasswordToggle, confirmPasswordToggle;
    private Button changePasswordButton;
    private ImageView homeIcon, settingsIcon, profileIcon, menuIcon;

    private boolean isOldPasswordVisible = false;
    private boolean isNewPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        backButton = findViewById(R.id.backButton);
        titleText = findViewById(R.id.titleText);
        oldPasswordEditText = findViewById(R.id.oldPasswordEditText);
        newPasswordEditText = findViewById(R.id.newPasswordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        oldPasswordToggle = findViewById(R.id.oldPasswordToggle);
        newPasswordToggle = findViewById(R.id.newPasswordToggle);
        confirmPasswordToggle = findViewById(R.id.confirmPasswordToggle);
        changePasswordButton = findViewById(R.id.changePasswordButton);

        homeIcon = findViewById(R.id.homeIcon);
        settingsIcon = findViewById(R.id.settingsIcon);
        profileIcon = findViewById(R.id.profileIcon);
        menuIcon = findViewById(R.id.menuIcon);

        titleText.setText("Đổi mật khẩu");
    }

    private void setupClickListeners() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        oldPasswordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(oldPasswordEditText, oldPasswordToggle, isOldPasswordVisible);
                isOldPasswordVisible = !isOldPasswordVisible;
            }
        });

        newPasswordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(newPasswordEditText, newPasswordToggle, isNewPasswordVisible);
                isNewPasswordVisible = !isNewPasswordVisible;
            }
        });

        confirmPasswordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(confirmPasswordEditText, confirmPasswordToggle, isConfirmPasswordVisible);
                isConfirmPasswordVisible = !isConfirmPasswordVisible;
            }
        });

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle password change
                String oldPassword = oldPasswordEditText.getText().toString();
                String newPassword = newPasswordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                // Add validation logic here
            }
        });
    }

    private void togglePasswordVisibility(EditText editText, ImageView toggleIcon, boolean isVisible) {
        if (isVisible) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            editText.setTransformationMethod(null);
        }
        editText.setSelection(editText.getText().length());
    }
}