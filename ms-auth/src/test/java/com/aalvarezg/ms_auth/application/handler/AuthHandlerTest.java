package com.aalvarezg.ms_auth.application.handler;

import com.aalvarezg.ms_auth.application.dto.UserRequestDTO;
import com.aalvarezg.ms_auth.application.dto.UserResponseDTO;
import com.aalvarezg.ms_auth.application.mapper.UserMapper;
import com.aalvarezg.ms_auth.domain.api.IAuthServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthHandlerTest {

    @Mock
    private IAuthServicePort authServicePort;

    @InjectMocks
    private AuthHandler authHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_ShouldReturnToken_WhenCredentialsAreValid() {
        // Arrange
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername("username");
        userRequestDTO.setPassword("password");

        String expectedToken = "generated-token";

        // Mocking
        when(authServicePort.login(any())).thenReturn(expectedToken);

        // Act
        UserResponseDTO result = authHandler.login(userRequestDTO);

        // Assert
        assertEquals(expectedToken, result.getToken());
        verify(authServicePort, times(1)).login(any());
    }
}

