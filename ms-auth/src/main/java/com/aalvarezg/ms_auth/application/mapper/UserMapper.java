package com.aalvarezg.ms_auth.application.mapper;

import com.aalvarezg.ms_auth.application.dto.UserRequestDTO;
import com.aalvarezg.ms_auth.domain.model.User;

public class UserMapper {

    private UserMapper(){
        throw new IllegalStateException("Mapper para usuarios");
    }
    public static UserRequestDTO userToUserRequest(User user) {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setPassword(user.getPassword());
        userRequestDTO.setUsername(user.getUsername());
        return userRequestDTO;
    }

    public static User userRequestToUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setPassword(userRequestDTO.getPassword());
        user.setUsername(userRequestDTO.getUsername());
        return user;
    }
}
