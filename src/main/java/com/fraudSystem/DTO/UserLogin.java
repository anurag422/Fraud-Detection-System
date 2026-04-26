package com.fraudSystem.DTO;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLogin {

    @NotBlank(message = "Email is Required")
    @Email(message = "Invalid Email Address")
    private String email;

    @NotBlank(message = "Invalid Password")
    private String password;

}
