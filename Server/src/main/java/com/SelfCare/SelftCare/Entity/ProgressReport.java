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
public class ProgressReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long reportId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    String reportType; // WEEKLY, MONTHLY, QUARTERLY
    LocalDate startDate; // ngày bắt đầu
    LocalDate endDate; // ngày kết thúc
    Double weightChange; // thay đổi cân nặng
    Double avgDailyCalories; // calo trung bình hàng ngày
    Integer totalWorkouts; // tổng số lần tập luyện
    Integer totalWorkoutTime; // tổng thời gian tập (phút)
    Double caloriesBurned; // tổng calo đốt cháy
    Integer waterIntakeAvg; // lượng nước uống trung bình (ml)
    Double sleepAvg; // giấc ngủ trung bình (giờ)
    Integer diaryEntries; // số lượng bài nhật ký
    Integer achievementsEarned; // số thành tích đạt được
    String reportData; // JSON dữ liệu chi tiết thống kê

    LocalDateTime generatedAt;

    @PrePersist
    protected void onCreate() {
        generatedAt = LocalDateTime.now();
    }
}
