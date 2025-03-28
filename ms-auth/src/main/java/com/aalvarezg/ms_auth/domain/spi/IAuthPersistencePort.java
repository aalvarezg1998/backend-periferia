package com.aalvarezg.ms_auth.domain.spi;

import com.aalvarezg.ms_auth.domain.model.User;

public interface IAuthPersistencePort {
    User getUser(String username);
}
