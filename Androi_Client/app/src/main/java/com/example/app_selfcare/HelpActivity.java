package com.example.app_selfcare;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        initViews();
        displayAppVersion();
    }

    private void initViews() {
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // 1. FAQ
        findViewById(R.id.layoutFaq).setOnClickListener(v ->
                startActivity(new Intent(HelpActivity.this, FaqActivity.class)));

        // 2. Hướng dẫn
        findViewById(R.id.layoutGuide).setOnClickListener(v ->
                startActivity(new Intent(HelpActivity.this, GuideActivity.class)));

        // 3. Chat Zalo OA (thay YOUR_ZALO_OA_ID bằng ID thật của bạn)
        findViewById(R.id.layoutChatZalo).setOnClickListener(v -> openZaloOA("YOUR_ZALO_OA_ID"));

        // 4. Gọi hotline
        findViewById(R.id.layoutHotline).setOnClickListener(v -> {
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:19001234"));
            startActivity(call);
        });

        // 5. Gửi email
        findViewById(R.id.layoutEmail).setOnClickListener(v -> {
            Intent email = new Intent(Intent.ACTION_SENDTO);
            email.setData(Uri.parse("mailto:support@yourapp.com"));
            email.putExtra(Intent.EXTRA_SUBJECT, "Yêu cầu hỗ trợ từ ứng dụng");
            try {
                startActivity(email);
            } catch (Exception e) {
                Toast.makeText(this, "Không tìm thấy ứng dụng email", Toast.LENGTH_SHORT).show();
            }
        });

        // 6. Gửi phản hồi / báo lỗi
        findViewById(R.id.layoutFeedback).setOnClickListener(v ->
                startActivity(new Intent(HelpActivity.this, FeedbackActivity.class)));

        // 7. Chính sách & Điều khoản
        findViewById(R.id.layoutPolicy).setOnClickListener(v -> openWeb("https://yourapp.com/privacy"));
        findViewById(R.id.layoutTerms).setOnClickListener(v -> openWeb("https://yourapp.com/terms"));

        // Bottom navigation (nếu muốn giữ chức năng)
        setupBottomNavigation();
    }

    private void displayAppVersion() {
        TextView tvVersion = findViewById(R.id.tvVersion);
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = "Phiên bản ứng dụng: " + pInfo.versionName + " (Build " + pInfo.versionCode + ")";
            tvVersion.setText(version);
        } catch (PackageManager.NameNotFoundException e) {
            tvVersion.setText("Phiên bản ứng dụng: Không xác định");
        }
    }

    private void openZaloOA(String oaId) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://zalo.me/" + oaId));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Vui lòng cài đặt Zalo để chat hỗ trợ", Toast.LENGTH_LONG).show();
        }
    }

    private void openWeb(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void setupBottomNavigation() {
        findViewById(R.id.homeIcon).setOnClickListener(v -> finish()); // ví dụ về nhà
        findViewById(R.id.settingsIcon).setOnClickListener(v -> finish());
        findViewById(R.id.profileIcon).setOnClickListener(v -> finish());
        findViewById(R.id.menuIcon).setOnClickListener(v -> finish());
        // Bạn có thể điều hướng đúng hơn tùy theo app của bạn
    }
}