package com.example.app_selfcare.upload;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app_selfcare.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InforBirthdate extends AppCompatActivity {

    private RecyclerView dayPicker, monthPicker, yearPicker;
    private LinearLayoutManager dayLM, monthLM, yearLM;
    private List<Integer> days = new ArrayList<>();
    private List<Integer> months = new ArrayList<>();
    private List<Integer> years = new ArrayList<>();

    private int selectedDay = 1, selectedMonth = 1, selectedYear = 2000;
    private TextView tvSelectedDate;

    // Số item padding trên/dưới để căn giữa (3 item hiển thị cùng lúc)
    private static final int ITEM_PADDING_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_birthdate);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            var systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupData();
        setupPickers();
        setupButtons();
        scrollToDefaultPosition();
    }

    private void initViews() {
        dayPicker = findViewById(R.id.dayPicker);
        monthPicker = findViewById(R.id.monthPicker);
        yearPicker = findViewById(R.id.yearPicker);
        tvSelectedDate = findViewById(R.id.tvSelectedDate);
    }

    private void setupData() {
        for (int i = 1; i <= 31; i++) days.add(i);
        for (int i = 1; i <= 12; i++) months.add(i);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear; i >= 1950; i--) years.add(i);
    }

    private void setupPickers() {
        setupPicker(dayPicker, days, day -> {
            selectedDay = day;
            updateDateDisplay();
        }, dayLM = new LinearLayoutManager(this));

        setupPicker(monthPicker, months, month -> {
            selectedMonth = month;
            updateDateDisplay();
        }, monthLM = new LinearLayoutManager(this));

        setupPicker(yearPicker, years, year -> {
            selectedYear = year;
            updateDateDisplay();
        }, yearLM = new LinearLayoutManager(this));
    }

    private void setupPicker(RecyclerView rv, List<Integer> data, OnValueChange listener, LinearLayoutManager lm) {
        rv.setLayoutManager(lm);
        DateAdapter adapter = new DateAdapter(data);
        rv.setAdapter(adapter);

        // Tạo hiệu ứng "snap" chính giữa
        new PagerSnapHelper().attachToRecyclerView(rv);

        // Padding trên dưới để căn giữa
        int padding = 140 * (ITEM_PADDING_COUNT + 1);
        rv.setPadding(0, padding, 0, padding);
        rv.setClipToPadding(false);

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int position = lm.findLastCompletelyVisibleItemPosition();
                    // Fix crash: lấy vị trí snap chính xác
                    if (position != RecyclerView.NO_POSITION && position >= ITEM_PADDING_COUNT) {
                        int realPosition = position - ITEM_PADDING_COUNT;
                        if (realPosition < data.size()) {
                            listener.onChange(data.get(realPosition));
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                updateCenterHighlight(recyclerView);
            }
        });
    }

    private void updateCenterHighlight(RecyclerView rv) {
        int centerY = rv.getHeight() / 2;
        for (int i = 0; i < rv.getChildCount(); i++) {
            View child = rv.getChildAt(i);
            TextView tv = (TextView) child;
            int childCenterY = child.getTop() + child.getHeight() / 2;
            float distance = Math.abs(centerY - childCenterY);
            float scale = Math.max(0.8f, 1f - distance / centerY);
            child.setScaleX(scale);
            child.setScaleY(scale);

            if (distance < 100) {
                tv.setTextColor(Color.parseColor("#FF6B35"));
                tv.setTypeface(tv.getTypeface(), android.graphics.Typeface.BOLD);
            } else {
                tv.setTextColor(Color.parseColor("#B8C2CC"));
                tv.setTypeface(tv.getTypeface(), android.graphics.Typeface.NORMAL);
            }
        }
    }

    private void scrollToDefaultPosition() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            dayLM.scrollToPositionWithOffset(days.indexOf(1) + ITEM_PADDING_COUNT, 0);
            monthLM.scrollToPositionWithOffset(months.indexOf(1) + ITEM_PADDING_COUNT, 0);
            yearLM.scrollToPositionWithOffset(years.indexOf(2000) + ITEM_PADDING_COUNT, 0);
        }, 200);
    }

    private void updateDateDisplay() {
        String[] monthNames = {"", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5",
                "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};
        tvSelectedDate.setText(String.format("Ngày %d %s năm %d", selectedDay, monthNames[selectedMonth], selectedYear));
    }

    private void setupButtons() {
        findViewById(R.id.buttonContinue).setOnClickListener(v -> {
            Intent intent = new Intent(this, InforHeight.class);
            intent.putExtra("user_gender", getIntent().getStringExtra("user_gender"));
            intent.putExtra("user_birthdate", String.format("%02d/%02d/%d", selectedDay, selectedMonth, selectedYear));
            intent.putExtra("user_day", selectedDay);
            intent.putExtra("user_month", selectedMonth);
            intent.putExtra("user_year", selectedYear);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        findViewById(R.id.backButton).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }

    interface OnValueChange {
        void onChange(int value);
    }

    static class DateAdapter extends RecyclerView.Adapter<DateAdapter.VH> {
        private final List<Integer> data;

        DateAdapter(List<Integer> data) {
            this.data = data;
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView tv = new TextView(parent.getContext());
            tv.setLayoutParams(new RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 200));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 42);
            tv.setTextColor(Color.parseColor("#B8C2CC"));
            return new VH(tv);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            // Thêm ITEM_PADDING_COUNT item trống ở đầu và cuối để căn giữa đẹp
            if (position < ITEM_PADDING_COUNT || position >= data.size() + ITEM_PADDING_COUNT) {
                holder.tv.setText("");
            } else {
                holder.tv.setText(String.format("%02d", data.get(position - ITEM_PADDING_COUNT)));
            }
        }

        @Override
        public int getItemCount() {
            return data.size() + 2 * ITEM_PADDING_COUNT; // thêm khoảng trống trên + dưới
        }

        static class VH extends RecyclerView.ViewHolder {
            TextView tv;
            VH(View itemView) {
                super(itemView);
                tv = (TextView) itemView;
            }
        }
    }
}