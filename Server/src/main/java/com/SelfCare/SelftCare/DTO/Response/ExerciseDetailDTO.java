package com.SelfCare.SelftCare.DTO.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExerciseDetailDTO {
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

