package com.SelfCare.SelftCare.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExerciseLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long logId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    LocalDate logDate; // ngày tập
    Integer sets = 1; // số set
    Integer reps; // số lần lặp lại
    Integer duration; // thời lượng (phút)
    Double weight; // cân nặng (kg)
    Double distance; // khoảng cách (km)
    Double caloriesBurned; // calo đốt cháy
    Integer heartRateAvg; // nhịp tim trung bình
    Integer heartRateMax; // nhịp tim tối đa
    String notes; // ghi chú

    LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    Exercise exercise;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
