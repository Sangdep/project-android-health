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
public class DailyLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long logId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    LocalDate logDate; // ngày ghi nhật ký

    Double currentWeight; // cân nặng hiện tại
    Double waistMeasurement; // đo vòng eo
    Double sleepHours; // số giờ ngủ
    Integer stressLevel; // mức stress từ 1-10
    Integer energyLevel; // mức năng lượng từ 1-10
    String mood; // VERY_BAD, BAD, NEUTRAL, GOOD, VERY_GOOD
    String notes; // ghi chú thêm
    String bodyPhotoUrl; // ảnh cơ thể

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

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
