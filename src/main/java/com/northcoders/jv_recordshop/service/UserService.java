package com.northcoders.jv_recordshop.service;

import com.northcoders.jv_recordshop.model.UserEntity;

public interface UserService {
    void addUserEntity(UserEntity user);
    UserEntity getUserByEmail(String email);
}
