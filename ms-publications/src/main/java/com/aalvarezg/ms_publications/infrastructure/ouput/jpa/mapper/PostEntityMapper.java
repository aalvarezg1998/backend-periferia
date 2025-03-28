package com.aalvarezg.ms_publications.infrastructure.ouput.jpa.mapper;

import com.aalvarezg.ms_publications.domain.model.Post;
import com.aalvarezg.ms_publications.infrastructure.ouput.jpa.entity.PostEntity;

public class PostEntityMapper {

    private PostEntityMapper(){
        throw new IllegalStateException("Mapper para Posteos o publicaciones");
    }
    public static PostEntity toEntity(Post post){
        PostEntity postEntity = new PostEntity();
        postEntity.setId(post.getId());
        postEntity.setContent(post.getContent());
        postEntity.setUsername(post.getUsername());
        postEntity.setImageUrl(post.getImageUrl());
        postEntity.setUserId(post.getUserId());
        return postEntity;
    }

    public static Post toPost(PostEntity postEntity){
        Post post = new Post();
        post.setId(postEntity.getId());
        post.setContent(postEntity.getContent());
        post.setUsername(postEntity.getUsername());
        post.setImageUrl(postEntity.getImageUrl());
        post.setUserId(postEntity.getUserId());
        post.setCreatedAt(postEntity.getCreatedAt());
        return post;
    }
}
