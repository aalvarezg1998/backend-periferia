package com.aalvarezg.ms_publications.infrastructure.ouput.jpa.adapter;

import com.aalvarezg.ms_publications.domain.model.Post;
import com.aalvarezg.ms_publications.domain.spi.IPostPersistencePort;
import com.aalvarezg.ms_publications.infrastructure.exception.NoDataFoundException;
import com.aalvarezg.ms_publications.infrastructure.ouput.jpa.entity.PostEntity;
import com.aalvarezg.ms_publications.infrastructure.ouput.jpa.mapper.PostEntityMapper;
import com.aalvarezg.ms_publications.infrastructure.ouput.jpa.repository.IPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostJpaAdapter implements IPostPersistencePort {
    private final IPostRepository postRepository;

    @Override
    public Post savePost(Post post) {
        PostEntity postEntity = postRepository.save(PostEntityMapper.toEntity(post));
        return PostEntityMapper.toPost(postEntity);
    }

    @Override
    public Post getById(Long id) {
        return postRepository.findById(id)
                .map(PostEntityMapper::toPost)
                .orElseThrow(NoDataFoundException::new);
    }

    @Override
    public Page<Post> getPost(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findAllByOrderByCreatedAtDesc(pageable).map(PostEntityMapper::toPost);
    }
}
