package com.aalvarezg.ms_auth.domain.usecase;

import com.aalvarezg.ms_auth.domain.api.IAuthServicePort;
import com.aalvarezg.ms_auth.domain.model.User;
import com.aalvarezg.ms_auth.domain.spi.IAuthPersistencePort;
import com.aalvarezg.ms_auth.domain.spi.IPasswordEncoderPort;
import com.aalvarezg.ms_auth.infrastructure.exception.NoDataFoundException;
import com.aalvarezg.ms_auth.infrastructure.exception.UserInvalidCredentialsException;
import com.aalvarezg.ms_auth.infrastructure.security.IJwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUseCase implements IAuthServicePort {
    private final IAuthPersistencePort authPersistencePort;
    private final IPasswordEncoderPort passwordEncoder;
    private final IJwtProvider jwtProvider;

    @Override
    public String login(User userParam) {
        final User user = authPersistencePort.getUser(userParam.getUsername());

        if (user == null) {
            throw new NoDataFoundException();
        }

        if (!passwordEncoder.matches(userParam.getPassword(), user.getPassword())) {
            throw new UserInvalidCredentialsException();
        }

        return jwtProvider.generateToken(user.getUsername());
    }
}
