package com.SelfCare.SelftCare.Service;

import com.SelfCare.SelftCare.DTO.Request.CreateExerciseRequest;
import com.SelfCare.SelftCare.DTO.Response.ExerciseResponse;
import com.SelfCare.SelftCare.Entity.Exercise;
import com.SelfCare.SelftCare.Entity.ExerciseCategory;
import com.SelfCare.SelftCare.Exception.AppException;
import com.SelfCare.SelftCare.Exception.ErrorCode;
import com.SelfCare.SelftCare.Repository.ExerciseCategoryRepository;
import com.SelfCare.SelftCare.Repository.ExerciseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExerciseService {

    ExerciseRepository exerciseRepository;
    ExerciseCategoryRepository exerciseCategoryRepository;

    public ExerciseResponse createExercise(CreateExerciseRequest request) {
        ensureAdmin();
        ExerciseCategory category = null;
        if (request.getCategoryId() != null) {
            category = exerciseCategoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new AppException(ErrorCode.EXERCISE_CATEGORY_NOT_FOUND));
        }

        Exercise exercise = Exercise.builder()
                .exerciseName(request.getExerciseName().trim())
                .caloriesPerMinute(request.getCaloriesPerMinute())
                .description(request.getDescription())
                .instructions(request.getInstructions())
                .difficultyLevel(resolveDifficulty(request.getDifficultyLevel()))
                .equipmentNeeded(request.getEquipmentNeeded())
                .muscleGroups(request.getMuscleGroups())
                .imageUrl(request.getImageUrl())
                .videoUrl(request.getVideoUrl())
                .exerciseCategory(category)
                .build();

        return toResponse(exerciseRepository.save(exercise));
    }

    public List<ExerciseResponse> listExercises(Integer page, Integer size, Long categoryId, String difficulty) {
        Pageable pageable = PageRequest.of(resolvePage(page), resolveSize(size),
                Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Exercise> exercises;
        if (categoryId != null && StringUtils.hasText(difficulty)) {
            exercises = exerciseRepository.findByExerciseCategoryCategoryIdAndDifficultyLevelIgnoreCase(
                    categoryId, difficulty.trim(), pageable);
        } else if (categoryId != null) {
            exercises = exerciseRepository.findByExerciseCategoryCategoryId(categoryId, pageable);
        } else if (StringUtils.hasText(difficulty)) {
            exercises = exerciseRepository.findByDifficultyLevelIgnoreCase(difficulty.trim(), pageable);
        } else {
            exercises = exerciseRepository.findAll(pageable);
        }
        return exercises.stream().map(this::toResponse).toList();
    }

    public List<ExerciseResponse> suggestExercises(int limit) {
        Pageable pageable = PageRequest.of(0, clampLimit(limit),
                Sort.by(Sort.Direction.DESC, "caloriesPerMinute"));
        return exerciseRepository.findAll(pageable)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private int resolvePage(Integer page) {
        return page != null && page >= 0 ? page : 0;
    }

    private int resolveSize(Integer size) {
        if (size == null || size <= 0) {
            return 20;
        }
        return Math.min(size, 50);
    }

    private int clampLimit(int limit) {
        if (limit < 1) {
            return 1;
        }
        return Math.min(limit, 20);
    }

    private String resolveDifficulty(String difficulty) {
        if (!StringUtils.hasText(difficulty)) {
            return "BEGINNER";
        }
        return difficulty.trim().toUpperCase();
    }

    private ExerciseResponse toResponse(Exercise exercise) {
        return ExerciseResponse.builder()
                .exerciseId(exercise.getExerciseId())
                .exerciseName(exercise.getExerciseName())
                .caloriesPerMinute(exercise.getCaloriesPerMinute())
                .description(exercise.getDescription())
                .instructions(exercise.getInstructions())
                .difficultyLevel(exercise.getDifficultyLevel())
                .equipmentNeeded(exercise.getEquipmentNeeded())
                .muscleGroups(exercise.getMuscleGroups())
                .imageUrl(exercise.getImageUrl())
                .videoUrl(exercise.getVideoUrl())
                .categoryName(exercise.getExerciseCategory() != null
                        ? exercise.getExerciseCategory().getCategoryName() : null)
                .createdAt(exercise.getCreatedAt())
                .build();
    }

    private void ensureAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication != null && authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(authority -> authority.equals("ROLE_ADMIN"));
        if (!isAdmin) {
            throw new AppException(ErrorCode.FORBIDDEN);
        }
    }
}

