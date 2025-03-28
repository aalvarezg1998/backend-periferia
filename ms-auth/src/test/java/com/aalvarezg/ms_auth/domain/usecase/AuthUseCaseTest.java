package com.aalvarezg.ms_auth.domain.usecase;

import static org.junit.jupiter.api.Assertions.*;
import com.aalvarezg.ms_auth.domain.model.User;
import com.aalvarezg.ms_auth.domain.spi.IAuthPersistencePort;
import com.aalvarezg.ms_auth.domain.spi.IPasswordEncoderPort;
import com.aalvarezg.ms_auth.infrastructure.exception.NoDataFoundException;
import com.aalvarezg.ms_auth.infrastructure.exception.UserInvalidCredentialsException;
import com.aalvarezg.ms_auth.infrastructure.security.IJwtProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class AuthUseCaseTest {

    @Mock
    private IAuthPersistencePort authPersistencePort;

    @Mock
    private IPasswordEncoderPort passwordEncoder;

    @Mock
    private IJwtProvider jwtProvider;

    private AuthUseCase authUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authUseCase = new AuthUseCase(authPersistencePort, passwordEncoder, jwtProvider);
    }

    @Test
    void login_Successful() {
        // Arrange
        // Crear el objeto User con setters
        User userParam = new User();
        userParam.setUsername("username");
        userParam.setPassword("password");

        User storedUser = new User();
        storedUser.setUsername("username");
        storedUser.setPassword("encodedPassword");

        when(authPersistencePort.getUser(userParam.getUsername())).thenReturn(storedUser);
        when(passwordEncoder.matches(userParam.getPassword(), storedUser.getPassword())).thenReturn(true);
        when(jwtProvider.generateToken(storedUser.getUsername())).thenReturn("jwtToken");

        // Act
        String result = authUseCase.login(userParam);

        // Assert
        assertEquals("jwtToken", result);
        verify(authPersistencePort, times(1)).getUser(userParam.getUsername());
        verify(passwordEncoder, times(1)).matches(userParam.getPassword(), storedUser.getPassword());
        verify(jwtProvider, times(1)).generateToken(storedUser.getUsername());
    }

    @Test
    void login_UserNotFound_ThrowsNoDataFoundException() {
        // Arrange
        User userParam = new User();
        userParam.setUsername("username");
        userParam.setPassword("password");

        when(authPersistencePort.getUser(userParam.getUsername())).thenReturn(null);

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> authUseCase.login(userParam));
        verify(authPersistencePort, times(1)).getUser(userParam.getUsername());
        verify(passwordEncoder, never()).matches(anyString(), anyString());
        verify(jwtProvider, never()).generateToken(anyString());
    }

    @Test
    void login_InvalidPassword_ThrowsUserInvalidCredentialsException() {
        // Arrange
        // Crear el objeto User con setters
        User userParam = new User();
        userParam.setUsername("username");
        userParam.setPassword("password");

        User storedUser = new User();
        storedUser.setUsername("username");
        storedUser.setPassword("encodedPassword");

        when(authPersistencePort.getUser(userParam.getUsername())).thenReturn(storedUser);
        when(passwordEncoder.matches(userParam.getPassword(), storedUser.getPassword())).thenReturn(false);

        // Act & Assert
        assertThrows(UserInvalidCredentialsException.class, () -> authUseCase.login(userParam));
        verify(authPersistencePort, times(1)).getUser(userParam.getUsername());
        verify(passwordEncoder, times(1)).matches(userParam.getPassword(), storedUser.getPassword());
        verify(jwtProvider, never()).generateToken(anyString());
    }
}
