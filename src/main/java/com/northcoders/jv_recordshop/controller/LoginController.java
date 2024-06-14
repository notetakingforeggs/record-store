package com.northcoders.jv_recordshop.controller;
import com.northcoders.jv_recordshop.DTO.UserDTO;
import com.northcoders.jv_recordshop.model.UserEntity;
import com.northcoders.jv_recordshop.security.PasswordUtils;
import com.northcoders.jv_recordshop.security.SecurityValidation;
import com.northcoders.jv_recordshop.service.UserService;
import com.northcoders.jv_recordshop.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/auth")

public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> postUser(@Valid @RequestBody UserDTO user) {
        System.out.println("getting in");
        try{SecurityValidation secVal = new SecurityValidation();
        PasswordUtils passwordUtils = new PasswordUtils();


            String encryptedPass = passwordUtils.hashPassword(user.getPassword());
            UserEntity newUser = new UserEntity();
            newUser.setId(user.getId());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(encryptedPass);

            userService.addUserEntity(newUser);
            return new ResponseEntity<>("success! new account created for " + user.getEmail(), HttpStatus.OK);

        } catch(Exception e){
            return new ResponseEntity<>("registration failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody UserEntity user){
        if (new UserServiceImpl().isValidPassword(user)){
            return new ResponseEntity<>("password is legit", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Invalid Password", HttpStatus.BAD_REQUEST);
        }
    }
}
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
