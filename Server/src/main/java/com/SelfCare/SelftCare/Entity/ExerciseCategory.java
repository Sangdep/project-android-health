package com.SelfCare.SelftCare.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExerciseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long categoryId;

    String categoryName; // tên danh mục
    String description; // mô tả
    String iconUrl; // URL icon

    LocalDateTime createdAt;

    @OneToMany(mappedBy = "exerciseCategory", cascade = CascadeType.ALL)
    List<Exercise> exercises;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

// Danh mục bài tập - phân loại bài tập (cardio, strength, flexibility...)
