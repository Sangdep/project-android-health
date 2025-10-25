package com.SelfCare.SelftCare.Entity;

import com.SelfCare.SelftCare.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String email;
    String fullName;
    String password;
    Set<String> roles;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    UserProfile userProfile;

}
