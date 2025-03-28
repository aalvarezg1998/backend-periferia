package com.aalvarezg.ms_auth.domain.api;

import com.aalvarezg.ms_auth.domain.model.User;

public interface IAuthServicePort {
    String login(User user);
}
