package com.example.app_selfcare.Data.Model.Request;

import okhttp3.MultipartBody;

public class UpdateUserProfileRequest {
    private String gender;
    private String dateOfBirth; // Format: "dd-MM-yyyy"
    private Double height;
    private Double weight;
    private String healthGoal;
    private MultipartBody.Part avatar;

    public UpdateUserProfileRequest() {
    }

    public UpdateUserProfileRequest(String gender, String dateOfBirth, Double height, Double weight, String healthGoal, MultipartBody.Part avatar) {
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
        this.healthGoal = healthGoal;
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getHealthGoal() {
        return healthGoal;
    }

    public void setHealthGoal(String healthGoal) {
        this.healthGoal = healthGoal;
    }

    public MultipartBody.Part getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartBody.Part avatar) {
        this.avatar = avatar;
    }
}

