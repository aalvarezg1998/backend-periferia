package com.aalvarezg.ms_user.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String profilePicture;
}
