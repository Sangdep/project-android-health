package com.SelfCare.SelftCare.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateExerciseRequest {

    @NotBlank(message = "exerciseName is required")
    String exerciseName;

    @NotNull(message = "caloriesPerMinute is required")
    Double caloriesPerMinute;

    String description;
    String instructions;
    String difficultyLevel;
    String equipmentNeeded;
    String muscleGroups;
    String imageUrl;
    String videoUrl;
    Long categoryId;
}

