package com.SelfCare.SelftCare.Entity;

import com.SelfCare.SelftCare.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfile {

    @Id
     Long id; //phai trung voi userid
     String fullName;
     String avatarUrl;

     LocalDate dateOfBirth;

     Gender gender;

     Double height;
     Double weight;
     String healthGoal;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    User user;

}
