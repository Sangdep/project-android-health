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
public class PersonalDiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long diaryId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    LocalDate diaryDate; // ngày nhập nhật ký
    String title; // tiêu đề nhật ký
    String content; // nội dung nhật ký
    String mood; // tâm trạng: VERY_BAD, BAD, NEUTRAL, GOOD, VERY_GOOD
    String photos; // JSON array của các URL ảnh
    Boolean isPrivate = true; // nhật ký riêng tư?

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
