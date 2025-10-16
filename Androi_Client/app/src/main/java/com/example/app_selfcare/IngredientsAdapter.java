package com.example.app_selfcare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {
    private List<IngredientsFragment.Ingredient> ingredients;

    public IngredientsAdapter(List<IngredientsFragment.Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IngredientsFragment.Ingredient item = ingredients.get(position);
        holder.tvIconName.setText(item.icon + " " + item.name);
        holder.tvAmount.setText(item.amount);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvIconName, tvAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIconName = itemView.findViewById(android.R.id.text1);
            tvAmount = itemView.findViewById(android.R.id.text2);
        }
    }
}