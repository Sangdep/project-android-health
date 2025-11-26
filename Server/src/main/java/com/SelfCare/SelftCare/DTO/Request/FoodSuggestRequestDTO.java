package com.SelfCare.SelftCare.DTO.Request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodSuggestRequestDTO {

    @Min(value = 1, message = "limit must be at least 1")
    @Max(value = 20, message = "limit must be <= 20")
    int limit;
}

