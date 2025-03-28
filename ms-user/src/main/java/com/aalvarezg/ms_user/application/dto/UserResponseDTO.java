package com.aalvarezg.ms_user.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String profilePicture;
}
