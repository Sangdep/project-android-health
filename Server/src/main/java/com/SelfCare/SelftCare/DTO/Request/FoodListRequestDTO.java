package com.SelfCare.SelftCare.DTO.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodListRequestDTO {
    Integer page;
    Integer size;
    Long categoryId;
}

