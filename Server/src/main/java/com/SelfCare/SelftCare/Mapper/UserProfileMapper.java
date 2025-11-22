package com.SelfCare.SelftCare.Mapper;

import com.SelfCare.SelftCare.DTO.Request.UpdateUserProfileRequest;
import com.SelfCare.SelftCare.DTO.Response.UserProfileResponse;
import com.SelfCare.SelftCare.Entity.UserProfile;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "Spring")
public interface UserProfileMapper {
    // Chuyển Entity → Response
    UserProfileResponse toResponse(UserProfile userProfile);


    // Cập nhật từ request → entity hiện tại (chỉ update field có trong request)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfileFromRequest(UpdateUserProfileRequest request, @MappingTarget UserProfile profile);
}
