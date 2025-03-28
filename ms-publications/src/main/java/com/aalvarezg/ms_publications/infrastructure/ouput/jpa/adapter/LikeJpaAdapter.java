package com.aalvarezg.ms_publications.infrastructure.ouput.jpa.adapter;

import com.aalvarezg.ms_publications.domain.model.Like;
import com.aalvarezg.ms_publications.domain.spi.ILikePersistencePort;
import com.aalvarezg.ms_publications.infrastructure.ouput.jpa.entity.LikeEntity;
import com.aalvarezg.ms_publications.infrastructure.ouput.jpa.mapper.LikeEntityMapper;
import com.aalvarezg.ms_publications.infrastructure.ouput.jpa.repository.ILikeRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeJpaAdapter implements ILikePersistencePort {
    private final ILikeRepository likeRepository;

    @Override
    public boolean addLikeToPost(Like like) {
        List<LikeEntity> likeEntities = likeRepository.findByUserIdAndPostId(like.getUserId(), like.getPostId());
        if (!likeEntities.isEmpty()){
            LikeEntity likeEntity = likeEntities.get(0);
            likeRepository.delete(likeEntity);
            return false;
        }
        likeRepository.save(LikeEntityMapper.toEntity(like));
        return true;
    }

    @Override
    public List<Like> getLikeByPost(Long postId) {
        return likeRepository.getLikeEntityByPostId(postId).stream().map(LikeEntityMapper::toLike).collect(Collectors.toList());
    }
}
