package com.SelfCare.SelftCare.DTO.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodListResponseDTO {
    List<FoodBasicDTO> foods;
    Integer page;
    Integer size;
    Long categoryId;
    long totalElements;
    int totalPages;
}

