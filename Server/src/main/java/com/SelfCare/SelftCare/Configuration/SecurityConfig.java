package com.SelfCare.SelftCare.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                .authorizeHttpRequests(request->
                    request.requestMatchers("/auth/**","/Users/Register").permitAll()
                            .anyRequest().authenticated()
                        );


        // Tắt CSRF nếu cần
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(10);
    }



}
