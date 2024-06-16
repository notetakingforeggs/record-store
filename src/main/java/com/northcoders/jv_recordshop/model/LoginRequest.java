package com.northcoders.jv_recordshop.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {

    String email;

        String password;
}
