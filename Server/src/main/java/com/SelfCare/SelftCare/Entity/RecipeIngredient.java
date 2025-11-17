package com.SelfCare.SelftCare.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ingredientId;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    Food food;

    Double quantity; // số lượng
    String unit; // đơn vị (g, ml, cái, muỗng...)
}
