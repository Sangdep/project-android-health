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
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long recipeId;

    String recipeName; // tên công thức
    String description; // mô tả
    String instructions; // hướng dẫn nấu ăn
    Integer prepTime; // thời gian chuẩn bị (phút)
    Integer cookTime; // thời gian nấu (phút)
    Integer servings = 1; // số khẩu phần
    String difficultyLevel = "MEDIUM"; // MỨC ĐỘ: EASY, MEDIUM, HARD

    Double totalCalories; // tổng calo
    Double totalProtein; // tổng protein
    Double totalCarb; // tổng carbohydrate
    Double totalFat; // tổng chất béo

    String imageUrl; // URL ảnh món ăn
    String videoUrl; // URL video hướng dẫn
    Boolean isPublic = true; // công khai?

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    User createdBy;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    List<RecipeIngredient> ingredients;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    List<FoodIntakeLog> foodIntakeLogs;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
