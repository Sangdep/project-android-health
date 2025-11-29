package com.SelfCare.SelftCare.Mapper;

import com.SelfCare.SelftCare.DTO.Response.FoodCategoryResponse;
import com.SelfCare.SelftCare.DTO.Response.FoodCreateResponse;
import com.SelfCare.SelftCare.DTO.Response.UserResponse;
import com.SelfCare.SelftCare.Entity.Food;
import com.SelfCare.SelftCare.Entity.FoodCategory;
import com.SelfCare.SelftCare.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface FoodMapper {
    @Mapping(target = "categoryResponse", source = "foodCategory")
    FoodCreateResponse toFoodResponse(Food request);
    FoodCategoryResponse toCategoryResponse(FoodCategory category);
}
