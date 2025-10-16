// NotificationActivity.java
package com.example.app_selfcare;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationActivity extends AppCompatActivity {

    private ImageView backButton;
    private TextView titleText;
    private LinearLayout personalNotifLayout, waterNotifLayout, exerciseNotifLayout, foodNotifLayout;
    private Switch personalNotifSwitch, waterNotifSwitch, exerciseNotifSwitch, foodNotifSwitch;
    private TextView personalNotifDesc, waterNotifDesc, exerciseNotifDesc, foodNotifDesc;
    private ImageView homeIcon, practiceIcon, profileIcon, menuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        initViews();
        setupClickListeners();
        setupNotificationSettings();

        // 🔸 Khi vào NotificationActivity thì highlight icon Thông báo (menuIcon)
        setSelectedIcon(menuIcon);
    }


    private void setSelectedIcon(ImageView selectedIcon) {
        // Reset tất cả icon về không có nền
        homeIcon.setBackground(null);
        practiceIcon.setBackground(null);
        profileIcon.setBackground(null);
        menuIcon.setBackground(null);

        // Gán nền tròn cam cho icon được chọn
        selectedIcon.setBackgroundResource(R.drawable.nav_selected_bg);

        // Thêm padding để icon nằm gọn trong vòng tròn
        selectedIcon.setPadding(8, 8, 8, 8);
    }


    private void initViews() {
        backButton = findViewById(R.id.backButton);
        titleText = findViewById(R.id.titleText);

        personalNotifLayout = findViewById(R.id.personalNotifLayout);
        waterNotifLayout = findViewById(R.id.waterNotifLayout);
        exerciseNotifLayout = findViewById(R.id.exerciseNotifLayout);
        foodNotifLayout = findViewById(R.id.foodNotifLayout);

        personalNotifSwitch = findViewById(R.id.personalNotifSwitch);
        waterNotifSwitch = findViewById(R.id.waterNotifSwitch);
        exerciseNotifSwitch = findViewById(R.id.exerciseNotifSwitch);
        foodNotifSwitch = findViewById(R.id.foodNotifSwitch);

        personalNotifDesc = findViewById(R.id.personalNotifDesc);
        waterNotifDesc = findViewById(R.id.waterNotifDesc);
        exerciseNotifDesc = findViewById(R.id.exerciseNotifDesc);
        foodNotifDesc = findViewById(R.id.foodNotifDesc);

        homeIcon = findViewById(R.id.homeIcon);
        practiceIcon = findViewById(R.id.settingsIcon);
        profileIcon = findViewById(R.id.profileIcon);
        menuIcon = findViewById(R.id.menuIcon);

        titleText.setText("Thông báo");
    }

    private void setupNotificationSettings() {
        // Set notification descriptions
        personalNotifDesc.setText("Điều chỉnh tác cá nhân");
        waterNotifDesc.setText("Bạn cần uống bao nhiều ml nước/ngày");
        exerciseNotifDesc.setText("Nhật tập luyện điều độ mỗi ngày");
        foodNotifDesc.setText("Hãy nhật ăn trong thấy hôm nay");

        // Set default switch states
        personalNotifSwitch.setChecked(true);
        waterNotifSwitch.setChecked(false);
        exerciseNotifSwitch.setChecked(false);
        foodNotifSwitch.setChecked(false);
    }

    private void setupClickListeners() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        personalNotifSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Handle personal notification toggle
        });

        waterNotifSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Handle water notification toggle
        });

        exerciseNotifSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Handle exercise notification toggle
        });

        foodNotifSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Handle food notification toggle
        });

        personalNotifLayout.setOnClickListener(v -> personalNotifSwitch.toggle());
        waterNotifLayout.setOnClickListener(v -> waterNotifSwitch.toggle());
        exerciseNotifLayout.setOnClickListener(v -> exerciseNotifSwitch.toggle());
        foodNotifLayout.setOnClickListener(v -> foodNotifSwitch.toggle());
    }


}