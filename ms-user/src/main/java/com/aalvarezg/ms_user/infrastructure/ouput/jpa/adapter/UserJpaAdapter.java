package com.aalvarezg.ms_user.infrastructure.ouput.jpa.adapter;

import com.aalvarezg.ms_user.domain.model.User;
import com.aalvarezg.ms_user.domain.spi.IUserPersistencePort;
import com.aalvarezg.ms_user.infrastructure.exception.NoDataFoundException;
import com.aalvarezg.ms_user.infrastructure.exception.UserAlreadyExitsException;
import com.aalvarezg.ms_user.infrastructure.ouput.jpa.entity.UserEntity;
import com.aalvarezg.ms_user.infrastructure.ouput.jpa.mapper.UserEntityMapper;
import com.aalvarezg.ms_user.infrastructure.ouput.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;

    @Override
    public User saveUser(User user) {
        userRepository.findByUsernameAndEmail(user.getUsername(), user.getEmail()).ifPresent(userEntity -> {
            throw new UserAlreadyExitsException();
        });
        UserEntity userEntity = userRepository.save(UserEntityMapper.toEntity(user));
        return UserEntityMapper.toUsuario(userEntity);
    }

    @Override
    public User getByUsername(String id) {
        return userRepository.findByUsername(id)
                .map(UserEntityMapper::toUsuario)
                .orElseThrow(NoDataFoundException::new);
    }
}
