package com.aalvarezg.ms_publications.domain.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Post {
    private Long id;
    private String content;
    private String imageUrl;
    private Long userId;
    private String username;
    private int likes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
