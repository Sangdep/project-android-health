package com.SelfCare.SelftCare.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodSearchRequestDTO {

    @NotBlank(message = "keyword is required")
    String keyword;
}

