package com.SelfCare.SelftCare.DTO.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodBasicDTO {
    Long foodId;
    String foodName;
    Double caloriesPer100g;
    String brand;
    String imageUrl;
    String categoryName;
}

