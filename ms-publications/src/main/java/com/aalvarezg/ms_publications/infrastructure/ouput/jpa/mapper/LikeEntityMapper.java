package com.aalvarezg.ms_publications.infrastructure.ouput.jpa.mapper;

import com.aalvarezg.ms_publications.domain.model.Like;
import com.aalvarezg.ms_publications.infrastructure.ouput.jpa.entity.LikeEntity;

public class LikeEntityMapper {
    private LikeEntityMapper(){
        throw new IllegalStateException("Mapper para Likes dados a un post");
    }

    public static LikeEntity toEntity(Like like){
        LikeEntity likeEntity =  new LikeEntity();
        likeEntity.setId(like.getId());
        likeEntity.setPostId(like.getPostId());
        likeEntity.setUserId(like.getUserId());
        return likeEntity;
    }

    public static Like toLike(LikeEntity likeEntity){
        Like like =  new Like();
        like.setId(likeEntity.getId());
        like.setUserId(likeEntity.getUserId());
        like.setPostId(likeEntity.getPostId());
        return like;
    }

}
