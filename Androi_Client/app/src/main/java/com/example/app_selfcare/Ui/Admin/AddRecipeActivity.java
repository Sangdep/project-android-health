package com.example.app_selfcare.Ui.Admin;

import android.os.Bundle;
import android.view.MenuItem; // Cần thiết cho onOptionsItemSelected
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar; // Sử dụng Toolbar thay vì ImageButton riêng

import com.example.app_selfcare.R;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeActivity extends AppCompatActivity {

    // --- Cập nhật View Components ---
    private Toolbar toolbar; // KHAI BÁO TOOLBAR MỚI THAY CHO ImageButton btnBack

    // Basic Info & Categorization
    private Spinner spinnerCategory; // categoryId
    private EditText etName; // foodName

    // Nutritional Info (Macros)
    private EditText etCal, etPro, etCarb, etFat, etFiber, etSugar; // Mapped to *Per100g

    // Recipe Details
    private EditText etServings; // servings
    private EditText etPrepTime, etCookTime; // prepTime, cookTime
    private EditText etInstructions; // instructions
    private Spinner spinnerMealType; // mealType
    private Spinner spinnerDifficulty; // difficultyLevel
    private Button btnSelectImage; // image (MultipartFile)

    // Action Button
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        initViews();
        setupSpinners();
        setupEvents();
    }

    private void initViews() {
        // HEADER: Thiết lập Toolbar
        toolbar = findViewById(R.id.toolbarFood); // ID của Toolbar trong layout mới
        setSupportActionBar(toolbar);

        // Hiển thị nút Back/Home
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // setHomeAsUpIndicator(R.drawable.ic_backk) nếu bạn muốn dùng icon tùy chỉnh
        }

        // 2. BASIC INFO
        etName = findViewById(R.id.etFoodName);
        spinnerCategory = findViewById(R.id.spinnerFoodCategory);

        // 3. NUTRITIONAL INFO
        etCal = findViewById(R.id.etFoodCalories);
        etPro = findViewById(R.id.etProtein);
        etCarb = findViewById(R.id.etCarb);
        etFat = findViewById(R.id.etFat);
        etFiber = findViewById(R.id.etFiber);
        etSugar = findViewById(R.id.etSugar);

        // 4. RECIPE DETAILS
        etServings = findViewById(R.id.etServings);
        etPrepTime = findViewById(R.id.etPrepTime);
        etCookTime = findViewById(R.id.etCookTime);
        etInstructions = findViewById(R.id.etInstructions);
        spinnerMealType = findViewById(R.id.spinnerMealType);
        spinnerDifficulty = findViewById(R.id.spinnerDifficulty);

        // 5. IMAGE
        btnSelectImage = findViewById(R.id.btnSelectImage);

        // 6. ACTION
        btnSave = findViewById(R.id.btnSaveFood);
    }

    private void setupSpinners() {
        // 1. Category Spinner (categoryId) - Giả định lấy từ server hoặc định nghĩa tĩnh
        List<String> categories = new ArrayList<>();
        categories.add("1 - Thịt"); // Ví dụ ID 1
        categories.add("2 - Rau"); // Ví dụ ID 2
        categories.add("3 - Tinh bột"); // Ví dụ ID 3
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        // 2. Meal Type Spinner (mealType) - MAPPING Enum: BREAKFAST, LUNCH, DINNER, SNACK
        ArrayAdapter<CharSequence> mealTypeAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.meal_types_array, // Cần tạo trong res/values/arrays.xml
                android.R.layout.simple_spinner_item);
        mealTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMealType.setAdapter(mealTypeAdapter);

        // 3. Difficulty Level Spinner (difficultyLevel) - MAPPING Enum: EASY, MEDIUM, HARD
        ArrayAdapter<CharSequence> difficultyAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.difficulty_levels_array, // Cần tạo trong res/values/arrays.xml
                android.R.layout.simple_spinner_item);
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(difficultyAdapter);
    }

    private void setupEvents() {
        // Nút Back được xử lý trong onOptionsItemSelected (Xem dưới đây)

        // Select Image Button (Cần thêm logic chọn ảnh)
        btnSelectImage.setOnClickListener(v -> {
            // TODO: Mở Intent để chọn ảnh từ thư viện (Gallery) hoặc camera
            Toast.makeText(this, "Chức năng chọn ảnh (MultipartFile) đang được triển khai...", Toast.LENGTH_SHORT).show();
        });

        // Save Button (Lấy data và gọi API)
        btnSave.setOnClickListener(v -> {
            // --- 1. Lấy dữ liệu bắt buộc ---
            String name = etName.getText().toString().trim();
            String calStr = etCal.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập Tên món ăn", Toast.LENGTH_SHORT).show();
                return;
            }
            if (calStr.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập Calo trên 100g", Toast.LENGTH_SHORT).show();
                return;
            }

            // --- 2. Xử lý các giá trị và gán vào DTO (Mô phỏng) ---

            Long categoryId = extractCategoryId(spinnerCategory.getSelectedItem().toString());

            // Macros
            double caloriesPer100g = parseDoubleSafe(calStr);
            double proteinPer100g = parseDoubleSafe(etPro.getText().toString());
            double fatPer100g = parseDoubleSafe(etFat.getText().toString());
            double fiberPer100g = parseDoubleSafe(etFiber.getText().toString());
            double sugarPer100g = parseDoubleSafe(etSugar.getText().toString());

            // Recipe Details
            Integer servings = parseIntSafe(etServings.getText().toString());
            Integer prepTime = parseIntSafe(etPrepTime.getText().toString());
            Integer cookTime = parseIntSafe(etCookTime.getText().toString());
            String instructions = etInstructions.getText().toString().trim();

            // Enum Mapping (Cần có logic mapping String -> Enum.MealType/DifficultyLevel)
            String mealTypeString = spinnerMealType.getSelectedItem().toString();
            String difficultyString = spinnerDifficulty.getSelectedItem().toString();


            // TODO: Khởi tạo CreateFoodRequest DTO và gọi API (ví dụ)

            /* CreateFoodRequest request = CreateFoodRequest.builder()
                .foodName(name)
                .caloriesPer100g(caloriesPer100g)
                .proteinPer100g(proteinPer100g)
                // ... fill all other fields
                .categoryId(categoryId)
                .build();

            // callService.createFood(request);
            */

            Toast.makeText(this, "Chuẩn bị lưu: " + name + " (Calo: " + caloriesPer100g + ")", Toast.LENGTH_LONG).show();
            // finish();
        });
    }

    /**
     * Xử lý sự kiện khi nhấn các item trên menu/toolbar.
     * Đặc biệt: android.R.id.home là ID mặc định cho nút mũi tên quay lại trên Toolbar
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Quay lại Activity trước đó
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Hàm tiện ích để tránh lỗi khi parse số Double rỗng
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

    // Hàm tiện ích để parse Integer cho PrepTime, CookTime, Servings
    private Integer parseIntSafe(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0; // Trả về 0 nếu trống
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // Hàm tiện ích để lấy Category ID từ Spinner Item (Giả định format "ID - Tên")
    private Long extractCategoryId(String selectedItem) {
        try {
            return Long.parseLong(selectedItem.split(" - ")[0]);
        } catch (Exception e) {
            return null; // Trả về null nếu không parse được ID
        }
    }
}