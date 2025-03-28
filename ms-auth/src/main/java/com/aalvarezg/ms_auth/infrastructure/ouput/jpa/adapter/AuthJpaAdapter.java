package com.aalvarezg.ms_auth.infrastructure.ouput.jpa.adapter;

import com.aalvarezg.ms_auth.domain.model.User;
import com.aalvarezg.ms_auth.domain.spi.IAuthPersistencePort;
import com.aalvarezg.ms_auth.infrastructure.exception.NoDataFoundException;
import com.aalvarezg.ms_auth.infrastructure.ouput.jpa.entity.UserEntity;
import com.aalvarezg.ms_auth.infrastructure.ouput.jpa.mapper.UserEntityMapper;
import com.aalvarezg.ms_auth.infrastructure.ouput.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthJpaAdapter implements IAuthPersistencePort {
    private final IUserRepository userRepository;

    @Override
    public User getUser(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(NoDataFoundException::new);
        return UserEntityMapper.toUser(userEntity);
    }
}
