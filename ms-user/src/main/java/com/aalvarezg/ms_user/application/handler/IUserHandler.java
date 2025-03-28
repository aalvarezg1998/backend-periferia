package com.aalvarezg.ms_user.application.handler;

import com.aalvarezg.ms_user.application.dto.UserRequestDTO;
import com.aalvarezg.ms_user.application.dto.UserResponseDTO;

public interface IUserHandler {
    UserResponseDTO createUser(UserRequestDTO porpietarioRequest);
    UserResponseDTO getUserByUsername(String usernema);
}
