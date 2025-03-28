package com.aalvarezg.ms_publications.application.mapper;

import com.aalvarezg.ms_publications.application.dto.LikeRequestDTO;
import com.aalvarezg.ms_publications.application.dto.LikeResponseDTO;
import com.aalvarezg.ms_publications.domain.model.Like;

public class LikeMapper {

    private LikeMapper(){
        throw new IllegalStateException("Mapper para los likes");
    }
    public static LikeResponseDTO likeToLikeRequest(Like like) {
        LikeResponseDTO likeResponseDTO = new LikeResponseDTO();
        likeResponseDTO.setUserId(like.getUserId());
        likeResponseDTO.setPostId(like.getPostId());
        return likeResponseDTO;
    }

    public static Like likeRequestToLike(LikeRequestDTO likeRequestDTO) {
        Like like = new Like();
        like.setUserId(likeRequestDTO.getUserId());
        like.setPostId(likeRequestDTO.getPostId());
        return like;
    }
}
