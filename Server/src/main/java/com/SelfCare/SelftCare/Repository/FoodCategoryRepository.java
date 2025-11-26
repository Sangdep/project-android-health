package com.SelfCare.SelftCare.Repository;

import com.SelfCare.SelftCare.Entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}

