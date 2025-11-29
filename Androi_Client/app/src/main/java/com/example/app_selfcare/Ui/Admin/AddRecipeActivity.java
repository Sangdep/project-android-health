package com.example.app_selfcare.Ui.Admin;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_selfcare.R;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private Spinner spinnerCategory;
    private EditText etName, etBrand, etServing, etBarcode, etImage;
    private EditText etCal, etPro, etCarb, etFat, etFiber, etSugar;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        initViews();
        setupCategorySpinner();
        setupEvents();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBackFood);
        spinnerCategory = findViewById(R.id.spinnerFoodCategory);

        // Info fields
        etName = findViewById(R.id.etFoodName);
        etBrand = findViewById(R.id.etBrand);
        etServing = findViewById(R.id.etServingSize);
        etBarcode = findViewById(R.id.etBarcode);
        etImage = findViewById(R.id.etFoodImage);

        // Macro fields
        etCal = findViewById(R.id.etFoodCalories);
        etPro = findViewById(R.id.etProtein);
        etCarb = findViewById(R.id.etCarb);
        etFat = findViewById(R.id.etFat);
        etFiber = findViewById(R.id.etFiber);
        etSugar = findViewById(R.id.etSugar);

        btnSave = findViewById(R.id.btnSaveFood);
    }

    private void setupCategorySpinner() {
        // Dữ liệu giả cho FoodCategory
        List<String> foodCategories = new ArrayList<>();
        foodCategories.add("Thịt & Protein");
        foodCategories.add("Rau củ & Trái cây");
        foodCategories.add("Tinh bột (Cơm, Phở...)");
        foodCategories.add("Đồ uống");
        foodCategories.add("Ăn vặt / Snack");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, foodCategories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
    }

    private void setupEvents() {
        btnBack.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String calStr = etCal.getText().toString();

            if (name.isEmpty() || calStr.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên món và lượng calo", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lấy các giá trị dinh dưỡng (mặc định là 0 nếu không nhập)
            double protein = parseDoubleSafe(etPro.getText().toString());
            double fat = parseDoubleSafe(etFat.getText().toString());
            double carb = parseDoubleSafe(etCarb.getText().toString());

            // TODO: Gọi API POST /api/foods để lưu
            // String categoryName = spinnerCategory.getSelectedItem().toString();

            Toast.makeText(this, "Đã thêm món: " + name, Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    // Hàm tiện ích để tránh lỗi khi parse số rỗng
    private double parseDoubleSafe(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}