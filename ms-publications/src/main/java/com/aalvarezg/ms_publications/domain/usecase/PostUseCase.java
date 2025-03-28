package com.aalvarezg.ms_publications.domain.usecase;

import com.aalvarezg.ms_publications.domain.api.IPostServicePort;
import com.aalvarezg.ms_publications.domain.model.Post;
import com.aalvarezg.ms_publications.domain.spi.IPostPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostUseCase implements IPostServicePort {

    private final IPostPersistencePort postPersistencePort;

    @Override
    public Post savePost(Post post) {
        return this.postPersistencePort.savePost(post);
    }

    @Override
    public Post getById(Long id) {
        return this.postPersistencePort.getById(id);
    }

    @Override
    public Page<Post> getPost(int page, int size) {
        return this.postPersistencePort.getPost(page,size);
    }

}
