package com.aalvarezg.ms_auth.domain.spi;

public interface IPasswordEncoderPort {
    boolean matches(String rawPassword, String encodedPassword);
}
