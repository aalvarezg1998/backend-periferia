package com.aalvarezg.ms_auth.application.handler;

import com.aalvarezg.ms_auth.application.dto.UserRequestDTO;
import com.aalvarezg.ms_auth.application.dto.UserResponseDTO;

public interface IAuthHandler {
    UserResponseDTO login(UserRequestDTO userRequestDTO);
}
