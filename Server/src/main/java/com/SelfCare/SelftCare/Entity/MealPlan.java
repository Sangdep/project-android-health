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
public class MealPlan {

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
    Double totalCalories; // tổng calo trong kế hoạch
    Boolean isActive = true; // đang hoạt động?

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @OneToMany(mappedBy = "mealPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    List<MealPlanDetail> details;

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
