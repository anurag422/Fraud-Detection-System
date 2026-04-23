package com.fraudSystem.DTO;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class UserLogin {

    private String email;
    private String password;

}
