package com.SelfCare.SelftCare.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodIntakeLog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long intakeId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    LocalDate logDate; // ngày ghi nhật ký
    String mealType; // BREAKFAST, LUNCH, DINNER, SNACK
    Double quantity; // số lượng
    String unit; // đơn vị

    Double calories; // calo
    Double protein; // protein
    Double carb; // carbohydrate
    Double fat; // chất béo

    String mealPhotoUrl; // ảnh bữa ăn
    String notes; // ghi chú

    LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "food_id")
    Food food;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    Recipe recipe;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
