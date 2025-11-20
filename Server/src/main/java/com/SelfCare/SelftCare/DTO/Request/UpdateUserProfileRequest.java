package com.SelfCare.SelftCare.DTO.Request;

import com.SelfCare.SelftCare.Enum.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UpdateUserProfileRequest {


    MultipartFile avatar;
    @NotNull
    LocalDate dateOfBirth;
    @NotNull
    Gender gender;
    @NotNull
    Double height;
    @NotNull
    Double weight;
    @NotBlank
    String healthGoal;
}
