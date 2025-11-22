package com.SelfCare.SelftCare.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long foodId;

    String foodName; // tên thực phẩm
    Double caloriesPer100g; // calo trên 100g
    Double proteinPer100g = 0.0; // protein trên 100g
    Double carbPer100g = 0.0; // carbohydrate trên 100g
    Double fatPer100g = 0.0; // chất béo trên 100g
    Double fiberPer100g = 0.0; // chất xơ trên 100g
    Double sugarPer100g = 0.0; // đường trên 100g
    Double sodiumPer100g = 0.0; // natri trên 100g

    String imageUrl; // URL ảnh thực phẩm
    String barcode; // mã vạch
    String brand; // thương hiệu
    String servingSize; // khẩu phần tiêu chuẩn
    Boolean isVerified = false; // đã xác minh?

    LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    FoodCategory foodCategory;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    List<RecipeIngredient> recipeIngredients;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    List<FoodIntakeLog> foodIntakeLogs;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
