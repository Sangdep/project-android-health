package com.example.app_selfcare; // thay bằng package của bạn

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // Nút back
        findViewById(R.id.backButton).setOnClickListener(v -> finish());

        // Hiển thị version
        TextView tvVersion = findViewById(R.id.tvVersion);
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            tvVersion.setText(pInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            tvVersion.setText("?.?.?");
        }

    }

}