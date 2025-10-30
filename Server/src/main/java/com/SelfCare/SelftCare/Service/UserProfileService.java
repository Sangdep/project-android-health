package com.SelfCare.SelftCare.Service;

import com.SelfCare.SelftCare.DTO.Request.UpdateUserProfileRequest;
import com.SelfCare.SelftCare.DTO.Response.UserProfileResponse;
import com.SelfCare.SelftCare.DTO.Response.UserResponse;
import com.SelfCare.SelftCare.Entity.User;
import com.SelfCare.SelftCare.Entity.UserProfile;
import com.SelfCare.SelftCare.Exception.AppException;
import com.SelfCare.SelftCare.Exception.ErrorCode;
import com.SelfCare.SelftCare.Mapper.UserProfileMapper;
import com.SelfCare.SelftCare.Repository.UserProfileRepository;
import com.SelfCare.SelftCare.Repository.UserRepository;
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
    public UserResponse updateProfile(UpdateUserProfileRequest request) throws IOException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        UserProfile profile = user.getUserProfile();
        if (profile == null) {
            throw new AppException(ErrorCode.PROFILE_NOT_FOUND);
        }


        if (request.getAvatar() != null && !request.getAvatar().isEmpty()) {
            String avatarUrl = fileUploadsService.uploadImage(request.getAvatar());
            profile.setAvatarUrl(avatarUrl);
        }


        profile.setDateOfBirth(request.getDateOfBirth());
        profile.setGender(request.getGender());
        profile.setHeight(request.getHeight());
        profile.setWeight(request.getWeight());
        profile.setHealthGoal(request.getHealthGoal());
        userProfileRepository.save(profile);

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .userProfileResponse(UserProfileResponse.builder()
                        .avatarUrl(profile.getAvatarUrl())
                        .dateOfBirth(profile.getDateOfBirth())
                        .gender(profile.getGender())
                        .height(profile.getHeight())
                        .weight(profile.getWeight())
                        .healthGoal(profile.getHealthGoal())
                        .build())
                .build();
    }




}
