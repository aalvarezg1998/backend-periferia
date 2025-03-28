package com.aalvarezg.ms_user.infrastructure.ouput.jpa.repository;

import com.aalvarezg.ms_user.infrastructure.ouput.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsernameAndEmail(String username, String correo);
    Optional<UserEntity> findByUsername(String username);
}
