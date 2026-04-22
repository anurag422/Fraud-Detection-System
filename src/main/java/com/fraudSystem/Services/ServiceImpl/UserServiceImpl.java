package com.fraudSystem.Services.ServiceImpl;


import com.fraudSystem.DTO.UserDto;
import com.fraudSystem.Entity.User;
import com.fraudSystem.Repository.UserRepository;
import com.fraudSystem.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User register(UserDto userDto) {

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole("USER");

        return this.userRepository.save(user);

    }
}
