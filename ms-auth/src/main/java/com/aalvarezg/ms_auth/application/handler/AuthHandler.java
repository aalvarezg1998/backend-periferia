package com.aalvarezg.ms_auth.application.handler;

import com.aalvarezg.ms_auth.application.dto.UserRequestDTO;
import com.aalvarezg.ms_auth.application.dto.UserResponseDTO;
import com.aalvarezg.ms_auth.application.mapper.UserMapper;
import com.aalvarezg.ms_auth.domain.api.IAuthServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthHandler implements IAuthHandler {
    private final IAuthServicePort authServicePort;

    @Override
    public UserResponseDTO login(UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setToken(authServicePort.login(UserMapper.userRequestToUser(userRequestDTO)));
        return userResponseDTO;
    }
}
