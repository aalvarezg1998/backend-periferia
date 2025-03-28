package com.aalvarezg.ms_auth.infrastructure.ouput.jpa.adapter;

import com.aalvarezg.ms_auth.domain.model.User;
import com.aalvarezg.ms_auth.infrastructure.exception.NoDataFoundException;
import com.aalvarezg.ms_auth.infrastructure.ouput.jpa.entity.UserEntity;
import com.aalvarezg.ms_auth.infrastructure.ouput.jpa.mapper.UserEntityMapper;
import com.aalvarezg.ms_auth.infrastructure.ouput.jpa.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthJpaAdapterTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private AuthJpaAdapter authJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUser_ShouldReturnUser_WhenUserExists() {
        // Arrange
        String username = "testUser";
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword("testPassword");

        User expectedUser = UserEntityMapper.toUser(userEntity);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(userEntity));

        // Act
        User result = authJpaAdapter.getUser(username);

        // Assert
        assertEquals(expectedUser.getUsername(), result.getUsername());
        assertEquals(expectedUser.getPassword(), result.getPassword());
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void getUser_ShouldThrowNoDataFoundException_WhenUserDoesNotExist() {
        // Arrange
        String username = "nonExistentUser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> authJpaAdapter.getUser(username));
        verify(userRepository, times(1)).findByUsername(username);
    }
}
