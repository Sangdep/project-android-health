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
public class FoodCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long categoryId;

    String categoryName; // tên danh mục
    String description; // mô tả danh mục


    LocalDateTime createdAt;
    @OneToMany(mappedBy = "foodCategory", cascade = CascadeType.ALL)
    List<Food> foods;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
