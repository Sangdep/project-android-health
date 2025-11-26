package com.SelfCare.SelftCare.DTO.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodDetailDTO {
    Long foodId;
    String foodName;
    Double caloriesPer100g;
    Double proteinPer100g;
    Double carbPer100g;
    Double fatPer100g;
    Double fiberPer100g;
    Double sugarPer100g;
    Double sodiumPer100g;
    String brand;
    String barcode;
    String servingSize;
    String imageUrl;
    Boolean verified;
    String categoryName;
    LocalDateTime createdAt;
}

