package com.fraudSystem.Controller;


import com.fraudSystem.DTO.UserDto;
import com.fraudSystem.DTO.UserLogin;
import com.fraudSystem.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String UserRegister(@RequestBody UserDto userDto){
         userService.register(userDto);
         return "User registerd Successfully";
    }

    @PostMapping("/login")
    public String UserLogin(@RequestBody UserLogin userLogin){

        return this.userService.UserLogin(userLogin);
    }

}
