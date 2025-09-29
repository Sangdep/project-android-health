package com.SelfCare.SelftCare.Mapper;

import com.SelfCare.SelftCare.DTO.Request.UserRegisterRequest;
import com.SelfCare.SelftCare.DTO.Response.UserResponse;
import com.SelfCare.SelftCare.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    @Mapping(source = "fullName", target = "userProfile.fullName")
    @Mapping(source = "dateOfBirth", target = "userProfile.dateOfBirth")
    @Mapping(source = "gender", target = "userProfile.gender")
    @Mapping(source = "height", target = "userProfile.height")
    @Mapping(source = "weight", target = "userProfile.weight")
    @Mapping(source = "healthGoal", target = "userProfile.healthGoal")
    User toUser(UserRegisterRequest request);


    @Mapping( source = "userProfile.fullName",target = "userProfileResponse.fullName")
    @Mapping(source = "userProfile.dateOfBirth",target = "userProfileResponse.dateOfBirth")
    @Mapping(source = "userProfile.gender",target = "userProfileResponse.gender")
    @Mapping(source = "userProfile.height",target = "userProfileResponse.height")
    @Mapping(source = "userProfile.weight",target = "userProfileResponse.weight")
    @Mapping(source = "userProfile.healthGoal",target = "userProfileResponse.healthGoal")
    UserResponse toUserResponse(User user);


}
