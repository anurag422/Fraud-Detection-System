package com.fraudSystem.Controller;


import com.fraudSystem.DTO.UserDto;
import com.fraudSystem.DTO.UserLogin;
import com.fraudSystem.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String UserRegister(@Valid @RequestBody UserDto userDto){
         userService.register(userDto);
         return "User registerd Successfully";
    }

    @PostMapping("/login")
    public String UserLogin(@Valid @RequestBody UserLogin userLogin){

        return this.userService.UserLogin(userLogin);
    }

    @GetMapping("/test")
    public String test(){
        return "Backend working";
    }

}
