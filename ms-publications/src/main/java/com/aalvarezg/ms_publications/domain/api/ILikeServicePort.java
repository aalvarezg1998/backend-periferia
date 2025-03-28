package com.aalvarezg.ms_publications.domain.api;

import com.aalvarezg.ms_publications.domain.model.Like;

import java.util.List;

public interface ILikeServicePort {
    boolean addLikeToPost(Like like);
    List<Like> getLikesByPost(Long postId);
}
