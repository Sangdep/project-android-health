
package com.example.app_selfcare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AccountSettingsActivity extends AppCompatActivity {

    private ImageView backButton;
    private TextView titleText;
    private LinearLayout notificationLayout, personalInfoLayout, passwordLayout, helpLayout;
    private Button signOutButton;
    private ImageView homeIcon, settingsIcon, profileIcon, menuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings); // phải là đúng XML này

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        backButton = findViewById(R.id.backButton);
        titleText = findViewById(R.id.titleText);
        personalInfoLayout = findViewById(R.id.personalInfoLayout);
        notificationLayout= findViewById(R.id.notificationLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        helpLayout = findViewById(R.id.helpLayout);
        signOutButton = findViewById(R.id.signOutButton);

        homeIcon = findViewById(R.id.homeIcon);
        settingsIcon = findViewById(R.id.settingsIcon);
        profileIcon = findViewById(R.id.profileIcon);
        menuIcon = findViewById(R.id.menuIcon);

        titleText.setText("Cài đặt tài khoản");
    }

    private void setupClickListeners() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // quay lại trang trước
            }
        });

        personalInfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DEBUG_CLICK", "Đã nhấn vào Thông tin cá nhân");
                Intent intent = new Intent(AccountSettingsActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        notificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountSettingsActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        helpLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountSettingsActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        passwordLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountSettingsActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
<<<<<<< HEAD
        helpLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountSettingsActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountSettingsActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
=======
        helpLayout.setOnClickListener(null);
        signOutButton.setOnClickListener(null);
>>>>>>> 558f613ff39e4eeddb5d81b0e1a1c898e64ffb1f
    }

}