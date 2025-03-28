package com.aalvarezg.ms_publications.domain.usecase;

import com.aalvarezg.ms_publications.domain.api.ILikeServicePort;
import com.aalvarezg.ms_publications.domain.model.Like;
import com.aalvarezg.ms_publications.domain.spi.ILikePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeUseCase implements ILikeServicePort {

    private final ILikePersistencePort likePersistencePort;

    @Override
    public boolean addLikeToPost(Like like) {
        return likePersistencePort.addLikeToPost(like);
    }

    @Override
    public List<Like> getLikesByPost(Long postId) {
        return likePersistencePort.getLikeByPost(postId);
    }
}
