package com.SelfCare.SelftCare.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long planId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    String planName; // tên kế hoạch
    LocalDate startDate; // ngày bắt đầu
    LocalDate endDate; // ngày kết thúc
    String planType; // DAILY, WEEKLY, MONTHLY
    String fitnessGoal; // LOSE_WEIGHT, GAIN_MUSCLE, IMPROVE_ENDURANCE, MAINTAIN_FITNESS
    String difficultyLevel = "BEGINNER"; // MỨC ĐỘ: BEGINNER, INTERMEDIATE, ADVANCED
    Boolean isActive = true; // đang hoạt động?

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @OneToMany(mappedBy = "workoutPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    List<WorkoutPlanDetail> details;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

// Kế hoạch tập luyện - lưu trữ các kế hoạch tập gym hàng ngày/tuần/tháng
