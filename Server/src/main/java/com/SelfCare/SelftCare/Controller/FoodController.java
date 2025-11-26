package com.SelfCare.SelftCare.Controller;

import com.SelfCare.SelftCare.DTO.ApiResponse;
import com.SelfCare.SelftCare.DTO.Request.CreateFoodRequest;
import com.SelfCare.SelftCare.DTO.Response.FoodCreateResponse;
import com.SelfCare.SelftCare.Service.FoodService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FoodController {

    FoodService foodService;

    @GetMapping("/{foodId}")
    ApiResponse<FoodCreateResponse> getFoodDetail(@PathVariable Long foodId) {
        return ApiResponse.<FoodCreateResponse>builder()
                .result(foodService.getFoodDetail(foodId))
                .message("Chi tiết món ăn")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    ApiResponse<FoodCreateResponse> createFood(@ModelAttribute  CreateFoodRequest request) throws IOException {
        return ApiResponse.<FoodCreateResponse>builder()
                .result(foodService.createFood(request))
                .message("Tạo món ăn thành công")
                .build();
    }

}

