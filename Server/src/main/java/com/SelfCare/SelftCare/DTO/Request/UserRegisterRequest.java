package com.SelfCare.SelftCare.DTO.Request;

import com.SelfCare.SelftCare.Enum.Gender;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserRegisterRequest {

    @NotBlank
    @Email
    String email;

    @NotBlank
    @Size(min = 3, message = "ít nhất 3 kí tự")
    String username;

    @NotBlank
    @Size(min = 8,message = "ít nhất 8 kí tự")
    String password;

     String fullName;
     LocalDate dateOfBirth;
     Gender gender;
     Double height;
     Double weight;
     String healthGoal;
}
