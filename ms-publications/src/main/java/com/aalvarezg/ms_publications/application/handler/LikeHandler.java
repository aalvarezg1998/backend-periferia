package com.aalvarezg.ms_publications.application.handler;

import com.aalvarezg.ms_publications.application.dto.LikeRequestDTO;
import com.aalvarezg.ms_publications.application.dto.PostRequestDTO;
import com.aalvarezg.ms_publications.application.dto.PostResponseDTO;
import com.aalvarezg.ms_publications.application.mapper.LikeMapper;
import com.aalvarezg.ms_publications.domain.api.ILikeServicePort;
import com.aalvarezg.ms_publications.domain.api.IPostServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeHandler implements ILikeHandler {
    private final ILikeServicePort likeServicePort;

    @Override
    public boolean addLikeToPost(LikeRequestDTO likeRequestDTO) {
        return likeServicePort.addLikeToPost(LikeMapper.likeRequestToLike(likeRequestDTO));
    }
}
