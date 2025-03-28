package com.aalvarezg.ms_auth.infrastructure.ouput.jpa.repository;

import com.aalvarezg.ms_auth.infrastructure.ouput.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
