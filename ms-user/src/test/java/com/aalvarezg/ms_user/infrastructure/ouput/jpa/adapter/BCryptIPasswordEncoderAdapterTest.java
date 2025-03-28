package com.aalvarezg.ms_user.infrastructure.ouput.jpa.adapter;

import com.aalvarezg.ms_user.domain.spi.IPasswordEncoderPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BCryptIPasswordEncoderAdapterTest {

    private IPasswordEncoderPort passwordEncoder;

    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptIPasswordEncoderAdapter();
    }

    @Test
    void testEncodePassword() {
        // Arrange
        String rawPassword = "myPassword123";

        // Act
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // Assert
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword); // Verificamos que el password no se mantenga igual
    }

    @Test
    void testMatches_CorrectPassword() {
        // Arrange
        String rawPassword = "myPassword123";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // Act
        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);

        // Assert
        assertTrue(matches); // La contraseña debe coincidir
    }

    @Test
    void testMatches_IncorrectPassword() {
        // Arrange
        String rawPassword = "myPassword123";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // Act
        boolean matches = passwordEncoder.matches("wrongPassword", encodedPassword);

        // Assert
        assertFalse(matches); // La contraseña no debe coincidir
    }
}
