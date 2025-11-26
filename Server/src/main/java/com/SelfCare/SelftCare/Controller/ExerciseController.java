package com.SelfCare.SelftCare.Controller;

import com.SelfCare.SelftCare.DTO.ApiResponse;
import com.SelfCare.SelftCare.DTO.Request.CreateExerciseRequest;
import com.SelfCare.SelftCare.DTO.Response.ExerciseResponse;
import com.SelfCare.SelftCare.Service.ExerciseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
@Validated
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExerciseController {

    ExerciseService exerciseService;

    @PostMapping
    ApiResponse<ExerciseResponse> createExercise(@RequestBody @Valid CreateExerciseRequest request) {
        return ApiResponse.<ExerciseResponse>builder()
                .result(exerciseService.createExercise(request))
                .message("Tạo bài tập thành công")
                .build();
    }

    @GetMapping
    ApiResponse<List<ExerciseResponse>> listExercises(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "size", required = false) Integer size,
                                                      @RequestParam(value = "categoryId", required = false) Long categoryId,
                                                      @RequestParam(value = "difficulty", required = false) String difficulty) {
        return ApiResponse.<List<ExerciseResponse>>builder()
                .result(exerciseService.listExercises(page, size, categoryId, difficulty))
                .message("Danh sách bài tập")
                .build();
    }

    @GetMapping("/suggestions")
    ApiResponse<List<ExerciseResponse>> suggestExercises(@RequestParam(value = "limit", defaultValue = "5")
                                                         @Min(1) @Max(20) int limit) {
        return ApiResponse.<List<ExerciseResponse>>builder()
                .result(exerciseService.suggestExercises(limit))
                .message("Gợi ý bài tập")
                .build();
    }
}

