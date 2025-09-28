package com.SelfCare.SelftCare.Service;

import com.SelfCare.SelftCare.DTO.Request.UserRegisterRequest;
import com.SelfCare.SelftCare.DTO.Response.UserResponse;
import com.SelfCare.SelftCare.Entity.User;
import com.SelfCare.SelftCare.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {


    UserRepository userRepository;


//    public UserResponse userRegister(UserRegisterRequest request)
//    {
//        User user=userRepository.findByUsername(request.getUsername())
//                .orElseThrow(() -> new RuntimeException(""));
//
//
//    }

}
