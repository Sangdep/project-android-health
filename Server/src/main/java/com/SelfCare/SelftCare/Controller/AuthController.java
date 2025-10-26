package com.SelfCare.SelftCare.Controller;

import com.SelfCare.SelftCare.DTO.ApiResponse;
import com.SelfCare.SelftCare.DTO.Request.UserLoginRequest;
import com.SelfCare.SelftCare.DTO.Response.UserLoginResponse;
import com.SelfCare.SelftCare.Service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class AuthController {

    AuthService authService;


    @PostMapping("/login")
    ApiResponse<UserLoginResponse> login(@RequestBody UserLoginRequest request)
    {
        var result=authService.authenticated(request);
        return ApiResponse.<UserLoginResponse>builder()
                .result(result)
                .build();

    }
}
