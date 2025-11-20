package com.SelfCare.SelftCare.Service;

import com.SelfCare.SelftCare.DTO.Request.UserRegisterRequest;
import com.SelfCare.SelftCare.DTO.Response.UserResponse;
import com.SelfCare.SelftCare.Entity.User;
import com.SelfCare.SelftCare.Entity.UserProfile;
import com.SelfCare.SelftCare.Enum.Role;
import com.SelfCare.SelftCare.Exception.AppException;
import com.SelfCare.SelftCare.Exception.ErrorCode;
import com.SelfCare.SelftCare.Mapper.UserMapper;
import com.SelfCare.SelftCare.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {


    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;



    public UserResponse userRegister(UserRegisterRequest request)
    {

        if(userRepository.existsByEmail(request.getEmail()))
        {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }


        //tao user
        User user=userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String>role= new HashSet<>();
        role.add(Role.USER.name());

        user.setRoles(role);

        //tao userprofile null
        UserProfile userProfile= UserProfile.builder()
                .user(user)
                .build();

        user.setUserProfile(userProfile);

        User save=userRepository.save(user);

        return userMapper.toUserResponse(save);

    }


}
