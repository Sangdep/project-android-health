package com.example.app_selfcare.Ui.upload;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_selfcare.R;

import java.util.ArrayList;
import java.util.List;

public class InforAge extends AppCompatActivity {

    private RecyclerView agePicker;
    private LinearLayoutManager layoutManager;
    private List<Integer> ageList = new ArrayList<>();
    private int selectedAge = 19;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_infor_age);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        agePicker = findViewById(R.id.agePicker);
        setupAgePicker();

        findViewById(R.id.buttonContinue).setOnClickListener(v -> {
            Intent intent = new Intent(InforAge.this, InforHeight.class);
            intent.putExtra("user_age", selectedAge);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        findViewById(R.id.buttonBack).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }

    private void setupAgePicker() {
        // Tuổi từ 16 đến 80
        for (int i = 16; i <= 80; i++) {
            ageList.add(i);
        }

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        agePicker.setLayoutManager(layoutManager);
        agePicker.setAdapter(new AgeAdapter(ageList));

        new PagerSnapHelper().attachToRecyclerView(agePicker);
        agePicker.setClipToPadding(false);

        agePicker.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView rv, int dx, int dy) {
                updateAppearance();
            }

            @Override
            public void onScrollStateChanged(RecyclerView rv, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int pos = layoutManager.findFirstCompletelyVisibleItemPosition();
                    if (pos != RecyclerView.NO_POSITION) selectedAge = ageList.get(pos);
                }
            }
        });

        // Mặc định 19 tuổi
        int pos19 = ageList.indexOf(19);
        agePicker.scrollToPosition(pos19);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            agePicker.smoothScrollToPosition(pos19);
            updateAppearance();
        }, 100);
    }

    private void updateAppearance() {
        int centerY = agePicker.getHeight() / 2;
        for (int i = 0; i < agePicker.getChildCount(); i++) {
            View child = agePicker.getChildAt(i);
            TextView tv = (TextView) child;

            int childCenter = child.getTop() + child.getHeight() / 2;
            float distance = Math.abs(centerY - childCenter);
            float factor = distance / (float) centerY;
            float scale = Math.max(0.75f, 1f - factor * 0.5f);

            child.setScaleX(scale);
            child.setScaleY(scale);

            if (scale > 0.95f) {
                tv.setTextColor(Color.WHITE);
                tv.setBackgroundResource(R.drawable.bg_age_selected); // nền cam tròn
            } else {
                tv.setTextColor(Color.parseColor("#B8C2CC"));
                tv.setBackgroundResource(0);
            }
        }
    }

    // Adapter siêu nhẹ – KHÔNG CẦN file item_age.xml
    static class AgeAdapter extends RecyclerView.Adapter<AgeAdapter.VH> {
        List<Integer> ages;
        AgeAdapter(List<Integer> ages) { this.ages = ages; }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView tv = new TextView(parent.getContext());
            tv.setLayoutParams(new RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    280
            ));
            tv.setGravity(android.view.Gravity.CENTER);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 64);
            tv.setTypeface(null, android.graphics.Typeface.BOLD);  // ← SỬA Ở ĐÂY
            tv.setTextColor(Color.parseColor("#B8C2CC")); // mặc định màu xám
            return new VH(tv);
        }
        @Override
        public void onBindViewHolder(VH holder, int position) {
            holder.tv.setText(String.valueOf(ages.get(position)));
        }

        @Override
        public int getItemCount() { return ages.size(); }

        static class VH extends RecyclerView.ViewHolder {
            TextView tv;
            VH(View v) { super(v); tv = (TextView) v; }
        }
    }
}