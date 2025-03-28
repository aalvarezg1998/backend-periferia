package com.aalvarezg.ms_publications.infrastructure.ouput.jpa.adapter;

import com.aalvarezg.ms_publications.domain.model.Post;
import com.aalvarezg.ms_publications.infrastructure.exception.NoDataFoundException;
import com.aalvarezg.ms_publications.infrastructure.ouput.jpa.entity.PostEntity;
import com.aalvarezg.ms_publications.infrastructure.ouput.jpa.mapper.PostEntityMapper;
import com.aalvarezg.ms_publications.infrastructure.ouput.jpa.repository.IPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostJpaAdapterTest {

    @Mock
    private IPostRepository postRepository;

    @InjectMocks
    private PostJpaAdapter postJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSavePost() {
        // Arrange
        Post post = new Post();
        post.setId(1L);
        post.setContent("Sample content");
        post.setUserId(1L);
        post.setUsername("user1");
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());

        PostEntity postEntity = PostEntityMapper.toEntity(post);

        when(postRepository.save(any(PostEntity.class))).thenReturn(postEntity);

        // Act
        Post savedPost = postJpaAdapter.savePost(post);

        // Assert
        assertNotNull(savedPost);
        assertEquals(post.getContent(), savedPost.getContent());
        verify(postRepository, times(1)).save(any(PostEntity.class));
    }

    @Test
    void testGetById() {
        // Arrange
        Post post = new Post();
        post.setId(1L);
        post.setContent("Sample content");
        post.setUserId(1L);
        post.setUsername("user1");
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());

        PostEntity postEntity = PostEntityMapper.toEntity(post);

        when(postRepository.findById(1L)).thenReturn(Optional.of(postEntity));

        // Act
        Post foundPost = postJpaAdapter.getById(1L);

        // Assert
        assertNotNull(foundPost);
        assertEquals(post.getId(), foundPost.getId());
        assertEquals(post.getContent(), foundPost.getContent());
        verify(postRepository, times(1)).findById(1L);
    }

    @Test
    void testGetById_NoDataFoundException() {
        // Arrange
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> postJpaAdapter.getById(1L));
        verify(postRepository, times(1)).findById(1L);
    }

    @Test
    void testGetPost() {
        // Arrange
        Post post1 = new Post();
        post1.setId(1L);
        post1.setContent("Content 1");
        post1.setUserId(1L);
        post1.setUsername("user1");
        post1.setCreatedAt(LocalDateTime.now());
        post1.setUpdatedAt(LocalDateTime.now());

        Post post2 = new Post();
        post2.setId(2L);
        post2.setContent("Content 2");
        post2.setUserId(2L);
        post2.setUsername("user2");
        post2.setCreatedAt(LocalDateTime.now());
        post2.setUpdatedAt(LocalDateTime.now());

        PostEntity postEntity1 = PostEntityMapper.toEntity(post1);
        PostEntity postEntity2 = PostEntityMapper.toEntity(post2);

        Pageable pageable = PageRequest.of(0, 10);
        Page<PostEntity> postEntityPage = new PageImpl<>(List.of(postEntity1, postEntity2));

        when(postRepository.findAllByOrderByCreatedAtDesc(pageable)).thenReturn(postEntityPage);

        // Act
        Page<Post> result = postJpaAdapter.getPost(0, 10);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals("Content 1", result.getContent().get(0).getContent());
        assertEquals("Content 2", result.getContent().get(1).getContent());
        verify(postRepository, times(1)).findAllByOrderByCreatedAtDesc(pageable);
    }
}
