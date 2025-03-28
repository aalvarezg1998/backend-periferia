package com.aalvarezg.ms_user.application.handler;

import com.aalvarezg.ms_user.application.dto.UserRequestDTO;
import com.aalvarezg.ms_user.application.dto.UserResponseDTO;
import com.aalvarezg.ms_user.application.mapper.UsuarioMapper;
import com.aalvarezg.ms_user.domain.api.IUserServicePort;
import com.aalvarezg.ms_user.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserHandlerTest {

    @Mock
    private IUserServicePort userServicePort;

    @InjectMocks
    private UserHandler userHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        // Arrange
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername("user123");
        userRequestDTO.setPassword("plainPassword");

        User user = new User();
        user.setUsername("user123");
        user.setPassword("encodedPassword");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("user123");
        savedUser.setPassword("encodedPassword");

        when(userServicePort.saveUser(any(User.class))).thenReturn(savedUser);

        // Act
        UserResponseDTO result = userHandler.createUser(userRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals("user123", result.getUsername());
        assertEquals(1L, result.getId());
        verify(userServicePort, times(1)).saveUser(any(User.class));
    }

    @Test
    void testGetUserByUsername() {
        // Arrange
        String username = "user123";

        User user = new User();
        user.setId(1L);
        user.setUsername(username);
        user.setPassword("encodedPassword");

        when(userServicePort.getByUsername(username)).thenReturn(user);

        // Act
        UserResponseDTO result = userHandler.getUserByUsername(username);

        // Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        assertEquals(1L, result.getId());
        verify(userServicePort, times(1)).getByUsername(username);
    }
}
