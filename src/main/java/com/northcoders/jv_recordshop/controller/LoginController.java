package com.northcoders.jv_recordshop.controller;

import com.northcoders.jv_recordshop.DTO.UserDTO;
import com.northcoders.jv_recordshop.model.UserEntity;
import com.northcoders.jv_recordshop.security.PasswordUtils;
import com.northcoders.jv_recordshop.security.SecurityValidation;
import com.northcoders.jv_recordshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/auth")

public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    // should password utils somehow be a bean and be autowired?


    @PostMapping("/signup")
    public ResponseEntity<String> postUser(@Valid @RequestBody UserDTO user) {
        try {
            String encryptedPass = new PasswordUtils().hashPassword(user.getPassword());
            UserEntity newUser = new UserEntity();
            newUser.setId(user.getId());
            newUser.setUsername(user.getUsername());
            newUser.setPassword(encryptedPass);

            userService.addUserEntity(newUser);
            return new ResponseEntity<>("success! new account created for " + user.getUsername(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("registration failed", HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User login success!", HttpStatus.OK);
    }
}


/*    if (new UserServiceImpl().isValidPassword(user)){
            return new ResponseEntity<>("password is legit", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Invalid Password", HttpStatus.BAD_REQUEST);
        }
        @PostMapping("login")*/
/*
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
        Authentication authenticationResponse =
                this.authenticationManager.authenticate(authenticationRequest);
        // ...
    }

    public record LoginRequest(String username, String password) {
    }*/
