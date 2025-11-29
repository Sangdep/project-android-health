package com.example.app_selfcare.Data.Model;

public class Recipe {
    private int id;
    private String name;
    private String description;
    private int imageResId;
    private int timeMinutes;
    private int calories;
    private String difficulty; // "Dễ", "Trung bình", "Khó"
    private String mealType; // "BREAKFAST", "LUNCH", "DINNER"

    public Recipe(int id, String name, String description, int imageResId,
                  int timeMinutes, int calories, String difficulty, String mealType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
        this.timeMinutes = timeMinutes;
        this.calories = calories;
        this.difficulty = difficulty;
        this.mealType = mealType;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getImageResId() { return imageResId; }
    public int getTimeMinutes() { return timeMinutes; }
    public int getCalories() { return calories; }
    public String getDifficulty() { return difficulty; }
    public String getMealType() { return mealType; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setImageResId(int imageResId) { this.imageResId = imageResId; }
    public void setTimeMinutes(int timeMinutes) { this.timeMinutes = timeMinutes; }
    public void setCalories(int calories) { this.calories = calories; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public void setMealType(String mealType) { this.mealType = mealType; }
}