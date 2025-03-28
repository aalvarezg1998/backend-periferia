package com.aalvarezg.ms_publications.domain.spi;

import com.aalvarezg.ms_publications.domain.model.Like;

import java.util.List;

public interface ILikePersistencePort {
    boolean addLikeToPost(Like like);
    List<Like> getLikeByPost(Long postId);
}
