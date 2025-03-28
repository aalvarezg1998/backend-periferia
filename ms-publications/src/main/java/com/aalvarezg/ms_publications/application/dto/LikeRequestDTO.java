package com.aalvarezg.ms_publications.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeRequestDTO {
    private Long userId;
    private Long postId;
}
