package com.aalvarezg.ms_publications.domain.usecase;

import com.aalvarezg.ms_publications.domain.model.Like;
import com.aalvarezg.ms_publications.domain.spi.ILikePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class LikeUseCaseTest {

    @Mock
    private ILikePersistencePort likePersistencePort;

    @InjectMocks
    private LikeUseCase likeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addLikeToPost_ShouldReturnTrue_WhenLikeIsAddedSuccessfully() {
        // Arrange
        Like like = new Like();
        like.setId(1L);
        like.setPostId(1L);

        when(likePersistencePort.addLikeToPost(any(Like.class))).thenReturn(true);

        // Act
        boolean result = likeUseCase.addLikeToPost(like);

        // Assert
        assertTrue(result);
        verify(likePersistencePort, times(1)).addLikeToPost(any(Like.class));
    }

    @Test
    void getLikesByPost_ShouldReturnListOfLikes() {
        // Arrange
        Like like1 = new Like();
        like1.setId(1L);
        like1.setPostId(1L);

        Like like2 = new Like();
        like2.setId(2L);
        like2.setPostId(1L);

        List<Like> likes = Arrays.asList(like1, like2);

        when(likePersistencePort.getLikeByPost(anyLong())).thenReturn(likes);

        // Act
        List<Like> result = likeUseCase.getLikesByPost(1L);

        // Assert
        assertEquals(2, result.size());
        assertEquals(like1, result.get(0));
        assertEquals(like2, result.get(1));
        verify(likePersistencePort, times(1)).getLikeByPost(1L);
    }
}
