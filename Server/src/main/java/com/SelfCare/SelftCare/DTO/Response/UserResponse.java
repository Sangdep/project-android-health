package com.SelfCare.SelftCare.DTO.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
     Long id;
     String email;
     String fullName;
     UserProfileResponse userProfileResponse;
}
