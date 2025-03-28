package com.aalvarezg.ms_user.domain.usecase;

import com.aalvarezg.ms_user.domain.api.IUserServicePort;
import com.aalvarezg.ms_user.domain.model.User;
import com.aalvarezg.ms_user.domain.spi.IUserPersistencePort;
import com.aalvarezg.ms_user.domain.spi.IPasswordEncoderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort usuaioPersistencePort;
    private final IPasswordEncoderPort passwordEncoder;

    @Override
    public User saveUser(User user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        return this.usuaioPersistencePort.saveUser(user);
    }

    @Override
    public User getByUsername(String username) {
        return this.usuaioPersistencePort.getByUsername(username);
    }

}
