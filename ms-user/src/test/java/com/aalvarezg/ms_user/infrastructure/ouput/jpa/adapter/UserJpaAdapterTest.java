package com.aalvarezg.ms_user.infrastructure.ouput.jpa.adapter;

import com.aalvarezg.ms_user.domain.model.User;
import com.aalvarezg.ms_user.infrastructure.exception.NoDataFoundException;
import com.aalvarezg.ms_user.infrastructure.exception.UserAlreadyExitsException;
import com.aalvarezg.ms_user.infrastructure.ouput.jpa.entity.UserEntity;
import com.aalvarezg.ms_user.infrastructure.ouput.jpa.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserJpaAdapterTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser_Success() {
        // Arrange
        User user = new User();
        user.setUsername("user123");
        user.setEmail("user123@example.com");
        user.setPassword("encodedPassword");

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("user123");
        userEntity.setEmail("user123@example.com");
        userEntity.setPassword("encodedPassword");

        when(userRepository.findByUsernameAndEmail(user.getUsername(), user.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        // Act
        User result = userJpaAdapter.saveUser(user);

        // Assert
        assertNotNull(result);
        assertEquals("user123", result.getUsername());
        assertEquals("user123@example.com", result.getEmail());
        verify(userRepository, times(1)).findByUsernameAndEmail("user123", "user123@example.com");
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void testSaveUser_UserAlreadyExists() {
        // Arrange
        User user = new User();
        user.setUsername("user123");
        user.setEmail("user123@example.com");
        user.setPassword("encodedPassword");

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("user123");
        userEntity.setEmail("user123@example.com");
        userEntity.setPassword("encodedPassword");

        when(userRepository.findByUsernameAndEmail(user.getUsername(), user.getEmail())).thenReturn(Optional.of(userEntity));

        // Act & Assert
        assertThrows(UserAlreadyExitsException.class, () -> userJpaAdapter.saveUser(user));
        verify(userRepository, times(1)).findByUsernameAndEmail("user123", "user123@example.com");
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void testGetByUsername_Success() {
        // Arrange
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("user123");
        userEntity.setEmail("user123@example.com");
        userEntity.setPassword("encodedPassword");

        when(userRepository.findByUsername("user123")).thenReturn(Optional.of(userEntity));

        // Act
        User result = userJpaAdapter.getByUsername("user123");

        // Assert
        assertNotNull(result);
        assertEquals("user123", result.getUsername());
        assertEquals("user123@example.com", result.getEmail());
        verify(userRepository, times(1)).findByUsername("user123");
    }

    @Test
    void testGetByUsername_NoDataFound() {
        // Arrange
        when(userRepository.findByUsername("user123")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> userJpaAdapter.getByUsername("user123"));
        verify(userRepository, times(1)).findByUsername("user123");
    }
}
