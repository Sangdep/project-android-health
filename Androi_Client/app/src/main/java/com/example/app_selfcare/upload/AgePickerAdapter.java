package com.example.app_selfcare.upload;


import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AgePickerAdapter extends RecyclerView.Adapter<AgePickerAdapter.AgeViewHolder> {

    private final List<String> ages;
    private final OnAgeSelectedListener listener;

    public interface OnAgeSelectedListener {
        void onAgeSelected(int age);
    }

    public AgePickerAdapter(List<String> ages, OnAgeSelectedListener listener) {
        this.ages = ages;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AgeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        textView.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                180 // chiều cao mỗi item
        ));
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(48);
        textView.setTextColor(ContextCompat.getColor(parent.getContext(), android.R.color.black));
        return new AgeViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull AgeViewHolder holder, int position) {
        int age = 10 + position;
        holder.textView.setText(String.valueOf(age));

        // Hiệu ứng mờ dần khi xa trung tâm (giống iPhone)
        float centerPos = (holder.getBindingAdapterPosition() - getItemCount() / 2f);
        float scale = 1.0f - Math.abs(centerPos) * 0.15f; // càng xa càng nhỏ + mờ
        float alpha = 1.0f - Math.abs(centerPos) * 0.2f;

        holder.textView.setScaleX(scale);
        holder.textView.setScaleY(scale);
        holder.textView.setAlpha(Math.max(alpha, 0.3f));

        // Khi click → chọn ngay
        holder.itemView.setOnClickListener(v -> {
            listener.onAgeSelected(age);
            // Cuộn mượt đến vị trí vừa chọn
            ((RecyclerView) holder.itemView.getParent()).smoothScrollToPosition(position);
        });
    }

    @Override
    public int getItemCount() {
        return ages.size();
    }

    static class AgeViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public AgeViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}
