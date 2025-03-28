package com.aalvarezg.ms_user.domain.usecase;

import com.aalvarezg.ms_user.domain.model.User;
import com.aalvarezg.ms_user.domain.spi.IUserPersistencePort;
import com.aalvarezg.ms_user.domain.spi.IPasswordEncoderPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private IUserPersistencePort usuarioPersistencePort;

    @Mock
    private IPasswordEncoderPort passwordEncoder;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setUsername("user123");
        user.setPassword("plainPassword");

        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode("plainPassword")).thenReturn(encodedPassword);

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("user123");
        savedUser.setPassword(encodedPassword);

        when(usuarioPersistencePort.saveUser(any(User.class))).thenReturn(savedUser);

        // Act
        User result = userUseCase.saveUser(user);

        // Assert
        assertNotNull(result);
        assertEquals("user123", result.getUsername());
        assertEquals(encodedPassword, result.getPassword());
        verify(passwordEncoder, times(1)).encode("plainPassword");
        verify(usuarioPersistencePort, times(1)).saveUser(any(User.class));
    }

    @Test
    void testGetByUsername() {
        // Arrange
        String username = "user123";

        User user = new User();
        user.setId(1L);
        user.setUsername(username);
        user.setPassword("encodedPassword");

        when(usuarioPersistencePort.getByUsername(username)).thenReturn(user);

        // Act
        User result = userUseCase.getByUsername(username);

        // Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        verify(usuarioPersistencePort, times(1)).getByUsername(username);
    }
}
