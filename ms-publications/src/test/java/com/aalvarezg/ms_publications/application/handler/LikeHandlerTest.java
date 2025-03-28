package com.aalvarezg.ms_publications.application.handler;

import com.aalvarezg.ms_publications.application.dto.LikeRequestDTO;
import com.aalvarezg.ms_publications.application.mapper.LikeMapper;
import com.aalvarezg.ms_publications.domain.api.ILikeServicePort;
import com.aalvarezg.ms_publications.domain.model.Like;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class LikeHandlerTest {

    @Mock
    private ILikeServicePort likeServicePort;

    @InjectMocks
    private LikeHandler likeHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addLikeToPost_ShouldReturnTrue() {
        // Arrange
        LikeRequestDTO likeRequestDTO = new LikeRequestDTO();
        likeRequestDTO.setPostId(1L);
        likeRequestDTO.setUserId(1L);

        Like like = LikeMapper.likeRequestToLike(likeRequestDTO);

        when(likeServicePort.addLikeToPost(any(Like.class))).thenReturn(true);

        // Act
        boolean result = likeHandler.addLikeToPost(likeRequestDTO);

        // Assert
        assertTrue(result);
        verify(likeServicePort, times(1)).addLikeToPost(any(Like.class));
    }
}
