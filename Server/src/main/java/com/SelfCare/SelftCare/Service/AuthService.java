package com.SelfCare.SelftCare.Service;

import com.SelfCare.SelftCare.DTO.Request.UserLoginRequest;
import com.SelfCare.SelftCare.DTO.Request.UserResetPasswordRequest;
import com.SelfCare.SelftCare.DTO.Response.UserLoginResponse;
import com.SelfCare.SelftCare.Entity.User;
import com.SelfCare.SelftCare.Exception.AppException;
import com.SelfCare.SelftCare.Exception.ErrorCode;
import com.SelfCare.SelftCare.Repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import io.jsonwebtoken.JwsHeader;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class AuthService {
    UserRepository userRepository;
    MailService mailService;
    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;


    //Đăng Nhập
    public UserLoginResponse authenticated(UserLoginRequest request)
    {
        //tìm email ở db
        var user=userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new AppException(ErrorCode.EMAIL_NOT_FOUND));

        //check password tu request voi password repo
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder(10);
        boolean authenticated= passwordEncoder.matches(request.getPassword(),user.getPassword());
        if (!authenticated)
        {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        var token= genereToken(user);
        return UserLoginResponse.builder()
                .token(token)
                .authenticated(true)
                .role(buildScope(user))
                .build();
    }

    private String genereToken(User user)
    {
        //tao header
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        //tao claim
        JWTClaimsSet jwtClaimsSet=new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issuer("sangledev.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(5    , ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope", buildScope(user))
                .build();
        //chuyen claim vao Payload
        Payload payload =new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject= new JWSObject(header,payload);

        //ky token va chuyen chuoi thanh JWT hoan chinh

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();

        } catch (JOSEException e) {
            log.error("can't create token");
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user)
    {
        StringJoiner stringJoiner=new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles()))
        {
            user.getRoles().forEach(stringJoiner::add);
        }
        return stringJoiner.toString();
    }


    public void resetPassword(UserResetPasswordRequest request) {

        String email=request.getEmail();
        String newPassword=request.getNewPassword();
        // Kiểm tra đã xác minh OTP chưa
        if (!mailService.isOtpVerified(email)) {
            throw new AppException(ErrorCode.INVALID_OTP); // hoặc tạo thêm ErrorCode như "OTP_NOT_VERIFIED"
        }

        // Kiểm tra email có tồn tại không
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.EMAIL_NOT_FOUND));

        // Mã hóa mật khẩu mới
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        // Dọn dẹp OTP sau khi reset thành công
        mailService.clearOtp(email);
    }


}
