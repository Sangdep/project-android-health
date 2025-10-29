package com.SelfCare.SelftCare.Controller;

import com.SelfCare.SelftCare.DTO.ApiResponse;
import com.SelfCare.SelftCare.DTO.Request.UserForgotPassword;
import com.SelfCare.SelftCare.DTO.Request.UserLoginRequest;
import com.SelfCare.SelftCare.DTO.Request.UserResetPasswordRequest;
import com.SelfCare.SelftCare.DTO.Request.UserVerifyRequest;
import com.SelfCare.SelftCare.DTO.Response.UserLoginResponse;
import com.SelfCare.SelftCare.Service.AuthService;
import com.SelfCare.SelftCare.Service.MailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class AuthController {

    AuthService authService;
    MailService mailService;


    @PostMapping("/login")
    ApiResponse<UserLoginResponse> login(@RequestBody UserLoginRequest request)
    {
        var result=authService.authenticated(request);
        return ApiResponse.<UserLoginResponse>builder()
                .result(result)
                .build();

    }

    @PostMapping("/forgot-password")
    public ApiResponse<String> forgotPassword(@RequestBody UserForgotPassword request) {
        mailService.sendOtp(request);
        return ApiResponse.<String>builder()
                .result("OTP đã được gửi đến email " + request.getEmail())
                .message("Vui lòng kiểm tra hộp thư của bạn")
                .code(200)
                .build();
    }


    @PostMapping("/verify-otp")
    public ApiResponse<String> verifyOtp(@RequestBody UserVerifyRequest request) {

        boolean valid = mailService.verifyOtp(request);
        if (valid) {
            return ApiResponse.<String>builder()
                    .result("Xác minh OTP thành công! Bạn có thể đặt lại mật khẩu.")
                    .code(200)
                    .build();
        } else {
            return ApiResponse.<String>builder()
                    .result("OTP không hợp lệ hoặc đã hết hạn.")
                    .code(400)
                    .build();
        }
    }

    @PostMapping("/reset-password")
    public ApiResponse<String> resetPassword(@RequestBody UserResetPasswordRequest request) {
        authService.resetPassword(request);
        return ApiResponse.<String>builder()
                .result("Mật khẩu đã được đặt lại thành công!")
                .code(200)
                .build();
    }
}
