package com.aalvarezg.ms_publications.application.mapper;

import com.aalvarezg.ms_publications.application.dto.PostRequestDTO;
import com.aalvarezg.ms_publications.application.dto.PostResponseDTO;
import com.aalvarezg.ms_publications.domain.model.Like;
import com.aalvarezg.ms_publications.domain.model.Post;

import java.util.List;

public class PostMapper {

    private PostMapper(){
        throw new IllegalStateException("Mapper para publicaciones");
    }
    public static PostResponseDTO postToPostRequest(Post post, List<Like> likes) {
        PostResponseDTO postResponseDTO = new PostResponseDTO();
        postResponseDTO.setPostId(post.getId());
        postResponseDTO.setContent(post.getContent());
        postResponseDTO.setUsername(post.getUsername());
        postResponseDTO.setImageUrl(post.getImageUrl());
        postResponseDTO.setCreatedAt(post.getCreatedAt());
        postResponseDTO.setLikes(likes);
        return postResponseDTO;
    }

    public static Post postRequestToPost(PostRequestDTO postRequest) {
        Post post = new Post();
        post.setContent(postRequest.getContent());
        post.setUsername(postRequest.getUsername());
        post.setImageUrl(postRequest.getImageUrl());
        post.setUserId(postRequest.getUserId());
        return post;
    }
}
