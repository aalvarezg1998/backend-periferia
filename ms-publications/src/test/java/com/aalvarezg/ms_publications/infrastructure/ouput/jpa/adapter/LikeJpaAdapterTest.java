package com.aalvarezg.ms_publications.infrastructure.ouput.jpa.adapter;

import com.aalvarezg.ms_publications.domain.model.Like;
import com.aalvarezg.ms_publications.infrastructure.ouput.jpa.entity.LikeEntity;
import com.aalvarezg.ms_publications.infrastructure.ouput.jpa.mapper.LikeEntityMapper;
import com.aalvarezg.ms_publications.infrastructure.ouput.jpa.repository.ILikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LikeJpaAdapterTest {

    @Mock
    private ILikeRepository likeRepository;

    @InjectMocks
    private LikeJpaAdapter likeJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddLikeToPost_NewLike() {
        // Arrange
        Like like = new Like();
        like.setUserId(1L);
        like.setPostId(2L);

        when(likeRepository.findByUserIdAndPostId(1L, 2L)).thenReturn(new ArrayList<>());
        when(likeRepository.save(any(LikeEntity.class))).thenReturn(LikeEntityMapper.toEntity(like));

        // Act
        boolean result = likeJpaAdapter.addLikeToPost(like);

        // Assert
        assertTrue(result);
        verify(likeRepository, times(1)).findByUserIdAndPostId(1L, 2L);
        verify(likeRepository, times(1)).save(any(LikeEntity.class));
        verify(likeRepository, never()).delete(any(LikeEntity.class));
    }

    @Test
    void testAddLikeToPost_RemoveExistingLike() {
        // Arrange
        Like like = new Like();
        like.setUserId(1L);
        like.setPostId(2L);

        LikeEntity existingLikeEntity = LikeEntityMapper.toEntity(like);
        List<LikeEntity> existingLikes = List.of(existingLikeEntity);

        when(likeRepository.findByUserIdAndPostId(1L, 2L)).thenReturn(existingLikes);

        // Act
        boolean result = likeJpaAdapter.addLikeToPost(like);

        // Assert
        assertFalse(result);
        verify(likeRepository, times(1)).findByUserIdAndPostId(1L, 2L);
        verify(likeRepository, times(1)).delete(existingLikeEntity);
        verify(likeRepository, never()).save(any(LikeEntity.class));
    }

    @Test
    void testGetLikeByPost() {
        // Arrange
        Long postId = 2L;

        LikeEntity likeEntity1 = new LikeEntity();
        likeEntity1.setId(1L);
        likeEntity1.setUserId(1L);
        likeEntity1.setPostId(postId);

        LikeEntity likeEntity2 = new LikeEntity();
        likeEntity2.setId(2L);
        likeEntity2.setUserId(2L);
        likeEntity2.setPostId(postId);

        List<LikeEntity> likeEntities = List.of(likeEntity1, likeEntity2);

        when(likeRepository.getLikeEntityByPostId(postId)).thenReturn(likeEntities);

        // Act
        List<Like> likes = likeJpaAdapter.getLikeByPost(postId);

        // Assert
        assertEquals(2, likes.size());
        assertEquals(1L, likes.get(0).getUserId());
        assertEquals(2L, likes.get(1).getUserId());
        verify(likeRepository, times(1)).getLikeEntityByPostId(postId);
    }
}
