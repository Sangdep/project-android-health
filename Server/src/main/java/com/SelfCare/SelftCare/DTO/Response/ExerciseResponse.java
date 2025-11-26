package com.SelfCare.SelftCare.DTO.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExerciseResponse {
    Long exerciseId;
    String exerciseName;
    Double caloriesPerMinute;
    String description;
    String instructions;
    String difficultyLevel;
    String equipmentNeeded;
    String muscleGroups;
    String imageUrl;
    String videoUrl;
    String categoryName;
    LocalDateTime createdAt;
}

