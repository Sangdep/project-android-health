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
public class WaterIntakeLog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long logId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    LocalDate logDate; // ngày ghi nhật ký
    Integer amountMl; // lượng nước (ml)
    String logTime; // thời gian uống nước

    LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
