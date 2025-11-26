package com.SelfCare.SelftCare.Service;

import com.SelfCare.SelftCare.DTO.Request.FoodCategoryCreateRequest;
import com.SelfCare.SelftCare.DTO.Response.FoodCategoryResponse;
import com.SelfCare.SelftCare.Repository.FoodCategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FoodCategoryService {


    FoodCategoryRepository categoryRepository;


    // Tạo category
    public FoodCategoryResponse createCategory(FoodCategoryCreateRequest request) {
        com.SelfCare.SelftCare.Entity.FoodCategory category = com.SelfCare.SelftCare.Entity.FoodCategory.builder()
                .categoryName(request.getCategoryName())
                .description(request.getDescription())
                .build();
        categoryRepository.save(category);

        return FoodCategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .build();
    }
}
