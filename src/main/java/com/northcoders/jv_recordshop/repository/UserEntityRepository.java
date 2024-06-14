package com.northcoders.jv_recordshop.repository;

import com.northcoders.jv_recordshop.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByemail(String email);

}
