package com.aalvarezg.ms_publications.application.dto;

import com.aalvarezg.ms_publications.domain.model.Like;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostResponseDTO {
    private Long postId;
    private String content;
    private String imageUrl;
    private Long userId;
    private String username;
    private LocalDateTime createdAt;
    private List<Like> likes;
}
