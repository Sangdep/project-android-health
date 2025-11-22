package com.example.app_selfcare.Data.remote;

import com.example.app_selfcare.Data.Model.Request.UserLoginRequest;
import com.example.app_selfcare.Data.Model.Response.ApiResponse;
import com.example.app_selfcare.Data.Model.Response.UserLoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("app/auth/login")
    Call<ApiResponse<UserLoginResponse>> login(@Body UserLoginRequest request);

}