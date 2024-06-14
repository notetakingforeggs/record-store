package com.northcoders.jv_recordshop.service;

import com.northcoders.jv_recordshop.model.UserEntity;
import com.northcoders.jv_recordshop.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserEntityRepository userEntityRepository;

    @Override
    public void addUserEntity(UserEntity user) {
        userEntityRepository.save(user);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userEntityRepository.findByemail(email);
    }


    public Boolean isValidPassword(UserEntity user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(user.getPassword(), getUserByEmail(user.getEmail()).getPassword());
    }

}






