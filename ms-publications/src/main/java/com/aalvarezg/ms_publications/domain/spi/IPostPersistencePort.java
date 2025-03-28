package com.aalvarezg.ms_publications.domain.spi;

import com.aalvarezg.ms_publications.domain.model.Post;
import org.springframework.data.domain.Page;

public interface IPostPersistencePort {
    Post savePost(Post post);
    Post getById(Long id);
    Page<Post> getPost(int page, int size);
}
