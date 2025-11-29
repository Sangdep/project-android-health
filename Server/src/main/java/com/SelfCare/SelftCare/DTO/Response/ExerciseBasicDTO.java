package com.SelfCare.SelftCare.DTO.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExerciseBasicDTO {
    Long exerciseId;
    String exerciseName;
    String difficultyLevel;
    Double caloriesPerMinute;
    String muscleGroups;
    String categoryName;
    String imageUrl;
}


