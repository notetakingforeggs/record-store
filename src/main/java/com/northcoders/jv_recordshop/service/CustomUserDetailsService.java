package com.northcoders.jv_recordshop.service;

import com.northcoders.jv_recordshop.model.UserEntity;
import com.northcoders.jv_recordshop.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserEntityRepository userEntityRepository; // Your UserRepository implementation

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userEntityRepository.findByusername(username);
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // You may build UserDetails object based on the retrieved user entity
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}

