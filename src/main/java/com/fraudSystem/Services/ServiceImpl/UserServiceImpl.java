package com.fraudSystem.Services.ServiceImpl;


import com.fraudSystem.DTO.UserDto;
import com.fraudSystem.DTO.UserLogin;
import com.fraudSystem.Entity.User;
import com.fraudSystem.Exception.ResourceNotFoundException;
import com.fraudSystem.Repository.UserRepository;
import com.fraudSystem.Security.JwtUtil;
import com.fraudSystem.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User register(UserDto userDto) {

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole("USER");

        return this.userRepository.save(user);

    }

    @Override
    public String UserLogin(UserLogin userLogin) {

        User user = userRepository.findByEmail(userLogin.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User not Found"));

        if(!passwordEncoder.matches(userLogin.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Exception");
        }

        return jwtUtil.generateToken(user.getEmail());
    }


}
