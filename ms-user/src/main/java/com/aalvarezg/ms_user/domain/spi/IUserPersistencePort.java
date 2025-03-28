package com.aalvarezg.ms_user.domain.spi;

import com.aalvarezg.ms_user.domain.model.User;

public interface IUserPersistencePort {
    User saveUser(User user);
    User getByUsername(String username);
}
