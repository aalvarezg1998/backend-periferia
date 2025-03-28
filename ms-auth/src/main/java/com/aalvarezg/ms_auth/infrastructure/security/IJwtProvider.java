package com.aalvarezg.ms_auth.infrastructure.security;

public interface IJwtProvider {
    String generateToken(String username);
    boolean validateToken(String token);
    String extractUsername(String token);
}
