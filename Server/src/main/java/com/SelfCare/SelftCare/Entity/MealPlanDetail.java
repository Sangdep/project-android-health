package com.SelfCare.SelftCare.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MealPlanDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long detailId;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    MealPlan mealPlan;

    LocalDate mealDate; // ngày bữa ăn
    String mealType; // BREAKFAST, LUNCH, DINNER, SNACK
    Double quantity; // số lượng
    String unit; // đơn vị
    Double calories; // calo

    @ManyToOne
    @JoinColumn(name = "food_id")
    Food food;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    Recipe recipe;
}
