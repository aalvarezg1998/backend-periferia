package com.aalvarezg.ms_user.application.handler;

import com.aalvarezg.ms_user.application.dto.UserRequestDTO;
import com.aalvarezg.ms_user.application.dto.UserResponseDTO;
import com.aalvarezg.ms_user.application.mapper.UsuarioMapper;
import com.aalvarezg.ms_user.domain.api.IUserServicePort;
import com.aalvarezg.ms_user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = UsuarioMapper.userRequestDTOToUser(userRequestDTO);
        User savedUser = userServicePort.saveUser(user);
        return UsuarioMapper.userToUserRequestDTO(savedUser);
    }

    @Override
    public UserResponseDTO getUserByUsername(String username) {
        return UsuarioMapper.userToUserRequestDTO(userServicePort.getByUsername(username));
    }

}
