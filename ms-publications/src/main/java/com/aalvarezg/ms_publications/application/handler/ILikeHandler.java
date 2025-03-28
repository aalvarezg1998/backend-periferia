package com.aalvarezg.ms_publications.application.handler;

import com.aalvarezg.ms_publications.application.dto.LikeRequestDTO;

public interface ILikeHandler {
    boolean addLikeToPost(LikeRequestDTO likeRequestDTO);
}
