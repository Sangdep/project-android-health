package com.example.app_selfcare.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_selfcare.R;
import com.example.app_selfcare.Adapter.RecipePeriodAdapter;
import com.example.app_selfcare.Data.RecipeData;
import com.example.app_selfcare.Data.Model.Recipe;

import java.util.List;

public class RecipePeriodFragment extends Fragment {

    private static final String ARG_MEAL_TYPE = "mealType";

    private String mealType;
    private RecyclerView recyclerView;
    private RecipePeriodAdapter adapter;
    private LinearLayout layoutEmpty;
    private TextView tvEmptyMessage;

    public static RecipePeriodFragment newInstance(String mealType) {
        RecipePeriodFragment fragment = new RecipePeriodFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MEAL_TYPE, mealType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mealType = getArguments().getString(ARG_MEAL_TYPE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_period, container, false);

        initViews(view);
        setupRecyclerView();
        loadRecipes();

        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerViewRecipes);
        layoutEmpty = view.findViewById(R.id.layoutEmpty);
        tvEmptyMessage = view.findViewById(R.id.tvEmptyMessage);

        // Set empty message based on meal type
        if (mealType != null) {
            switch (mealType) {
                case "BREAKFAST":
                    tvEmptyMessage.setText("Chưa có món ăn sáng nào");
                    break;
                case "LUNCH":
                    tvEmptyMessage.setText("Chưa có món ăn trưa nào");
                    break;
                case "DINNER":
                    tvEmptyMessage.setText("Chưa có món ăn tối nào");
                    break;
                case "ALL":
                    tvEmptyMessage.setText("Chưa có món ăn nào");
                    break;
            }
        }
    }

    private void setupRecyclerView() {
        adapter = new RecipePeriodAdapter(getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
    }

    private void loadRecipes() {
        // Lấy dữ liệu từ RecipeData
        List<Recipe> recipes = RecipeData.getRecipesByMealType(mealType);

        if (recipes.isEmpty()) {
            showEmpty();
        } else {
            showRecipes(recipes);
        }
    }

    private void showRecipes(List<Recipe> recipes) {
        recyclerView.setVisibility(View.VISIBLE);
        layoutEmpty.setVisibility(View.GONE);
        adapter.setRecipes(recipes);
    }

    private void showEmpty() {
        recyclerView.setVisibility(View.GONE);
        layoutEmpty.setVisibility(View.VISIBLE);
    }
}