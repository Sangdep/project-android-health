package com.SelfCare.SelftCare.Mapper;

import com.SelfCare.SelftCare.DTO.Request.UserRegisterRequest;
import com.SelfCare.SelftCare.DTO.Response.UserResponse;
import com.SelfCare.SelftCare.Entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    User toUser(UserRegisterRequest request);
    UserResponse toUserResponse(User user);
}
