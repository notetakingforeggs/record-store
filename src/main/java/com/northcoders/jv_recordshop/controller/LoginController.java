package com.northcoders.jv_recordshop.controller;

import com.northcoders.jv_recordshop.DTO.UserDTO;
import com.northcoders.jv_recordshop.model.LoginRequest;
import com.northcoders.jv_recordshop.model.LoginResponse;
import com.northcoders.jv_recordshop.model.UserEntity;
import com.northcoders.jv_recordshop.security.JWTIssuer;
import com.northcoders.jv_recordshop.security.PasswordUtils;
import com.northcoders.jv_recordshop.security.SecurityValidation;
import com.northcoders.jv_recordshop.service.UserService;
import com.northcoders.jv_recordshop.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class LoginController {

    private final JWTIssuer jwtIssuer;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request){
        var token = jwtIssuer.issue(1L, request.getEmail(), List.of("USER"));
        return  LoginResponse.builder()
                .accessToken(token)
                .build();

    }


}
