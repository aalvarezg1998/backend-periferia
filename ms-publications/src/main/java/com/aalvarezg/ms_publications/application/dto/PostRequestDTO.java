package com.aalvarezg.ms_publications.application.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDTO {
    private String content;
    private String imageUrl;
    private Long userId;
    private String username;
}
