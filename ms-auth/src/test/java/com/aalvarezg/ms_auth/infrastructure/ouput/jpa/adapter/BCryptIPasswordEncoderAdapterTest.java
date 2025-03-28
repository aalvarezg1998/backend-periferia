package com.aalvarezg.ms_auth.infrastructure.ouput.jpa.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class BCryptIPasswordEncoderAdapterTest {

    private BCryptIPasswordEncoderAdapter passwordEncoderAdapter;
    private BCryptPasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        passwordEncoderAdapter = new BCryptIPasswordEncoderAdapter();
        encoder = new BCryptPasswordEncoder();
    }

    @Test
    void matches_ShouldReturnTrue_WhenPasswordsMatch() {
        // Arrange
        String rawPassword = "password123";
        String encodedPassword = encoder.encode(rawPassword); // Encode using the same encoder

        // Act
        boolean result = passwordEncoderAdapter.matches(rawPassword, encodedPassword);

        // Assert
        assertTrue(result);
    }

    @Test
    void matches_ShouldReturnFalse_WhenPasswordsDoNotMatch() {
        // Arrange
        String rawPassword = "password123";
        String encodedPassword = encoder.encode("differentPassword");

        // Act
        boolean result = passwordEncoderAdapter.matches(rawPassword, encodedPassword);

        // Assert
        assertFalse(result);
    }
}
