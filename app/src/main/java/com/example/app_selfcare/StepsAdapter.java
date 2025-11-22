package com.example.app_selfcare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {
    private List<StepsFragment.Step> steps;

    public StepsAdapter(List<StepsFragment.Step> steps) {
        this.steps = steps;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StepsFragment.Step item = steps.get(position);
        holder.tvStep.setText(item.number + ". " + item.description);
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStep;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStep = itemView.findViewById(android.R.id.text1);
        }
    }
}