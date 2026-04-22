package com.fraudSystem.Services;

import com.fraudSystem.DTO.UserDto;
import com.fraudSystem.Entity.User;

public interface UserService {

    User register(UserDto userDto);

}
