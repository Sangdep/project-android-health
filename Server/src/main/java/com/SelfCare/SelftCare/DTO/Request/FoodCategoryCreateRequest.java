package com.SelfCare.SelftCare.DTO.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class FoodCategoryCreateRequest {
    String categoryName;
    String description;
}
