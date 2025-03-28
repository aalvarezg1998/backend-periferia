package com.aalvarezg.ms_user.infrastructure.configuration;

import com.aalvarezg.ms_user.domain.spi.IPasswordEncoderPort;
import com.aalvarezg.ms_user.infrastructure.ouput.jpa.entity.UserEntity;
import com.aalvarezg.ms_user.infrastructure.ouput.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserSeeder implements CommandLineRunner {
    private final IUserRepository userRepository;
    private final IPasswordEncoderPort passwordEncoderPort;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            createTestUsers();
        }
    }

    private void createTestUsers() {
        List<UserEntity> users = Arrays.asList(
                createUser("malu19", "malu19@example.com", "password", "Mayra Luz"),
                createUser("aalvarezg29", "aalvarezg@example.com", "password", "Alexander Alvarez")
        );

        userRepository.saveAll(users);
        log.info("Users created successfully!");
    }

    private UserEntity createUser(String username, String email, String password, String fullName) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoderPort.encode(password));
        user.setFullName(fullName);
        user.setProfilePicture("https://www.gravatar.com/avatar/" + username.hashCode() + "?d=identicon");
        return user;
    }
}
