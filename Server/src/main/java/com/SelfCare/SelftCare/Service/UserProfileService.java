package com.SelfCare.SelftCare.Service;

import com.SelfCare.SelftCare.DTO.Request.UpdateUserProfileRequest;
import com.SelfCare.SelftCare.DTO.Request.UserProfileRequest;
import com.SelfCare.SelftCare.DTO.Response.UserProfileResponse;
import com.SelfCare.SelftCare.DTO.Response.UserResponse;
import com.SelfCare.SelftCare.Entity.User;
import com.SelfCare.SelftCare.Entity.UserProfile;
import com.SelfCare.SelftCare.Exception.AppException;
import com.SelfCare.SelftCare.Exception.ErrorCode;
import com.SelfCare.SelftCare.Mapper.UserProfileMapper;
import com.SelfCare.SelftCare.Repository.UserProfileRepository;
import com.SelfCare.SelftCare.Repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserProfileService {


    UserProfileRepository userProfileRepository;
    UserRepository userRepository;
    UserProfileMapper userProfileMapper;
    FileUploadsService fileUploadsService;


    @Transactional
    public UserResponse updateProfile(@Valid UpdateUserProfileRequest request) throws IOException {
        // 1. Lấy thông tin User hiện tại từ Security Context
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        // 2. Lấy Profile, nếu chưa có thì có thể tạo mới (tuỳ logic app bạn, ở đây mình giữ nguyên throw exception)
        UserProfile profile = user.getUserProfile();


        // 3. Xử lý Upload Avatar (Chỉ upload nếu người dùng có gửi file)
        if (request.getAvatar() != null && !request.getAvatar().isEmpty()) {
            try {
                // Gọi hàm upload Cloudinary vừa viết
                String avatarUrl = fileUploadsService.uploadImage(request.getAvatar());

                // Lưu link ảnh (https://res.cloudinary...) vào DB
                profile.setAvatarUrl(avatarUrl);
            } catch (IOException e) {
                // Log lỗi nếu cần thiết và ném ra ngoại lệ để Controller xử lý
                throw new IOException("Lỗi khi upload ảnh lên Cloudinary: " + e.getMessage());
            }
        }

        // 4. Cập nhật các thông tin khác
        // Mẹo: Nên kiểm tra null trước khi set để tránh ghi đè null vào dữ liệu cũ (nếu request gửi thiếu trường)
        if (request.getDateOfBirth() != null) profile.setDateOfBirth(request.getDateOfBirth());
        if (request.getGender() != null) profile.setGender(request.getGender());
        if (request.getHeight() != null) profile.setHeight(request.getHeight());
        if (request.getWeight() != null) profile.setWeight(request.getWeight());
        if (request.getHealthGoal() != null) profile.setHealthGoal(request.getHealthGoal());

        // 5. Lưu vào DB
        userProfileRepository.save(profile);

        // 6. Trả về Response
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .userProfileResponse(UserProfileResponse.builder()
                        .avatarUrl(profile.getAvatarUrl()) // Trả về link Cloudinary
                        .dateOfBirth(profile.getDateOfBirth())
                        .gender(profile.getGender())
                        .height(profile.getHeight())
                        .weight(profile.getWeight())
                        .healthGoal(profile.getHealthGoal())
                        .build())
                .build();
    }



}
