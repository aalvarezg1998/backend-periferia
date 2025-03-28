package com.aalvarezg.ms_user.domain.api;

import com.aalvarezg.ms_user.domain.model.User;

public interface IUserServicePort {
    User saveUser(User user);
    User getByUsername(String username);
}
