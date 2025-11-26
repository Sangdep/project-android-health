package com.SelfCare.SelftCare.Mapper;

import com.SelfCare.SelftCare.DTO.Response.FoodCreateResponse;
import com.SelfCare.SelftCare.DTO.Response.UserResponse;
import com.SelfCare.SelftCare.Entity.Food;
import com.SelfCare.SelftCare.Entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface FoodMapper {

    FoodCreateResponse toFoodResponse(Food request);
}
