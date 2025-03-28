package com.aalvarezg.ms_publications.infrastructure.ouput.jpa.repository;


import com.aalvarezg.ms_publications.infrastructure.ouput.jpa.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ILikeRepository extends JpaRepository<LikeEntity, Long> {
    Optional<LikeEntity> findByUserId(Long nombreRol);
    List<LikeEntity> findByUserIdAndPostId(Long userId, Long postId);
    List<LikeEntity>  getLikeEntityByPostId(Long postId);
}
