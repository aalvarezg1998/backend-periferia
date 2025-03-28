package com.aalvarezg.ms_publications.infrastructure.ouput.jpa.repository;

import com.aalvarezg.ms_publications.infrastructure.ouput.jpa.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPostRepository extends JpaRepository<PostEntity, Long> {
    Optional<PostEntity> findByUsername(String username);

    Page<PostEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
