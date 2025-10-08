package com.example.app_selfcare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class IngredientsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_ingredients);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Hardcode dữ liệu nguyên liệu từ hình
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("🍅", "Củ cà chua", "500g"));
        ingredients.add(new Ingredient("🥬", "Bắp cải", "300g"));
        ingredients.add(new Ingredient("🌮", "Bánh taco", "300g"));
        ingredients.add(new Ingredient("🥪", "Bánh mì", "300g"));

        IngredientsAdapter adapter = new IngredientsAdapter(ingredients);
        recyclerView.setAdapter(adapter);

        return view;
    }

    // Model class đơn giản cho nguyên liệu
    public static class Ingredient {
        String icon;
        String name;
        String amount;

        public Ingredient(String icon, String name, String amount) {
            this.icon = icon;
            this.name = name;
            this.amount = amount;
        }
    }
}