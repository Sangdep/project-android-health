package com.SelfCare.SelftCare.Mapper;

import com.SelfCare.SelftCare.DTO.Request.UserRegisterRequest;
import com.SelfCare.SelftCare.DTO.Response.UserResponse;
import com.SelfCare.SelftCare.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {


    User toUser(UserRegisterRequest request);


    //map user ra userReponse

    @Mapping(source = "userProfile.dateOfBirth",target = "userProfileResponse.dateOfBirth")
    @Mapping(source = "userProfile.gender",target = "userProfileResponse.gender")
    @Mapping(source = "userProfile.height",target = "userProfileResponse.height")
    @Mapping(source = "userProfile.weight",target = "userProfileResponse.weight")
    @Mapping(source = "userProfile.healthGoal",target = "userProfileResponse.healthGoal")
    UserResponse toUserResponse(User user);


}
