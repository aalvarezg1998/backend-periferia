package com.aalvarezg.ms_user.application.mapper;

import com.aalvarezg.ms_user.application.dto.UserRequestDTO;
import com.aalvarezg.ms_user.application.dto.UserResponseDTO;
import com.aalvarezg.ms_user.domain.model.User;

public class UsuarioMapper {

    private UsuarioMapper(){
        throw new IllegalStateException("Mapper para usuarios");
    }
    public static UserResponseDTO userToUserRequestDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setFullName(user.getFullName());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setId(user.getId());
        userResponseDTO.setProfilePicture(user.getProfilePicture());
        return userResponseDTO;
    }

    public static User userRequestDTOToUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setProfilePicture(userRequestDTO.getProfilePicture());
        user.setFullName(userRequestDTO.getFullName());
        return user;
    }
}
