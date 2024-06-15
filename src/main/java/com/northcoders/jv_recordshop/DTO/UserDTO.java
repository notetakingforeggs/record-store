package com.northcoders.jv_recordshop.DTO;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    Long id;

    @Email(message = "please provide a valid email")
    String username;

//    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",
//    message = "Password must contain uppercase, lowercase, numbers and special characters, and be over 8 characters long.")
    String password;

    String role;

}


