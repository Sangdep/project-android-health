package com.SelfCare.SelftCare.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkoutPlanDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long detailId;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    WorkoutPlan workoutPlan;

    LocalDate workoutDate; // ngày tập
    Integer sets = 1; // số set
    Integer reps; // số lần lặp lại
    Integer duration; // thời lượng (phút)
    Integer restTime; // thời gian nghỉ (giây)
    Double weight; // cân nặng sử dụng (kg)
    Double distance; // khoảng cách (km)
    Double caloriesTarget; // mục tiêu calo đốt cháy
    String notes; // ghi chú

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    Exercise exercise;
}

// Chi tiết kế hoạch tập luyện - các chi tiết cụ thể của từng bài tập
